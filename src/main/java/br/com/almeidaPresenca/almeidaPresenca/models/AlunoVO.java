package br.com.almeidaPresenca.almeidaPresenca.models;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AlunoVO {

  private Integer idAluno;

  private String nome;

  private String cpf;

  private String email;

  private String senha;

  private Integer idGraduacao;

  private String situacao;

  private String icAdministrador;

  private Date dtPagamento;

  private Integer idPlano;

  private Date dtExpiracaoPlano;
}
