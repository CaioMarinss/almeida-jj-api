package br.com.almeidaPresenca.almeidaPresenca.dao.rowmapper;

import br.com.almeidaPresenca.almeidaPresenca.models.PlanoVO;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

public class PlanoRowMapper implements RowMapper<PlanoVO> {

  @Override
  public PlanoVO mapRow(ResultSet rs, int rowNum) throws SQLException {
    PlanoVO plano = new PlanoVO();
    plano.setIdPlano(rs.getInt("ID_PLANO"));
    plano.setPeriodo(rs.getString("PERIODO"));
    plano.setQtdMeses(rs.getInt("QTD_MESES"));
    return plano;
  }
}
