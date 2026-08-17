package br.com.almeidaPresenca.almeidaPresenca.dao;

import br.com.almeidaPresenca.almeidaPresenca.models.GraduacaoVO;

import java.util.List;
import java.util.Optional;

public interface GraduacaoDAO {

    List<GraduacaoVO> findAll();

    Optional<GraduacaoVO> findById(Integer idGraduacao);

    GraduacaoVO insert(GraduacaoVO graduacaoVO);

    GraduacaoVO update(GraduacaoVO graduacaoVO);

    boolean deleteById(Integer idGraduacao);
}
