package br.com.almeidaPresenca.almeidaPresenca.dao;

import br.com.almeidaPresenca.almeidaPresenca.models.PlanoVO;

import java.util.List;
import java.util.Optional;

public interface PlanoDAO {

    List<PlanoVO> findAll();

    Optional<PlanoVO> findById(Integer idPlano);

    PlanoVO insert(PlanoVO planoVO);

    PlanoVO update(PlanoVO planoVO);

    boolean deleteById(Integer idPlano);
}
