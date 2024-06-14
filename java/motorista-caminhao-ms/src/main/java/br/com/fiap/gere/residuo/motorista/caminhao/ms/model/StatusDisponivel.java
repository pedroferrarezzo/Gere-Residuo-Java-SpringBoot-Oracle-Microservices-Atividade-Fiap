package br.com.fiap.gere.residuo.motorista.caminhao.ms.model;

public enum StatusDisponivel {
    DISPONIVEL("disponivel"),
    OCUPADO("ocupado");

    private String status;

    StatusDisponivel (String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
