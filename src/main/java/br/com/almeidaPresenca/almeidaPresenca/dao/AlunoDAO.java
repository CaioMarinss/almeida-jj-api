package br.com.almeidaPresenca.almeidaPresenca.dao;

import br.com.almeidaPresenca.almeidaPresenca.enums.SituacaoAtivoInativo;
import br.com.almeidaPresenca.almeidaPresenca.models.AlunoVO;
import java.util.List;

public interface AlunoDAO {

  List<AlunoVO> obterTodosAlunosAtivos();

  AlunoVO obterPorId(Integer idAluno);

  AlunoVO obterPorEmail(String email);

  AlunoVO insertAluno(AlunoVO alunoVO);

  AlunoVO updateAluno(Integer idAlunoAtual, AlunoVO alunoNovoVO);

  void atualizaSitAluno(Integer idAluno, SituacaoAtivoInativo situacao);

  void atualizaSenha(Integer idAluno, String novaSenha);
}
