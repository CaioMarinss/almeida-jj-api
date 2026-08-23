package br.com.almeidaPresenca.almeidaPresenca.dao.impl;

import br.com.almeidaPresenca.almeidaPresenca.dao.AlunoDAO;
import br.com.almeidaPresenca.almeidaPresenca.dao.rowmapper.AlunoRowMapper;
import br.com.almeidaPresenca.almeidaPresenca.enums.SituacaoAtivoInativo;
import br.com.almeidaPresenca.almeidaPresenca.models.AlunoVO;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class AlunoDAOImpl implements AlunoDAO {

  private static final Logger logger = LoggerFactory.getLogger(AlunoDAOImpl.class);
  private final NamedParameterJdbcTemplate namedJdbcTemplate;

  public AlunoDAOImpl(DataSource dataSource) {
    this.namedJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
  }

  @Override
  public List<AlunoVO> obterTodosAlunosAtivos() {
    logger.info("Executando: obterTodosAlunosAtivos");

    Map<String, String> params = new HashMap<>();
    params.put("SIT_ATIVO", SituacaoAtivoInativo.ATIVO.getValue());

    StringBuilder sql = new StringBuilder();
    sql.append("SELECT *                                  ");
    sql.append("FROM ALUNOS                               ");
    sql.append("WHERE SITUACAO = :SIT_ATIVO               ");
    sql.append("ORDER BY ID_ALUNO                         ");

    return namedJdbcTemplate.query(sql.toString(), params, new AlunoRowMapper());
  }

  @Override
  public AlunoVO obterPorId(Integer idAluno) {
    logger.info("Executando: obterPorId");

    Map<String, Object> params = new HashMap<>();
    params.put("ID_ALUNO", idAluno);
    params.put("SIT_ATIVO", SituacaoAtivoInativo.ATIVO.getValue());

    StringBuilder sql = new StringBuilder();
    sql.append("SELECT *                                  ");
    sql.append("FROM ALUNOS                               ");
    sql.append("WHERE SITUACAO = :SIT_ATIVO               ");
    sql.append("AND ID_ALUNO = :ID_ALUNO                  ");

    return namedJdbcTemplate.queryForObject(sql.toString(), params, new AlunoRowMapper());
  }

  @Override
  public AlunoVO obterPorEmail(String email) {
    logger.info("Executando: obterPorEmail");

    Map<String, Object> params = new HashMap<>();
    params.put("EMAIL", email);

    StringBuilder sql = new StringBuilder();
    sql.append("SELECT *                                    ");
    sql.append("FROM ALUNOS                                 ");
    sql.append("WHERE LOWER(EMAIL) = LOWER(:EMAIL)          ");

    try {
      return namedJdbcTemplate.queryForObject(sql.toString(), params, new AlunoRowMapper());
    } catch (EmptyResultDataAccessException e) {
      logger.error("Erro ao obter aluno por email: " + e.getMessage());
      return null;
    }
  }

  @Override
  public AlunoVO insertAluno(AlunoVO alunoVO) {
    logger.info("Executando: insertAluno");

    KeyHolder keyHolder = new GeneratedKeyHolder();
    Map<String, Object> params = new HashMap<>();
    params.put("NOME", alunoVO.getNome());
    params.put("CPF", alunoVO.getCpf());
    params.put("EMAIL", alunoVO.getEmail());
    params.put("SENHA", alunoVO.getSenha());
    params.put("ID_GRADUACAO", alunoVO.getIdGraduacao());
    params.put("SITUACAO", alunoVO.getSituacao());
    params.put("IC_ADMINISTRADOR", alunoVO.getIcAdministrador());
    params.put("DT_PAGAMENTO", alunoVO.getDtPagamento());
    params.put("ID_PLANO", alunoVO.getIdPlano());
    params.put("DT_EXPIRACAO_PLANO", alunoVO.getDtExpiracaoPlano());

    StringBuilder sql = new StringBuilder();
    sql.append("INSERT INTO ALUNOS                                                      ");
    sql.append("(NOME, CPF, EMAIL, SENHA, ID_GRADUACAO, SITUACAO, IC_ADMINISTRADOR,     ");
    sql.append(" DT_PAGAMENTO, ID_PLANO, DT_EXPIRACAO_PLANO)                            ");
    sql.append("VALUES (:NOME, :CPF, :EMAIL, :SENHA, :ID_GRADUACAO, :SITUACAO,          ");
    sql.append(" :IC_ADMINISTRADOR, :DT_PAGAMENTO, :ID_PLANO, :DT_EXPIRACAO_PLANO)      ");

    namedJdbcTemplate.update(
        sql.toString(), new MapSqlParameterSource(params), keyHolder, new String[] {"id_aluno"});

    Number generatedId = keyHolder.getKey();
    if (generatedId != null) {
      alunoVO.setIdAluno(generatedId.intValue());
    }
    return alunoVO;
  }

  @Override
  public AlunoVO updateAluno(Integer idAlunoAtual, AlunoVO alunoNovoVO) {
    logger.info("Executando: updateAluno");

    Map<String, Object> params = new HashMap<>();
    params.put("ID_ALUNO", idAlunoAtual);

    params.put("NOME", alunoNovoVO.getNome());
    params.put("CPF", alunoNovoVO.getCpf());
    params.put("EMAIL", alunoNovoVO.getEmail());
    params.put("SENHA", alunoNovoVO.getSenha());
    params.put("ID_GRADUACAO", alunoNovoVO.getIdGraduacao());
    params.put("SITUACAO", alunoNovoVO.getSituacao());
    params.put("IC_ADMINISTRADOR", alunoNovoVO.getIcAdministrador());
    params.put("DT_PAGAMENTO", alunoNovoVO.getDtPagamento());
    params.put("ID_PLANO", alunoNovoVO.getIdPlano());
    params.put("DT_EXPIRACAO_PLANO", alunoNovoVO.getDtExpiracaoPlano());

    StringBuilder sql = new StringBuilder();
    sql.append(
        "UPDATE ALUNOS                                                                               ");
    sql.append(
        "SET NOME = :NOME, CPF = :CPF, EMAIL = :EMAIL, SENHA = :SENHA, ID_GRADUACAO = :ID_GRADUACAO, ");
    sql.append(
        " SITUACAO = :SITUACAO, IC_ADMINISTRADOR = :IC_ADMINISTRADOR, DT_PAGAMENTO = :DT_PAGAMENTO,  ");
    sql.append(
        " ID_PLANO = :ID_PLANO, DT_EXPIRACAO_PLANO = :DT_EXPIRACAO_PLANO                             ");
    sql.append("WHERE ID_ALUNO = :ID_ALUNO");

    namedJdbcTemplate.update(sql.toString(), params);
    alunoNovoVO.setIdAluno(idAlunoAtual);
    return alunoNovoVO;
  }
}
