package br.com.almeidaPresenca.almeidaPresenca.dao;

import java.util.List;

import br.com.almeidaPresenca.almeidaPresenca.models.AlunoVO;

public interface AlunoDAO {

    List<AlunoVO> obterTodosAlunosAtivos();

    AlunoVO obterPorId(Integer idAluno);

    AlunoVO obterPorEmail(String email);

    AlunoVO insertAluno(AlunoVO alunoVO);

    AlunoVO updateAluno(Integer idAlunoAtual, AlunoVO alunoNovoVO);
}
