CREATE SEQUENCE T_MOTORISTA_SEQUENCE
    START WITH 1
    INCREMENT BY 1
    NOCACHE
    NOCYCLE;

CREATE SEQUENCE T_CAMINHAO_SEQUENCE
    START WITH 1
    INCREMENT BY 1
    NOCACHE
    NOCYCLE;


CREATE TABLE T_MOTORISTA (
            id INTEGER DEFAULT T_MOTORISTA_SEQUENCE.NEXTVAL NOT NULL,
            nome_motorista VARCHAR2(30) NOT NULL,
            nr_cpf VARCHAR2(11) NOT NULL,
            nr_celular VARCHAR2(9) NOT NULL,
            nr_celular_ddd VARCHAR2(2) NOT NULL,
            nr_celular_ddi VARCHAR2(3) NOT NULL,
            disponivel VARCHAR2(20) DEFAULT 'DISPONIVEL'
        );

                ALTER TABLE T_MOTORISTA
                    ADD CONSTRAINT PK_T_MOTORISTA PRIMARY KEY (id);

                ALTER TABLE T_MOTORISTA ADD CONSTRAINT UN_T_MOTORISTA_NR_CPF
                    UNIQUE (nr_cpf);

                ALTER TABLE T_MOTORISTA ADD CONSTRAINT CK_T_MOTORISTA_NR_CPF
                    CHECK (REGEXP_LIKE(nr_cpf, '^\d{11}$'));

                ALTER TABLE T_MOTORISTA ADD CONSTRAINT UN_T_MOTORISTA_NR_CELULAR
                    UNIQUE (nr_celular);

                ALTER TABLE T_MOTORISTA ADD CONSTRAINT CK_T_MOTORISTA_NR_CELULAR
                    CHECK (REGEXP_LIKE(nr_celular,'^\d{9}$'));

                ALTER TABLE T_MOTORISTA ADD CONSTRAINT CK_T_MOTORISTA_NR_CELULAR_DDD
                                    CHECK (REGEXP_LIKE(nr_celular_ddd, '^\d{1,2}$'));

                ALTER TABLE T_MOTORISTA ADD CONSTRAINT CK_T_MOTORISTA_NR_CELULAR_DDI
                                                    CHECK (REGEXP_LIKE(nr_celular_ddi, '^\d{1,3}$'));



CREATE TABLE T_CAMINHAO (
            id INTEGER DEFAULT T_CAMINHAO_SEQUENCE.NEXTVAL NOT NULL,
            placa VARCHAR2(7) NOT NULL,
            ano_modelo DATE NOT NULL,
            marca VARCHAR2(20) NOT NULL,
            modelo VARCHAR2(20) NOT NULL,
            disponivel VARCHAR2(20) DEFAULT 'DISPONIVEL'
        );

        ALTER TABLE T_CAMINHAO
            ADD CONSTRAINT PK_T_CAMINHAO PRIMARY KEY (id);

        ALTER TABLE T_CAMINHAO ADD CONSTRAINT UN_T_CAMINHAO_PLACA
            UNIQUE (placa);

        ALTER TABLE T_CAMINHAO ADD CONSTRAINT CK_T_CAMINHAO_PLACA
            CHECK(REGEXP_LIKE(placa,'^[A-Z]{3}\d[A-Z]\d{2}$'));


GRANT ALTER ON T_MOTORISTA TO agenda_notificacao;
GRANT ALTER ON T_CAMINHAO TO agenda_notificacao;
GRANT REFERENCES ON T_MOTORISTA TO agenda_notificacao;
GRANT REFERENCES ON T_CAMINHAO TO agenda_notificacao;