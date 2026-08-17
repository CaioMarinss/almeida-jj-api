package br.com.almeidaPresenca.almeidaPresenca.services;

import br.com.almeidaPresenca.almeidaPresenca.dao.AlunoDAO;
import br.com.almeidaPresenca.almeidaPresenca.models.AlunoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlunoService {

    @Autowired
    private AlunoDAO alunoDAO;

    public AlunoVO insertAluno(AlunoVO alunoVO) {
       return alunoDAO.insertAluno(alunoVO);
    }

    //alterar cadastro de aluno
    public AlunoVO updateAluno(Integer idAluno, AlunoVO alunoVOAlterado){
       AlunoVO alunoVOAtual = alunoDAO.obterPorId(idAluno);

       if (alunoVOAtual == null) {
           return null;
       }

       alunoVOAtual.setNome(alunoVOAlterado.getNome());
       alunoVOAtual.setCpf(alunoVOAlterado.getCpf());
       alunoVOAtual.setEmail(alunoVOAlterado.getEmail());
       alunoVOAtual.setSenha(alunoVOAlterado.getSenha());
       alunoVOAtual.setIdGraduacao(alunoVOAlterado.getIdGraduacao());
       alunoVOAtual.setSituacao(alunoVOAlterado.getSituacao());
       alunoVOAtual.setIcAdministrador(alunoVOAlterado.getIcAdministrador());
       alunoVOAtual.setDtPagamento(alunoVOAlterado.getDtPagamento());
       alunoVOAtual.setIdPlano(alunoVOAlterado.getIdPlano());
       alunoVOAtual.setDtExpiracaoPlano(alunoVOAlterado.getDtExpiracaoPlano());

       return alunoDAO.updateAluno(idAluno, alunoVOAtual);
    }
}
