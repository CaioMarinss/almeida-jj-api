package br.com.almeidaPresenca.almeidaPresenca.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "GRADUACOES")
@Table(name = "GRADUACOES")
public class GraduacaoVO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDGRADUACAO")
    private Integer idGraduacao;

    @Column(name = "FAIXA", length = 2)
    private String faixa;


}
