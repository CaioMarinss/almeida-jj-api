package br.com.almeidaPresenca.almeidaPresenca.enums;

public enum SituacaoAtivoInativo {
  ATIVO("A", "Ativo"),
  INATIVO("I", "Inativo");

  private final String value;
  private final String descricao;

  SituacaoAtivoInativo(String value, String descricao) {
    this.value = value;
    this.descricao = descricao;
  }

  public String getValue() {
    return value;
  }

  public String getDescricao() {
    return descricao;
  }
}
