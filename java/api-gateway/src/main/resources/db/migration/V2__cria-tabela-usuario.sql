CREATE SEQUENCE T_USUARIO_SEQUENCE
    START WITH 1
    INCREMENT BY 1
    NOCACHE
    NOCYCLE;

CREATE TABLE T_USUARIO (
    ID INTEGER DEFAULT T_USUARIO_SEQUENCE.NEXTVAL NOT NULL,
    NOME VARCHAR2(100) NOT NULL,
    EMAIL VARCHAR2(100) UNIQUE NOT NULL,
    SENHA VARCHAR2(100) NOT NULL,
    ROLE VARCHAR2(50) DEFAULT 'USER');