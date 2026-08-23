package br.com.almeidaPresenca.almeidaPresenca.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PlanoVO {

  private Integer idPlano;

  private String periodo;

  private Integer qtdMeses;
}
