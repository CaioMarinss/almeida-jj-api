package br.com.almeidaPresenca.almeidaPresenca.dao;

import java.util.List;

import br.com.almeidaPresenca.almeidaPresenca.models.PlanoVO;

public interface PlanoDAO {

    List<PlanoVO> obterTodosPlanos();

    PlanoVO obterPorId(Integer idPlano);
}
