package br.com.almeidaPresenca.almeidaPresenca.enums;

public enum Errors {
  ERR001("ERR001", "E-mail não encontrado"),
  ERR002("ERR002", "A senha não pode ser nula"),
  ERR003("ERR003", "E-mail não verificado, verifique sua caixa de entrada."),
  ERR004("ERR004", "Senha incorreta."),
  ERR005("ERR005", "E-mail já cadastrado."),
  ERR006("ERR006", "Erro ao enviar o email.");

  private final String value;
  private final String descricao;

  Errors(String value, String descricao) {
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
