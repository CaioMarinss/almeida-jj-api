package br.com.almeidaPresenca.almeidaPresenca.dao.rowmapper;

import br.com.almeidaPresenca.almeidaPresenca.models.GraduacaoVO;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

public class GraduacaoRowMapper implements RowMapper<GraduacaoVO> {

  @Override
  public GraduacaoVO mapRow(ResultSet rs, int rowNum) throws SQLException {
    GraduacaoVO graduacao = new GraduacaoVO();
    graduacao.setIdGraduacao(rs.getInt("ID_GRADUACAO"));
    graduacao.setFaixa(rs.getString("FAIXA"));
    graduacao.setDescricao(rs.getString("DESCRICAO"));
    return graduacao;
  }
}
