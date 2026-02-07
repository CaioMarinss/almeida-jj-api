package br.com.almeidaPresenca.almeidaPresenca.repository;

import br.com.almeidaPresenca.almeidaPresenca.models.GraduacaoVO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface  GraduacaoRepository extends JpaRepository<GraduacaoVO, Integer> {
}
