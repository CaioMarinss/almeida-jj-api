package br.com.almeidaPresenca.almeidaPresenca.dto;

public record ResponseDTO(String token, int idAluno, String name, String email, boolean isAdmin) {

  // o front ta esperando esses parametros

}
