package br.com.almeidaPresenca.almeidaPresenca.services;

import br.com.almeidaPresenca.almeidaPresenca.dao.AlunoDAO;
import br.com.almeidaPresenca.almeidaPresenca.dto.ResetSenhaDTO;
import br.com.almeidaPresenca.almeidaPresenca.enums.Errors;
import br.com.almeidaPresenca.almeidaPresenca.enums.SituacaoAtivoInativo;
import br.com.almeidaPresenca.almeidaPresenca.models.AlunoVO;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AlunoService {

  @Autowired private AlunoDAO alunoDAO;
  @Autowired private PasswordEncoder passwordEncoder;

  public AlunoVO insertAluno(AlunoVO alunoVO) {
    return alunoDAO.insertAluno(alunoVO);
  }

  public void atualizaSitAluno(Integer idAluno, SituacaoAtivoInativo situacao) {
    alunoDAO.atualizaSitAluno(idAluno, situacao);
  }

  public void resetPassword(ResetSenhaDTO body) {
    String emailDoRequest = body.email();

    AlunoVO autenticado =
        (AlunoVO) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

    String emailAutenticado = autenticado.getEmail();

    if (!emailDoRequest.equalsIgnoreCase(emailAutenticado)) {
      throw new RuntimeException(Errors.ERR007.getDescricao());
    }

    Integer idAluno = alunoDAO.obterPorEmail(emailDoRequest).getIdAluno();
    if (Objects.isNull(idAluno)) {
      throw new RuntimeException(Errors.ERR001.getDescricao());
    }

    String novaSenha = passwordEncoder.encode(body.novaSenha());
    alunoDAO.atualizaSenha(idAluno, novaSenha);
  }
}
