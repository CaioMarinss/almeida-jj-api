package br.com.almeidaPresenca.almeidaPresenca.services;

import java.util.List;

import br.com.almeidaPresenca.almeidaPresenca.models.Aluno;
import br.com.almeidaPresenca.almeidaPresenca.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlunoService {

    //aqui vamos apenas colocar a logica do Aluno Repository (no caso extends do jpa repository)
    // por isso então vamos criar um NEW alunoRepository
    @Autowired
    private AlunoRepository alunoRepository;

    //listar alunos

    public List<Aluno> findAll(){
        return alunoRepository.findAll();
    }

    //mostrar um aluno pelo id

    public Aluno findById(Integer idAluno){
        return alunoRepository.findById(idAluno).orElse(null);
    }

    //Cadastrar novo aluno
    public Aluno insertNewAluno(Aluno aluno){
        return alunoRepository.save(aluno);
    }

    //alterar cadastro de aluno
    public Aluno update(Integer idAluno, Aluno alunoAlterado){
        Aluno alunoAtual = findById(idAluno);

        alunoAtual.setNome(alunoAlterado.getNome());
        alunoAtual.setGraduacao(alunoAlterado.getGraduacao());
        alunoAtual.setSituacao(alunoAlterado.getSituacao());
        alunoAtual.setDtPagamento(alunoAlterado.getDtPagamento());
        alunoAtual.setPlano(alunoAlterado.getPlano());

        return alunoRepository.save(alunoAtual);
    }

    //deletar aluno
    public boolean deleteById(Integer idAluno){
        Aluno aluno = findById(idAluno);
        if (aluno == null){
            return false;
        }else{
            alunoRepository.deleteById(idAluno);
            return true;
        }

    }
}
