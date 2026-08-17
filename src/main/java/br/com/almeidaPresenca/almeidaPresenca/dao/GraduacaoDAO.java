package br.com.almeidaPresenca.almeidaPresenca.dao;

import br.com.almeidaPresenca.almeidaPresenca.models.GraduacaoVO;

import java.util.List;
import java.util.Optional;

public interface GraduacaoDAO {

    List<GraduacaoVO> obterTodasGraduacoes();

    GraduacaoVO obterPorId(Integer idGraduacao);

}
