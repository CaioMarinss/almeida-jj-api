package br.com.almeidaPresenca.almeidaPresenca.enums;

public enum MsgSucesso {
    SUC001("SUC001", "Cadastro realizado com sucesso! Verifique seu e-mail para ativação."),
    SUC002("SUC002", "Email de recuperação enviado com sucesso."),
    SUC003("SUC003", "Email de verificação enviado com sucesso.");

    private final String value;
    private final String descricao;

    MsgSucesso(String value, String descricao) {
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
