package kz.mouzitoto.gransoftwaretest2.dao.entity;


import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "t_operationtypes")
@Data
public class OperationType {

    public static final String REMOVE_STYLES = "REMOVE_STYLES";

    @Id
    @SequenceGenerator(name = "s_main", sequenceName = "s_main", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "s_main")
    private Long id;

    @Column(name = "vname")
    private String name;

    @Column(name = "vcode")
    private String code;
}
