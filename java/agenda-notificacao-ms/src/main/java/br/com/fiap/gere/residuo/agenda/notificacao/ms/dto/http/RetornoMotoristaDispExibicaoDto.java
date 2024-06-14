package br.com.fiap.gere.residuo.agenda.notificacao.ms.dto.http;

import br.com.fiap.gere.residuo.agenda.notificacao.ms.model.http.Motorista;
import br.com.fiap.gere.residuo.agenda.notificacao.ms.model.http.StatusDisponivel;

public record RetornoMotoristaDispExibicaoDto(
        StatusDisponivel disponivel
) {
    public RetornoMotoristaDispExibicaoDto(Motorista motorista) {
        this(
                motorista.getDisponivel()
        );

    }
}
