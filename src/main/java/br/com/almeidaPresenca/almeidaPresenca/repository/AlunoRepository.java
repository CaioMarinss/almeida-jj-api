package br.com.almeidaPresenca.almeidaPresenca.repository;

import br.com.almeidaPresenca.almeidaPresenca.models.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlunoRepository extends JpaRepository<Aluno, Integer> {
}
