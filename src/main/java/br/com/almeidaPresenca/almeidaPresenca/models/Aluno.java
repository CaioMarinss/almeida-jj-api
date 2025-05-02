package br.com.almeidaPresenca.almeidaPresenca.models;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity(name = "alunos")
@Table(name = "alunos")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor


public class Aluno {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idAluno")
    @Id
    private Integer idAluno;

    @Column(name = "nome",length = 45, nullable = false)
    private String nome;

    @ManyToOne
    @JoinColumn(name = "idGraduacao")
    private Graduacao graduacao;

    @Column(name = "dtPagamento")
    @Temporal(TemporalType.DATE)
    private Date dtPagamento;

    @ManyToOne
    @JoinColumn(name = "idPlano")
    private Plano plano;

    @Column(name = "situacao", nullable = false)
    private Boolean situacao;

}
