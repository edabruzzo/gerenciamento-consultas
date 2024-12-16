package br.com.abruzzo.med.voll.exceptions;

public interface IMensagem {
    String getModulo();

    int getCodigo();

    String getMensagem();

    default String getCodigoCompleto() {
        String var10000 = this.getModulo();
        return var10000 + "-" + String.format("%03d", this.getCodigo());
    }
}