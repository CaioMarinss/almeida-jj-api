package br.com.almeidaPresenca.almeidaPresenca.repository;

import br.com.almeidaPresenca.almeidaPresenca.models.AlunoVO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface  AlunoRepository extends JpaRepository<AlunoVO, Integer> {
}
