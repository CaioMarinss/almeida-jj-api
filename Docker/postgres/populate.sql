SET search_path TO ALMEIDAJJ, public;

/* =========================
   PLANOS
   ========================= */
INSERT INTO PLANOS (ID_PLANO, PERIODO, QTD_MESES) VALUES
    (1, 'MENSAL', 1),
    (2, 'BIMESTRAL', 2),
    (3, 'TRIMESTRAL', 3),
    (4, 'SEMESTRAL', 6),
    (5, 'ANUAL', 12);


/* =========================
   GRADUACOES
   ========================= */
INSERT INTO GRADUACOES (ID_GRADUACAO, FAIXA, DESCRICAO) VALUES
    (1, 'FB', 'Faixa Branca'),
    (2, 'FA', 'Faixa Azul'),
    (3, 'FR', 'Faixa Roxa'),
    (4, 'FM', 'Faixa Marrom'),
    (5, 'FP', 'Faixa Preta');


/* =========================
   ALUNOS
   ========================= */
INSERT INTO ALUNOS (ID_ALUNO, NOME, CPF, EMAIL, SENHA, ID_GRADUACAO, SITUACAO, IC_ADMINISTRADOR, DT_PAGAMENTO, ID_PLANO, DT_EXPIRACAO_PLANO) VALUES
    (1, 'CAIO MARINS','12345678901', 'CAIO@GMAIL.COM', '$2a$10$8a1nH1C4X0QwJ7hWg8Rk7eQ9m3pG3l7vD4T8NOq3XnVv0NQw0d7mW', 2, 'I','N','2025-04-18', 5, '2026-04-18'),
    (2, 'RAUL','12345678902', 'RAUL@GMAIL.COM', '$2a$10$8a1nH1C4X0QwJ7hWg8Rk7eQ9m3pG3l7vD4T8NOq3XnVv0NQw0d7mW',3, 'I','N', '2022-03-12', 5, '2023-03-12'),
    (3, 'CAIERA','12345678903', 'CAIERA@GMAIL.COM', '$2a$10$8a1nH1C4X0QwJ7hWg8Rk7eQ9m3pG3l7vD4T8NOq3XnVv0NQw0d7mW',4, 'I','N', '2022-05-12', 5, '2023-05-12'),
    (4, 'LEO KRUCHEWSKY','12345678902', 'LEO@GMAIL.COM', '$2a$10$8a1nH1C4X0QwJ7hWg8Rk7eQ9m3pG3l7vD4T8NOq3XnVv0NQw0d7mW',3, 'I','N', '2022-03-12', 5, '2023-03-12'),
    (5, 'DANIEL BELO','12345678903', 'DANIEL@GMAIL.COM', '$2a$10$8a1nH1C4X0QwJ7hWg8Rk7eQ9m3pG3l7vD4T8NOq3XnVv0NQw0d7mW',4, 'I','N', '2022-05-12', 5, '2023-05-12'),
    (6, 'DANILO','12345678904', 'DANILO@GMAIL.COM', '$2a$10$8a1nH1C4X0QwJ7hWg8Rk7eQ9m3pG3l7vD4T8NOq3XnVv0NQw0d7mW',1, 'I','N', '2024-06-27', 4, '2024-12-27'),
    (7, 'ANDER','12345678905', 'ANDER@GMAIL.COM', '$2a$10$8a1nH1C4X0QwJ7hWg8Rk7eQ9m3pG3l7vD4T8NOq3XnVv0NQw0d7mW',3, 'I','N', '2021-03-17', 5, '2022-03-17'),
    (8, 'ELIAS','12345678906' ,'ELIAS@GMAIL.COM', '$2a$10$8a1nH1C4X0QwJ7hWg8Rk7eQ9m3pG3l7vD4T8NOq3XnVv0NQw0d7mW',2, 'I','N', '2022-05-10', 5, '2023-05-10'),
    (9, 'GUI PRADO','12345678907', 'PRADO@GMAIL.COM', '$2a$10$8a1nH1C4X0QwJ7hWg8Rk7eQ9m3pG3l7vD4T8NOq3XnVv0NQw0d7mW',5, 'I','N', '2021-02-04', 5, '2022-02-04');

/* =========================
   AULAS
   ========================= */
INSERT INTO AULAS (ID_AULA, HORARIO, DTHR_REGISTRO) VALUES
  (1,'07:00:00', CURRENT_TIMESTAMP),
  (2,'09:00:00', CURRENT_TIMESTAMP),
  (3,'18:00:00', CURRENT_TIMESTAMP),
  (4,'19:00:00', CURRENT_TIMESTAMP),
  (5,'20:30:00', CURRENT_TIMESTAMP);

/* =========================
   PRESENCAS
   ========================= */
INSERT INTO PRESENCAS (ID_ALUNO, ID_AULA, DTHR_REGISTRO) VALUES
    (1, 3, NOW()),
    (4, 2, NOW()),
    (5, 1, NOW()),
    (6, 4, NOW());
