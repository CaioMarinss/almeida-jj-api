CREATE SCHEMA almeidajj;
SET search_path TO almeidajj, public;

CREATE TABLE planos (
    id_plano SERIAL PRIMARY KEY,
    periodo VARCHAR(20),
    qtd_meses INTEGER NOT NULL,
    CONSTRAINT uk_planos_periodo UNIQUE (qtd_meses)
);

CREATE TABLE graduacoes (
    id_graduacao SERIAL PRIMARY KEY,
    faixa VARCHAR(2) NOT NULL,
    descricao VARCHAR(30) NOT NULL,
    CONSTRAINT uk_graduacoes_faixa UNIQUE (faixa)
);

CREATE TABLE alunos (
    id_aluno SERIAL PRIMARY KEY,
    nome VARCHAR(50) NOT NULL,
    cpf VARCHAR(11) NOT NULL,
    email VARCHAR(80) NOT NULL,
    id_graduacao INTEGER NOT NULL,
    situacao VARCHAR(1) NOT NULL DEFAULT 'I',
    ic_administrador VARCHAR(1) NOT NULL DEFAULT 'N',
    dt_pagamento DATE,
    id_plano INTEGER NOT NULL,
    dt_expiracao_plano DATE,
    CONSTRAINT fk_alunos_graduacoes FOREIGN KEY (id_graduacao) REFERENCES graduacoes (id_graduacao),
    CONSTRAINT fk_alunos_planos FOREIGN KEY (id_plano) REFERENCES planos (id_plano),
    CONSTRAINT uk_alunos_email UNIQUE (email)
);
CREATE INDEX idx_alunos_graduacao ON alunos (id_graduacao);
CREATE INDEX idx_alunos_plano ON alunos (id_plano);

CREATE TABLE aulas (
    id_aula SERIAL PRIMARY KEY,
    horario TIME NOT NULL,
    dthr_registro TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    CONSTRAINT uk_aulas_horario UNIQUE (horario)
);

CREATE TABLE presencas (
    id_presenca SERIAL PRIMARY KEY,
    id_aluno INTEGER NOT NULL,
    id_aula INTEGER NOT NULL,
    dthr_registro TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT uk_presenca_aluno_idaula UNIQUE (id_aluno, id_aula),
    CONSTRAINT fk_presencas_alunos FOREIGN KEY (id_aluno) REFERENCES alunos (id_aluno),
    CONSTRAINT fk_presencas_aulas FOREIGN KEY (id_aula) REFERENCES aulas (id_aula)
);

CREATE TABLE script_update (
    id_script SERIAL PRIMARY KEY,
    versao VARCHAR(10) NOT NULL,
    dh_incl TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);
