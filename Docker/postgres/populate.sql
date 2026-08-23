SET search_path TO ALMEIDAJJ, public;

/* =========================
   PLANOS
   ========================= */
INSERT INTO PLANOS (PERIODO, QTD_MESES) VALUES
    ('MENSAL', 1),
    ('BIMESTRAL', 2),
    ('TRIMESTRAL', 3),
    ('SEMESTRAL', 6),
    ('ANUAL', 12);


/* =========================
   GRADUACOES
   ========================= */
INSERT INTO GRADUACOES (FAIXA, DESCRICAO) VALUES
    ('FB', 'Faixa Branca'),
    ('FA', 'Faixa Azul'),
    ('FR', 'Faixa Roxa'),
    ('FM', 'Faixa Marrom'),
    ('FP', 'Faixa Preta');


/* =========================
   ALUNOS
   ========================= */
INSERT INTO ALUNOS (NOME, CPF, EMAIL, SENHA, ID_GRADUACAO, SITUACAO, IC_ADMINISTRADOR, DT_PAGAMENTO, ID_PLANO, DT_EXPIRACAO_PLANO) VALUES
    ( 'CAIO MARINS','12345678901', 'caio@gmail.com', '$2a$10$8a1nH1C4X0QwJ7hWg8Rk7eQ9m3pG3l7vD4T8NOq3XnVv0NQw0d7mW', 2, 'I','N','2025-04-18', 5, '2026-04-18'),
    ( 'RAUL','12345678902', 'raul@gmail.com', '$2a$10$8a1nH1C4X0QwJ7hWg8Rk7eQ9m3pG3l7vD4T8NOq3XnVv0NQw0d7mW',3, 'I','N', '2022-03-12', 5, '2023-03-12'),
    ( 'CAIERA','12345678903', 'caiera@gmail.com', '$2a$10$8a1nH1C4X0QwJ7hWg8Rk7eQ9m3pG3l7vD4T8NOq3XnVv0NQw0d7mW',4, 'I','N', '2022-05-12', 5, '2023-05-12'),
    ( 'LEO KRUCHEWSKY','12345678902', 'leo@gmail.com', '$2a$10$8a1nH1C4X0QwJ7hWg8Rk7eQ9m3pG3l7vD4T8NOq3XnVv0NQw0d7mW',3, 'I','N', '2022-03-12', 5, '2023-03-12'),
    ( 'DANIEL BELO','12345678903', 'daniel@gmail.com', '$2a$10$8a1nH1C4X0QwJ7hWg8Rk7eQ9m3pG3l7vD4T8NOq3XnVv0NQw0d7mW',4, 'I','N', '2022-05-12', 5, '2023-05-12'),
    ( 'DANILO','12345678904', 'danilo@gmail.com', '$2a$10$8a1nH1C4X0QwJ7hWg8Rk7eQ9m3pG3l7vD4T8NOq3XnVv0NQw0d7mW',1, 'I','N', '2024-06-27', 4, '2024-12-27'),
    ( 'ANDER','12345678905', 'ander@gmail.com', '$2a$10$8a1nH1C4X0QwJ7hWg8Rk7eQ9m3pG3l7vD4T8NOq3XnVv0NQw0d7mW',3, 'I','N', '2021-03-17', 5, '2022-03-17'),
    ( 'ELIAS','12345678906' ,'elias@gmail.com', '$2a$10$8a1nH1C4X0QwJ7hWg8Rk7eQ9m3pG3l7vD4T8NOq3XnVv0NQw0d7mW',2, 'I','N', '2022-05-10', 5, '2023-05-10'),
    ( 'GUI PRADO','12345678907', 'prado@gmail.com', '$2a$10$8a1nH1C4X0QwJ7hWg8Rk7eQ9m3pG3l7vD4T8NOq3XnVv0NQw0d7mW',5, 'I','N', '2021-02-04', 5, '2022-02-04');

/* =========================
   AULAS
   ========================= */
INSERT INTO AULAS (HORARIO, DTHR_REGISTRO) VALUES
  ('07:00:00', CURRENT_TIMESTAMP),
  ('09:00:00', CURRENT_TIMESTAMP),
  ('18:00:00', CURRENT_TIMESTAMP),
  ('19:00:00', CURRENT_TIMESTAMP),
  ('20:30:00', CURRENT_TIMESTAMP);

/* =========================
   PRESENCAS
   ========================= */
INSERT INTO PRESENCAS (ID_ALUNO, ID_AULA, DTHR_REGISTRO) VALUES
    (1, 3, NOW()),
    (4, 2, NOW()),
    (5, 1, NOW()),
    (6, 4, NOW());
