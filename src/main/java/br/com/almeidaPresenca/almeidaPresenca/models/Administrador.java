package br.com.almeidaPresenca.almeidaPresenca.models;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity(name = "administradores")
@Table(name = "administradores")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
//o lombok cria os getters e setters, alem dos construtores com todos parametros e nenhum

public class Administrador {

    @GeneratedValue(strategy = GenerationType.IDENTITY) // Usando auto incremento
    @Column(name = "idAdministrador")
    @Id
    private Integer idAdministrador;

    @Column(name = "nome", length = 100, nullable = false)
    private String nome;

    @Column(name = "email",length = 80, nullable = false, unique = true)
    private String email;

    @Column(name = "senha", length = 100, nullable = false)
    private String senha;


}
