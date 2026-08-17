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
@Entity
@Table(name = "GRADUACOES", schema = "ALMEIDAJJ",
        uniqueConstraints = @UniqueConstraint(name = "UK_GRADUACOES_FAIXA", columnNames = "FAIXA"))
public class GraduacaoVO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_GRADUACAO")
    private Integer idGraduacao;

    @Column(name = "FAIXA", length = 2, nullable = false)
    private String faixa;

    @Column(name = "DESCRICAO", length = 30, nullable = false)
    private String descricao;
}
