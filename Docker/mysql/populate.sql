/* =========================
   PLANOS
   ========================= */
INSERT INTO PLANOS (idPlano, periodo) VALUES
    (1, 'mensal'),
    (2, 'bimestral'),
    (3, 'trimestral'),
    (4, 'semestral'),
    (5, 'anual');


/* =========================
   GRADUACOES
   ========================= */
INSERT INTO GRADUACOES (idGraduacao, faixa) VALUES
    (1, 'FB'),
    (2, 'FA'),
    (3, 'FR'),
    (4, 'FM'),
    (5, 'FP');


/* =========================
   ALUNOS
   ========================= */
INSERT INTO ALUNOS (idAluno, nome, idGraduacao, situacao, dtPagamento, idPlano) VALUES
    (1, 'Caio Marins', 2, 0, '2025-04-18', 5),
    (4, 'Angelo', 3, 1, '2022-03-12', 5),
    (5, 'Dani Belo', 4, 1, '2022-05-12', 5),
    (6, 'Danilo', 1, 1, '2024-06-27', 4),
    (7, 'Ander', 3, 1, '2021-03-17', 5),
    (8, 'Elias', 2, 1, '2022-05-10', 5),
    (9, 'Guilherme Prado', 5, 0, '2021-02-04', 5);


/* =========================
   ADMINISTRADORES
   ========================= */
INSERT INTO ADMINISTRADORES (idAdministrador, nome, email, senha, verificado) VALUES
  (1, 'Caio Marins', 'caio@teste.com', 'testando', 0),
  (4, 'Raul', 'raul@gmail.com', '$2a$10$bNPK.Ethd/XrnTOD4igZbeyj753tqY8XdKXGh9XCmhgT3wa3L00M6', 0),
  (5, 'Caiera', 'caiera@gmail.com', '$2a$10$TMxSUvn1OGAvMzhSrY6P0eUWv1DsstoweA8BX/FY7IQacz9RzYIYW', 0),
  (11, 'Teste Validator', 'validasenha@aa', '$2a$10$zByQaNTTB98d5xTf8P5IzuuNiZD8KI95oggExhrrr069TZ3uPacPi', 0),
  (12, 'Caca', 'cacacasd@asdasd', '$2a$10$AWFH3MJrUe485FRTWujsT.ijCSp6IO6lBgjxt1L5uRGdBIEhQme76', 0),
  (13, 'Caioba', 'caioba@fds', '$2a$10$p8F18hhpOKUGF.Scqy1w7uHPt9meBwg/rKEOvqwyFAt5knhWuvyvO', 0),
  (14, 'Caiobass', 'epaepa@malandro', '$2a$10$LhacIreAJPMMVDPKjuH3sO.yN9IgpBlCOygseCZDDxXfXdQrmZVrG', 0),
  (15, 'Teste Final', 'testefinal@teste.com', '$2a$10$yotLAxRzSf8yYnodstUX9eWTw0Wi7WNURFmnhsk/fKtrZWAhL7FfC', 0),
  (18, 'Caio', 'caio.marins011@gmail.com', '$2a$10$GQya6Ihc8.ubqJsVL3K5..JOWm1S2w9kR40xq2Pz35qK5EOADhrj6', 1);

/* =========================
   AULAS
   ========================= */
INSERT INTO AULAS (horario, dataHoraRegistro) VALUES
  ('07:00:00', '2025-04-14 13:31:26'),
  ('09:00:00', '2025-04-14 13:33:24'),
  ('19:00:00', '2025-04-14 13:33:33'),
  ('20:30:00', '2025-04-14 13:33:35');

/* =========================
   PRESENCAS
   ========================= */
INSERT INTO PRESENCAS (idAluno, horario, dataHoraRegistro) VALUES
    (1, '07:00:00', NOW()),
    (4, '19:00:00', NOW()),
    (5, '20:30:00', NOW()),
    (6, '09:00:00', NOW());
