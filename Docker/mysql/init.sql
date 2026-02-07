CREATE TABLE GRADUACOES (
                            IDGRADUACAO INT NOT NULL AUTO_INCREMENT,
                            FAIXA VARCHAR(2) DEFAULT NULL,
                            PRIMARY KEY (IDGRADUACAO),
                            UNIQUE KEY UK_GRADUACOES_FAIXA (FAIXA)
);

CREATE TABLE PLANOS (
                        IDPLANO INT NOT NULL AUTO_INCREMENT,
                        PERIODO VARCHAR(15) DEFAULT NULL,
                        PRIMARY KEY (IDPLANO),
                        UNIQUE KEY UK_PLANOS_PERIODO (PERIODO)
);

CREATE TABLE ADMINISTRADORES (
                                 IDADMINISTRADOR INT NOT NULL AUTO_INCREMENT,
                                 NOME VARCHAR(100) NOT NULL,
                                 EMAIL VARCHAR(80) NOT NULL,
                                 SENHA VARCHAR(100) NOT NULL,
                                 VERIFICADO TINYINT(1) NOT NULL DEFAULT 0,
                                 PRIMARY KEY (IDADMINISTRADOR),
                                 UNIQUE KEY UK_ADMIN_EMAIL (EMAIL)
);

CREATE TABLE AULAS (
                       HORARIO TIME NOT NULL,
                       DATAHORAREGISTRO DATETIME DEFAULT CURRENT_TIMESTAMP,
                       PRIMARY KEY (HORARIO),
                       UNIQUE KEY UK_AULAS_HORARIO (HORARIO)
);

CREATE TABLE ALUNOS (
                        IDALUNO INT NOT NULL AUTO_INCREMENT,
                        NOME VARCHAR(45) NOT NULL,
                        IDGRADUACAO INT DEFAULT NULL,
                        SITUACAO TINYINT(1) NOT NULL DEFAULT 0,
                        DTPAGAMENTO DATE DEFAULT NULL,
                        IDPLANO INT DEFAULT NULL,
                        PRIMARY KEY (IDALUNO),
                        KEY IDX_ALUNOS_GRADUACAO (IDGRADUACAO),
                        KEY IDX_ALUNOS_PLANO (IDPLANO),
                        CONSTRAINT FK_ALUNOS_GRADUACOES
                            FOREIGN KEY (IDGRADUACAO)
                                REFERENCES GRADUACOES (IDGRADUACAO),
                        CONSTRAINT FK_ALUNOS_PLANOS
                            FOREIGN KEY (IDPLANO)
                                REFERENCES PLANOS (IDPLANO)
);

CREATE TABLE PRESENCAS (
                           IDPRESENCA INT NOT NULL AUTO_INCREMENT,
                           IDALUNO INT NOT NULL,
                           HORARIO TIME NOT NULL,
                           DATAHORAREGISTRO DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
                           PRIMARY KEY (IDPRESENCA),
                           UNIQUE KEY UK_PRESENCA_ALUNO_HORARIO (IDALUNO, HORARIO),
                           KEY IDX_PRESENCAS_HORARIO (HORARIO),
                           CONSTRAINT FK_PRESENCAS_ALUNOS
                               FOREIGN KEY (IDALUNO)
                                   REFERENCES ALUNOS (IDALUNO),
                           CONSTRAINT FK_PRESENCAS_AULAS
                               FOREIGN KEY (HORARIO)
                                   REFERENCES AULAS (HORARIO)
);

CREATE TABLE SCRIPT_UPDATE (
                               IDSCRIPT INT NOT NULL AUTO_INCREMENT,
                               VERSAO VARCHAR(10) NOT NULL,
                               DH_INCL DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
                               PRIMARY KEY (IDSCRIPT)
);