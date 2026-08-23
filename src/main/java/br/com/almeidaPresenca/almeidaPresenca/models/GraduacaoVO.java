package br.com.almeidaPresenca.almeidaPresenca.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GraduacaoVO {

  private Integer idGraduacao;

  private String faixa;

  private String descricao;
}
