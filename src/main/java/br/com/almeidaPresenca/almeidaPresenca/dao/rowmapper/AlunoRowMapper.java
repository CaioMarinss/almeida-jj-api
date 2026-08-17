package br.com.almeidaPresenca.almeidaPresenca.dao.rowmapper;

import br.com.almeidaPresenca.almeidaPresenca.models.AlunoVO;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AlunoRowMapper implements RowMapper<AlunoVO> {

    @Override
    public AlunoVO mapRow(ResultSet rs, int rowNum) throws SQLException {
        AlunoVO aluno = new AlunoVO();

        aluno.setIdAluno(rs.getInt("ID_ALUNO"));
        aluno.setNome(rs.getString("NOME"));
        aluno.setCpf(rs.getString("CPF"));
        aluno.setEmail(rs.getString("EMAIL"));
        aluno.setSenha(rs.getString("SENHA"));
        aluno.setIdGraduacao(rs.getInt("ID_GRADUACAO"));
        aluno.setSituacao(rs.getString("SITUACAO"));
        aluno.setIcAdministrador(rs.getString("IC_ADMINISTRADOR"));
        aluno.setDtPagamento(rs.getDate("DT_PAGAMENTO"));
        aluno.setIdPlano(rs.getInt("ID_PLANO"));
        aluno.setDtExpiracaoPlano(rs.getDate("DT_EXPIRACAO_PLANO"));

        return aluno;
    }
}
