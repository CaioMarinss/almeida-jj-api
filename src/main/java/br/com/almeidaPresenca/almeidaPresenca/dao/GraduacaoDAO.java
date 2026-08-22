package br.com.almeidaPresenca.almeidaPresenca.dao;

import java.util.List;

import br.com.almeidaPresenca.almeidaPresenca.models.GraduacaoVO;

public interface GraduacaoDAO {

    List<GraduacaoVO> obterTodasGraduacoes();

    GraduacaoVO obterPorId(Integer idGraduacao);

}
