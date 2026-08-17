package br.com.almeidaPresenca.almeidaPresenca.dao.impl;

import br.com.almeidaPresenca.almeidaPresenca.dao.GraduacaoDAO;
import br.com.almeidaPresenca.almeidaPresenca.dao.rowmapper.GraduacaoRowMapper;
import br.com.almeidaPresenca.almeidaPresenca.models.GraduacaoVO;
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
public class GraduacaoDAOImpl implements GraduacaoDAO {

    private static final Logger logger = LoggerFactory.getLogger(GraduacaoDAOImpl.class);
    private final NamedParameterJdbcTemplate namedJdbcTemplate;

    public GraduacaoDAOImpl(DataSource dataSource) {
        this.namedJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public List<GraduacaoVO> findAll() {
        logger.info("Executando: findAll");

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT ID_GRADUACAO, FAIXA, DESCRICAO ");
        sql.append("FROM ALMEIDAJJ.GRADUACOES ");
        sql.append("ORDER BY ID_GRADUACAO");
        return namedJdbcTemplate.query(sql.toString(), new HashMap<>(), new GraduacaoRowMapper());
    }

    @Override
    public Optional<GraduacaoVO> findById(Integer idGraduacao) {
        logger.info("Executando: findById");

        Map<String, Object> params = new HashMap<>();
        params.put("ID_GRADUACAO", idGraduacao);

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT ID_GRADUACAO, FAIXA, DESCRICAO ");
        sql.append("FROM ALMEIDAJJ.GRADUACOES ");
        sql.append("WHERE ID_GRADUACAO = :ID_GRADUACAO");

        List<GraduacaoVO> list = namedJdbcTemplate.query(sql.toString(), params, new GraduacaoRowMapper());
        return list.isEmpty() ? Optional.empty() : Optional.of(list.get(0));
    }

    @Override
    public GraduacaoVO insert(GraduacaoVO graduacaoVO) {
        logger.info("Executando: insert");

        KeyHolder keyHolder = new GeneratedKeyHolder();
        Map<String, Object> params = new HashMap<>();
        params.put("FAIXA", graduacaoVO.getFaixa());
        params.put("DESCRICAO", graduacaoVO.getDescricao());

        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO ALMEIDAJJ.GRADUACOES ");
        sql.append("(FAIXA, DESCRICAO) ");
        sql.append("VALUES (:FAIXA, :DESCRICAO)");

        namedJdbcTemplate.update(sql.toString(), params, keyHolder, new String[]{"ID_GRADUACAO"});

        Number generatedId = keyHolder.getKey();
        if (generatedId != null) {
            graduacaoVO.setIdGraduacao(generatedId.intValue());
        }
        return graduacaoVO;
    }

    @Override
    public GraduacaoVO update(GraduacaoVO graduacaoVO) {
        logger.info("Executando: update");

        Map<String, Object> params = new HashMap<>();
        params.put("FAIXA", graduacaoVO.getFaixa());
        params.put("DESCRICAO", graduacaoVO.getDescricao());
        params.put("ID_GRADUACAO", graduacaoVO.getIdGraduacao());

        StringBuilder sql = new StringBuilder();
        sql.append("UPDATE ALMEIDAJJ.GRADUACOES ");
        sql.append("SET FAIXA = :FAIXA, DESCRICAO = :DESCRICAO ");
        sql.append("WHERE ID_GRADUACAO = :ID_GRADUACAO");

        namedJdbcTemplate.update(sql.toString(), params);
        return graduacaoVO;
    }

    @Override
    public boolean deleteById(Integer idGraduacao) {
        logger.info("Executando: deleteById");

        Map<String, Object> params = new HashMap<>();
        params.put("ID_GRADUACAO", idGraduacao);

        StringBuilder sql = new StringBuilder();
        sql.append("DELETE FROM ALMEIDAJJ.GRADUACOES ");
        sql.append("WHERE ID_GRADUACAO = :ID_GRADUACAO");

        return namedJdbcTemplate.update(sql.toString(), params) > 0;
    }
}
