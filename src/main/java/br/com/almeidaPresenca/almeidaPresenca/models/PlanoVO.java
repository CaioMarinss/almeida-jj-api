package br.com.almeidaPresenca.almeidaPresenca.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(
    name = "PLANOS",
    schema = "ALMEIDAJJ",
    uniqueConstraints = @UniqueConstraint(name = "UK_PLANOS_PERIODO", columnNames = "QTD_MESES"))
public class PlanoVO {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ID_PLANO")
  private Integer idPlano;

  @Column(name = "PERIODO", length = 20)
  private String periodo;

  @Column(name = "QTD_MESES", nullable = false)
  private Integer qtdMeses;
}
