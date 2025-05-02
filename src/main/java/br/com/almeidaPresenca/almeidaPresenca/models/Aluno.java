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
//o lombok cria os getters e setters, alem dos construtores com todos parametros e nenhum

public class Aluno {

    @GeneratedValue(strategy = GenerationType.IDENTITY) // Usando auto incremento
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

    @ManyToOne //muitos alunos para 1 plano
    @JoinColumn(name = "idPlano") //na tabela "plano" qual o ID?
    private Plano plano;

    @Column(name = "situacao", nullable = false)
    private Boolean situacao;

}
