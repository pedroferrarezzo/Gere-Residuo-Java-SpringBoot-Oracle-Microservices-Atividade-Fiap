package br.com.fiap.gere.residuo.agenda.notificacao.ms.model;

public enum TipoResiduo {
    PLASTICO("disponivel"),
    ORGANICO("organico"),
    VIDRO("vidro"),

    GERAL("geral");

    private String tipoResiduo;

    TipoResiduo (String tipoResiduo) {
        this.tipoResiduo = tipoResiduo;
    }

    public String getTipoResiduo() {
        return tipoResiduo;
    }

}
