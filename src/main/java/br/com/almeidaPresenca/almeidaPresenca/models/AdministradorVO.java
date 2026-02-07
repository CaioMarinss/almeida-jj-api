package br.com.almeidaPresenca.almeidaPresenca.models;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "ADMINISTRADORES")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AdministradorVO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDADMINISTRADOR")
    private Integer idAdministrador;

    @Column(name = "NOME", length = 100, nullable = false)
    private String nome;

    @Column(name = "EMAIL", length = 80, nullable = false, unique = true)
    private String email;

    @Column(name = "SENHA", length = 100, nullable = false)
    private String senha;

    @Column(name = "VERIFICADO", nullable = false)
    private boolean verificado;
}