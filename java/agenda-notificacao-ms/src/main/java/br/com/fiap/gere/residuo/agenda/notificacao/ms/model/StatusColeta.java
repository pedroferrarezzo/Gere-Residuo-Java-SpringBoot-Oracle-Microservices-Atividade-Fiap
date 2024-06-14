package br.com.fiap.gere.residuo.agenda.notificacao.ms.model;

public enum StatusColeta {
    EM_ANDAMENTO("em_andamento"),
    FINALIZADA("finalizada");

    private String status;

    StatusColeta (String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
