package br.com.almeidaPresenca.almeidaPresenca.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import br.com.almeidaPresenca.almeidaPresenca.dao.GraduacaoDAO;
import br.com.almeidaPresenca.almeidaPresenca.dao.rowmapper.GraduacaoRowMapper;
import br.com.almeidaPresenca.almeidaPresenca.models.GraduacaoVO;

@Repository
public class GraduacaoDAOImpl implements GraduacaoDAO {

    private static final Logger logger = LoggerFactory.getLogger(GraduacaoDAOImpl.class);
    private final NamedParameterJdbcTemplate namedJdbcTemplate;

    public GraduacaoDAOImpl(DataSource dataSource) {
        this.namedJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public List<GraduacaoVO> obterTodasGraduacoes() {
        logger.info("Executando: obterTodasGraduacoes");

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT *                                    ");
        sql.append("FROM GRADUACOES                             ");
        sql.append("ORDER BY ID_GRADUACAO                       ");

        return namedJdbcTemplate.query(sql.toString(), new HashMap<>(), new GraduacaoRowMapper());
    }

    @Override
    public GraduacaoVO obterPorId(Integer idGraduacao) {
        logger.info("Executando: findById");

        Map<String, Object> params = new HashMap<>();
        params.put("ID_GRADUACAO", idGraduacao);

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT *                                    ");
        sql.append("FROM GRADUACOES                             ");
        sql.append("WHERE ID_GRADUACAO = :ID_GRADUACAO          ");

        return namedJdbcTemplate.queryForObject(sql.toString(), params, new GraduacaoRowMapper());
    }
}
