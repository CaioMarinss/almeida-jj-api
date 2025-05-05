package br.com.almeidaPresenca.almeidaPresenca.repository;

import br.com.almeidaPresenca.almeidaPresenca.models.Administrador;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdministradorRepository extends JpaRepository<Administrador, Integer> {
    Optional<Administrador> findByEmailIgnoreCase(String email);
}
