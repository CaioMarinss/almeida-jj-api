package br.com.almeidaPresenca.almeidaPresenca.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ALUNOS", schema = "ALMEIDAJJ",
        uniqueConstraints = @UniqueConstraint(name = "UK_ALUNOS_EMAIL", columnNames = "EMAIL"))
public class AlunoVO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_ALUNO")
    private Integer idAluno;

    @Column(name = "NOME", length = 50, nullable = false)
    private String nome;

    @Column(name = "CPF", length = 11, nullable = false)
    private String cpf;

    @Column(name = "EMAIL", length = 80, nullable = false)
    private String email;

    @Column(name = "SENHA")
    private String senha;

    @Column(name = "ID_GRADUACAO", nullable = false)
    private Integer idGraduacao;

    @Column(name = "SITUACAO", length = 1, nullable = false)
    private String situacao;

    @Column(name = "IC_ADMINISTRADOR", length = 1, nullable = false)
    private String icAdministrador;

    @Temporal(TemporalType.DATE)
    @Column(name = "DT_PAGAMENTO")
    private Date dtPagamento;

    @Column(name = "ID_PLANO", nullable = false)
    private Integer idPlano;

    @Temporal(TemporalType.DATE)
    @Column(name = "DT_EXPIRACAO_PLANO")
    private Date dtExpiracaoPlano;

}
