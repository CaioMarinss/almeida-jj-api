package br.com.almeidaPresenca.almeidaPresenca.models;

import jakarta.persistence.*;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "PLANOS")
@Table(name = "PLANOS")
public class PlanoVO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDPLANO")
    private Integer idPlano;

    @Column(name = "PERIODO", length = 15)
    private String periodo;

}
