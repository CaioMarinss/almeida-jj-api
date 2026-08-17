package br.com.almeidaPresenca.almeidaPresenca.dao;

import br.com.almeidaPresenca.almeidaPresenca.models.PlanoVO;

import java.util.List;
import java.util.Optional;

public interface PlanoDAO {

    List<PlanoVO> obterTodosPlanos();

    PlanoVO obterPorId(Integer idPlano);
}
