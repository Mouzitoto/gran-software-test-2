package kz.mouzitoto.gransoftwaretest2.model.response;

import kz.mouzitoto.gransoftwaretest2.dao.entity.FileOperation;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class FileOperationResponse {

    private Long id;
    private String fileName;
    private Long fileSizeInBytes;
    private OperationTypeResponse operationType;
    private LocalDateTime startDate;
    private LocalDateTime finishDate;

    // запрещаем использовать пустой конструктор
    private FileOperationResponse() {
    }

    public FileOperationResponse(FileOperation fileOperation) {
        this.id = fileOperation.getId();
        this.fileName = fileOperation.getFileName();
        this.fileSizeInBytes = fileOperation.getFileSizeInBytes();
        this.operationType = new OperationTypeResponse(fileOperation.getOperationType());
        this.startDate = fileOperation.getStartDate();
        this.finishDate = fileOperation.getFinishDate();
    }
}
