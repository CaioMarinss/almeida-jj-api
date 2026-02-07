package br.com.almeidaPresenca.almeidaPresenca.repository;

import br.com.almeidaPresenca.almeidaPresenca.models.PlanoVO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface  PlanoRepository extends JpaRepository<PlanoVO, Integer> {
}
