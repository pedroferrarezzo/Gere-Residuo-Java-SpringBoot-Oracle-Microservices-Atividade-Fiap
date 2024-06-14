package br.com.fiap.gere.residuo.agenda.notificacao.ms.utils;

public class BairroUtils {

    public static Float calculaPercentualColeta(Integer pesoColetaKg, Integer pesoMLixeirasKg, Integer qtdLixeiras) {
        Float pesoColeta = Float.valueOf(pesoColetaKg);
        Float pesoMLixeira = Float.valueOf(pesoMLixeirasKg);
        Float qtdLixeiraFloat = Float.valueOf(qtdLixeiras);

        Float divisao = (pesoColeta / (pesoMLixeira*qtdLixeiraFloat));

        return (divisao*100);

    }
}
