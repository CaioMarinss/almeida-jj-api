package br.com.almeidaPresenca.almeidaPresenca.models;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity(name = "ALUNOS")
@Table(name = "ALUNOS")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor


public class AlunoVO {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDALUNO")
    @Id
    private Integer idAluno;

    @Column(name = "NOME",length = 45, nullable = false)
    private String nome;

    @ManyToOne
    @JoinColumn(name = "IDGRADUACAO")
    private GraduacaoVO graduacaoVO;

    @Column(name = "DTPAGAMENTO")
    @Temporal(TemporalType.DATE)
    private Date dtPagamento;

    @ManyToOne
    @JoinColumn(name = "IDPLANO")
    private PlanoVO planoVO;

    @Column(name = "SITUACAO", nullable = false)
    private Boolean situacao;

}
