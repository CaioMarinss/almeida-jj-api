package br.com.almeidaPresenca.almeidaPresenca.dao.impl;

import br.com.almeidaPresenca.almeidaPresenca.dao.PlanoDAO;
import br.com.almeidaPresenca.almeidaPresenca.dao.rowmapper.PlanoRowMapper;
import br.com.almeidaPresenca.almeidaPresenca.models.PlanoVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class PlanoDAOImpl implements PlanoDAO {

    private static final Logger logger = LoggerFactory.getLogger(PlanoDAOImpl.class);
    private final NamedParameterJdbcTemplate namedJdbcTemplate;

    public PlanoDAOImpl(DataSource dataSource) {
        this.namedJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public List<PlanoVO> findAll() {
        logger.info("Executando: findAll");

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT ID_PLANO, PERIODO, QTD_MESES ");
        sql.append("FROM ALMEIDAJJ.PLANOS ");
        sql.append("ORDER BY ID_PLANO");
        return namedJdbcTemplate.query(sql.toString(), new HashMap<>(), new PlanoRowMapper());
    }

    @Override
    public Optional<PlanoVO> findById(Integer idPlano) {
        logger.info("Executando: findById");

        Map<String, Object> params = new HashMap<>();
        params.put("ID_PLANO", idPlano);

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT ID_PLANO, PERIODO, QTD_MESES ");
        sql.append("FROM ALMEIDAJJ.PLANOS ");
        sql.append("WHERE ID_PLANO = :ID_PLANO");

        List<PlanoVO> list = namedJdbcTemplate.query(sql.toString(), params, new PlanoRowMapper());
        return list.isEmpty() ? Optional.empty() : Optional.of(list.get(0));
    }

    @Override
    public PlanoVO insert(PlanoVO planoVO) {
        logger.info("Executando: insert");

        KeyHolder keyHolder = new GeneratedKeyHolder();
        Map<String, Object> params = new HashMap<>();
        params.put("PERIODO", planoVO.getPeriodo());
        params.put("QTD_MESES", planoVO.getQtdMeses());

        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO ALMEIDAJJ.PLANOS ");
        sql.append("(PERIODO, QTD_MESES) ");
        sql.append("VALUES (:PERIODO, :QTD_MESES)");

        namedJdbcTemplate.update(sql.toString(), params, keyHolder, new String[]{"ID_PLANO"});

        Number generatedId = keyHolder.getKey();
        if (generatedId != null) {
            planoVO.setIdPlano(generatedId.intValue());
        }
        return planoVO;
    }

    @Override
    public PlanoVO update(PlanoVO planoVO) {
        logger.info("Executando: update");

        Map<String, Object> params = new HashMap<>();
        params.put("PERIODO", planoVO.getPeriodo());
        params.put("QTD_MESES", planoVO.getQtdMeses());
        params.put("ID_PLANO", planoVO.getIdPlano());

        StringBuilder sql = new StringBuilder();
        sql.append("UPDATE ALMEIDAJJ.PLANOS ");
        sql.append("SET PERIODO = :PERIODO, QTD_MESES = :QTD_MESES ");
        sql.append("WHERE ID_PLANO = :ID_PLANO");

        namedJdbcTemplate.update(sql.toString(), params);
        return planoVO;
    }

    @Override
    public boolean deleteById(Integer idPlano) {
        logger.info("Executando: deleteById");

        Map<String, Object> params = new HashMap<>();
        params.put("ID_PLANO", idPlano);

        StringBuilder sql = new StringBuilder();
        sql.append("DELETE FROM ALMEIDAJJ.PLANOS ");
        sql.append("WHERE ID_PLANO = :ID_PLANO");

        return namedJdbcTemplate.update(sql.toString(), params) > 0;
    }
}
