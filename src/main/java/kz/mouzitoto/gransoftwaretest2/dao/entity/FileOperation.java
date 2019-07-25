package kz.mouzitoto.gransoftwaretest2.dao.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "t_fileoperations")
@Data
public class FileOperation {

    @Id
    @SequenceGenerator(name = "s_main", sequenceName = "s_main", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "s_main")
    private Long id;

    @Column(name = "vfilename")
    private String fileName;

    @Column(name = "nfilesizeinbytes")
    private Long fileSizeInBytes;

    @ManyToOne
    @JoinColumn(name = "noperationtypeid")
    private OperationType operationType;

    @Column(name = "dstartdate")
    private LocalDateTime startDate;

    @Column(name = "dfinishdate")
    private LocalDateTime finishDate;

}
