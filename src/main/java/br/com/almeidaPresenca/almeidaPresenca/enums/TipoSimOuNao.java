package br.com.almeidaPresenca.almeidaPresenca.enums;

public enum TipoSimOuNao {
    SIM("S"),
    NAO("N");

    private final String value;

    TipoSimOuNao(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
