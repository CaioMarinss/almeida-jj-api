package br.com.almeidaPresenca.almeidaPresenca.dao.impl;

import br.com.almeidaPresenca.almeidaPresenca.dao.PlanoDAO;
import br.com.almeidaPresenca.almeidaPresenca.dao.rowmapper.PlanoRowMapper;
import br.com.almeidaPresenca.almeidaPresenca.models.PlanoVO;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class PlanoDAOImpl implements PlanoDAO {

  private static final Logger logger = LoggerFactory.getLogger(PlanoDAOImpl.class);
  private final NamedParameterJdbcTemplate namedJdbcTemplate;

  public PlanoDAOImpl(DataSource dataSource) {
    this.namedJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
  }

  @Override
  public List<PlanoVO> obterTodosPlanos() {
    logger.info("Executando: obterTodosPlanos");

    StringBuilder sql = new StringBuilder();
    sql.append("SELECT *                                ");
    sql.append("FROM PLANOS                             ");
    sql.append("ORDER BY ID_PLANO                       ");
    return namedJdbcTemplate.query(sql.toString(), new HashMap<>(), new PlanoRowMapper());
  }

  @Override
  public PlanoVO obterPorId(Integer idPlano) {
    logger.info("Executando: obterPorId");

    Map<String, Object> params = new HashMap<>();
    params.put("ID_PLANO", idPlano);

    StringBuilder sql = new StringBuilder();
    sql.append("SELECT *                                ");
    sql.append("FROM PLANOS                             ");
    sql.append("WHERE ID_PLANO = :ID_PLANO              ");

    return namedJdbcTemplate.queryForObject(sql.toString(), params, new PlanoRowMapper());
  }
}
