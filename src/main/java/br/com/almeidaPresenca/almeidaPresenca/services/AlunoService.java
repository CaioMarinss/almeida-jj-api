package br.com.almeidaPresenca.almeidaPresenca.services;

import java.util.List;

import br.com.almeidaPresenca.almeidaPresenca.models.AlunoVO;
import br.com.almeidaPresenca.almeidaPresenca.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlunoService {

    @Autowired
    private AlunoRepository alunoRepository;

    //listar alunos
    public List<AlunoVO> findAll(){
        return alunoRepository.findAll();
    }

    //mostrar um aluno pelo id
    public AlunoVO findById(Integer idAluno){
        return alunoRepository.findById(idAluno).orElse(null);
    }

    //Cadastrar novo aluno
    public AlunoVO insertNewAluno(AlunoVO alunoVO){
        return alunoRepository.save(alunoVO);
    }

    //alterar cadastro de aluno
    public AlunoVO update(Integer idAluno, AlunoVO alunoVOAlterado){
        AlunoVO alunoVOAtual = findById(idAluno);

        alunoVOAtual.setNome(alunoVOAlterado.getNome());
        alunoVOAtual.setGraduacaoVO(alunoVOAlterado.getGraduacaoVO());
        alunoVOAtual.setSituacao(alunoVOAlterado.getSituacao());
        alunoVOAtual.setDtPagamento(alunoVOAlterado.getDtPagamento());
        alunoVOAtual.setPlanoVO(alunoVOAlterado.getPlanoVO());

        return alunoRepository.save(alunoVOAtual);
    }

    //deletar aluno
     public boolean deleteById(Integer idAluno){
        AlunoVO alunoVO = findById(idAluno);
        if (alunoVO == null){
            return false;
        }else{
            alunoRepository.deleteById(idAluno);
            return true;
        }

    }
}
