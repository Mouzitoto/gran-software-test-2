package kz.mouzitoto.gransoftwaretest2.dao.repository;


import kz.mouzitoto.gransoftwaretest2.dao.GranSoftwareTest2Repository;
import kz.mouzitoto.gransoftwaretest2.dao.entity.FileOperation;
import kz.mouzitoto.gransoftwaretest2.dao.entity.OperationType;
import org.springframework.data.domain.Sort;

import java.time.LocalDateTime;
import java.util.List;


public interface FileOperationRepository extends GranSoftwareTest2Repository<FileOperation, Long> {

    List<FileOperation> findByOperationTypeAndStartDateBetween(OperationType operationType,
                                                               LocalDateTime startDateFrom,
                                                               LocalDateTime startDateTo,
                                                               Sort sort);


}
