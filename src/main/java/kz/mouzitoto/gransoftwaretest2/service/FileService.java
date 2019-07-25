package kz.mouzitoto.gransoftwaretest2.service;

import kz.mouzitoto.gransoftwaretest2.dao.entity.FileOperation;
import kz.mouzitoto.gransoftwaretest2.dao.entity.OperationType;
import kz.mouzitoto.gransoftwaretest2.dao.repository.FileOperationRepository;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTStyles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.time.LocalDateTime;

@Service
public class FileService {

    private static final String DOCX_FORMAT = "docx";

    private final FileOperationRepository fileOperationRepository;
    private final OperationTypeService operationTypeService;

    @Autowired
    public FileService(FileOperationRepository fileOperationRepository, OperationTypeService operationTypeService) {
        this.fileOperationRepository = fileOperationRepository;
        this.operationTypeService = operationTypeService;
    }

    /**
     * Этот метод выставляет всем элементам документа дефолтный стиль ("Normal" / "Обычный").
     *
     * Было еще одно решение для docx формата - разархивировать файл и подправить файл /word/styles.xml,
     *      удалив из него все стили. Затем заархивировать файлы обратно в единый файл docx.
     *
     *
     * Я не нашел вменяемого способа удалить стили из старого формата .doc.
     * Все решения, которые я перепробовал ломали документ в том или ином смысле,
     *      к примеру - исчезали картинки, исчезали таблицы, ломалась структура документа и тд.
     * Я пробовал эти решения:
     *      1. ApachePOI через присвоение всем элементам стандартных значений для все полей, которые отвечают за стили.
     *          К примеру все сеттеры для секций, параграфов, чарактерРанов.
     *      2. Конвертация док в хтмл и обратно через ApachePOI / Apache Tika / jodConverter (удаляя стили в хтмл)
     *      3. Конвертация док в докх через jodConverter
     *      4. Старые версии либы aspose words
     *      5. Заставить docx4j работать со старым форматом .doc
     *
     * Последнее решение, которое я не реализовывал - это создать абсолютно новый doc документ через ApachePOI
     *      на основе старого документа (бежать по всем элементам). Но там нужно долго разбираться какие свойства
     *      стилей мне нужно обнулить, а какие оставить, чтобы не поломалась структура документа. Как правильно
     *      обрабатывать картинки, таблицы, колонтитулы и прочие нестандартные элементы. Сейчас нет столько свободного
     *      времени, чтобы долго и кропотливо копаться в документации + методом тыка, так как либа уже давно
     *      не поддерживается и документашка слабовата.
     *
     * @param file
     * @return
     * @throws Exception
     */
    public ByteArrayInputStream removeStylesFromWordDocument(MultipartFile file) throws Exception {
        String fileName = file.getOriginalFilename();

        // validate file format, only docx allowed
        if (fileName != null && !fileName.isEmpty()) {
            String fileFormat = fileName.substring(fileName.lastIndexOf(".") + 1);
            if (!fileFormat.trim().toLowerCase().equals(DOCX_FORMAT))
                throw new Exception("Unsupported file format. Only '*.docx' file format allowed");
        } else
            throw new Exception("FileName must not be empty");


        LocalDateTime startDate = LocalDateTime.now();

        // change element styles to default style
        XWPFDocument document = new XWPFDocument(file.getInputStream());
        CTStyles ctStyles = CTStyles.Factory.newInstance();
        document.createStyles().setStyles(ctStyles);

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        document.write(out);
        document.close();

        LocalDateTime finishDate = LocalDateTime.now();

        // audit
        FileOperation fileOperation = new FileOperation();
        fileOperation.setFileName(fileName);
        fileOperation.setFileSizeInBytes(file.getSize());
        fileOperation.setOperationType(operationTypeService.getOperationTypeByCode(OperationType.REMOVE_STYLES));
        fileOperation.setStartDate(startDate);
        fileOperation.setFinishDate(finishDate);
        fileOperationRepository.save(fileOperation);

        return new ByteArrayInputStream(out.toByteArray());
    }

}
