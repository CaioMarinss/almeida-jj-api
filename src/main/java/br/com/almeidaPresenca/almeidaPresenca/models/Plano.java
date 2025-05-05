package br.com.almeidaPresenca.almeidaPresenca.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "planos")
@Table(name = "planos")
public class  Plano {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idPlano")
    private Integer idPlano;

    @Column(name = "periodo", length = 15)
    private String periodo;



}
