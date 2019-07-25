package kz.mouzitoto.gransoftwaretest2.model.response;

import kz.mouzitoto.gransoftwaretest2.dao.entity.OperationType;
import lombok.Getter;

@Getter
public class OperationTypeResponse {
    private Long id;
    private String name;
    private String code;

    // запрещаем использовать пустой конструктор
    private OperationTypeResponse() {
    }

    public OperationTypeResponse(OperationType operationType) {
        this.id = operationType.getId();
        this.name = operationType.getName();
        this.code = operationType.getCode();
    }
}
