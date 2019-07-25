package kz.mouzitoto.gransoftwaretest2.service;

import kz.mouzitoto.gransoftwaretest2.dao.entity.OperationType;
import kz.mouzitoto.gransoftwaretest2.dao.repository.OperationTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OperationTypeService {

    private final OperationTypeRepository operationTypeRepository;

    @Autowired
    public OperationTypeService(OperationTypeRepository operationTypeRepository) {
        this.operationTypeRepository = operationTypeRepository;
    }

    public OperationType getOperationTypeByCode(String code) throws Exception {
        return operationTypeRepository.findByCode(code)
                .orElseThrow(() -> new Exception("OperationType not found by code: " + code));
    }
}
