package br.com.fiap.gere.residuo.agenda.notificacao.ms.dto.http;

import br.com.fiap.gere.residuo.agenda.notificacao.ms.model.http.Bairro;
import br.com.fiap.gere.residuo.agenda.notificacao.ms.model.http.StatusDisponivel;

public record RetornoBairroDispExibicaoDto(
        StatusDisponivel disponivel
) {
    public RetornoBairroDispExibicaoDto (Bairro bairro) {
        this(
                bairro.getDisponivel()
        );

    }
}
