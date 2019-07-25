package kz.mouzitoto.gransoftwaretest2.service;

import kz.mouzitoto.gransoftwaretest2.dao.entity.OperationType;
import kz.mouzitoto.gransoftwaretest2.dao.repository.FileOperationRepository;
import kz.mouzitoto.gransoftwaretest2.model.SortDirection;
import kz.mouzitoto.gransoftwaretest2.model.response.FileOperationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReportService {

    private static final String START_DATE = "startDate";

    private final FileOperationRepository fileOperationRepository;
    private final OperationTypeService operationTypeService;

    @Autowired
    public ReportService(FileOperationRepository fileOperationRepository, OperationTypeService operationTypeService) {
        this.fileOperationRepository = fileOperationRepository;
        this.operationTypeService = operationTypeService;
    }


    public List<FileOperationResponse> getRemoveStylesReport(LocalDate startDateFrom, LocalDate startDateTo, SortDirection sortDirection) throws Exception {
        return fileOperationRepository.findByOperationTypeAndStartDateBetween(
                operationTypeService.getOperationTypeByCode(OperationType.REMOVE_STYLES),
                startDateFrom.atStartOfDay(),
                startDateTo.atStartOfDay(),
                Sort.by(sortDirection.name().equals("asc") ? Sort.Direction.ASC : Sort.Direction.DESC, START_DATE)
        )
                .stream()
                .map(FileOperationResponse::new)
                .collect(Collectors.toList());
    }
}
