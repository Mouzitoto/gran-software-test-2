package kz.mouzitoto.gransoftwaretest2.dao.repository;


import kz.mouzitoto.gransoftwaretest2.dao.GranSoftwareTest2Repository;
import kz.mouzitoto.gransoftwaretest2.dao.entity.OperationType;

import java.util.Optional;


public interface OperationTypeRepository extends GranSoftwareTest2Repository<OperationType, Long> {

    Optional<OperationType> findByCode(String code);


}
