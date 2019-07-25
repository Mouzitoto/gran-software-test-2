create sequence if not exists s_main;

DROP TABLE IF EXISTS t_operationtypes;

CREATE TABLE t_operationtypes
(
    id    bigint default s_main.nextval primary key,
    vname VARCHAR(250) NOT NULL,
    vcode VARCHAR(250) NOT NULL
);

INSERT INTO t_operationtypes (vname, vcode)
VALUES ('Удаление стилей из документа', 'REMOVE_STYLES');


DROP TABLE IF EXISTS t_fileoperations;

CREATE TABLE t_fileoperations
(
    id               bigint default s_main.nextval primary key,
    vfilename        VARCHAR(250) NOT NULL,
    nfilesizeinbytes bigint       NOT NULL,
    noperationtypeid numeric      NOT NULL
        constraint t_fileoperations_t_operationtypes_id_fk
            references t_operationtypes,
    dstartdate       TIMESTAMP    NOT NULL,
    dfinishdate      TIMESTAMP    NOT NULL
);

-- добавим старые значения, чтобы можно было проверить фильтр по датам в эндпоинте /report/removeStyles
insert into t_fileoperations (vfilename, nfilesizeinbytes, noperationtypeid, dstartdate, dfinishdate)
values ('1DayBefore.docx', 1234567, (select id from t_operationtypes where vcode = 'REMOVE_STYLES'),
        (current_timestamp - 1), (current_timestamp - 1));
insert into t_fileoperations (vfilename, nfilesizeinbytes, noperationtypeid, dstartdate, dfinishdate)
values ('2DaysBefore.docx', 1234567, (select id from t_operationtypes where vcode = 'REMOVE_STYLES'),
        (current_timestamp - 2), (current_timestamp - 2));
