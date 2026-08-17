package br.com.almeidaPresenca.almeidaPresenca.dao;

import br.com.almeidaPresenca.almeidaPresenca.models.AlunoVO;

import java.util.List;
import java.util.Optional;

public interface AlunoDAO {

    List<AlunoVO> obterTodosAlunosAtivos();

    AlunoVO obterPorId(Integer idAluno);

    AlunoVO obterPorEmail(String email);

    AlunoVO insertAluno(AlunoVO alunoVO);

    AlunoVO updateAluno(Integer idAlunoAtual, AlunoVO alunoNovoVO);
}
