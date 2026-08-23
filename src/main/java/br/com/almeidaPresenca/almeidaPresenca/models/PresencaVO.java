package br.com.almeidaPresenca.almeidaPresenca.models;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(
    name = "PRESENCAS",
    schema = "ALMEIDAJJ",
    uniqueConstraints =
        @UniqueConstraint(
            name = "UK_PRESENCA_ALUNO_IDAULA",
            columnNames = {"ID_ALUNO", "ID_AULA"}))
public class PresencaVO {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ID_PRESENCA")
  private Integer idPresenca;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "ID_ALUNO", nullable = false)
  private AlunoVO aluno;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "ID_AULA", nullable = false)
  private AulaVO aula;

  @Column(name = "DTHR_REGISTRO", nullable = false)
  private LocalDateTime dthrRegistro;

  @PrePersist
  public void prePersist() {
    if (dthrRegistro == null) {
      dthrRegistro = LocalDateTime.now();
    }
  }
}
