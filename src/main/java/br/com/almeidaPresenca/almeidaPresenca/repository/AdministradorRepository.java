package br.com.almeidaPresenca.almeidaPresenca.repository;

import br.com.almeidaPresenca.almeidaPresenca.models.AdministradorVO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdministradorRepository extends JpaRepository<AdministradorVO, Integer> {
    Optional<AdministradorVO> findByEmailIgnoreCase(String email);
}
