package br.com.almeidaPresenca.almeidaPresenca.models;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.time.LocalTime;
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
    name = "AULAS",
    schema = "ALMEIDAJJ",
    uniqueConstraints = @UniqueConstraint(name = "UK_AULAS_HORARIO", columnNames = "HORARIO"))
public class AulaVO {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ID_AULA")
  private Integer idAula;

  @Column(name = "HORARIO", nullable = false)
  private LocalTime horario;

  @Column(name = "DTHR_REGISTRO", nullable = false)
  private LocalDateTime dthrRegistro;

  @PrePersist
  public void prePersist() {
    if (dthrRegistro == null) {
      dthrRegistro = LocalDateTime.now();
    }
  }
}
