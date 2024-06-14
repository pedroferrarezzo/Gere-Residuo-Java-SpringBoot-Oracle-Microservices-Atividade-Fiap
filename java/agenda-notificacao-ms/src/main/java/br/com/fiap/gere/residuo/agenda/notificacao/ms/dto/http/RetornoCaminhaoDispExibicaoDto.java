package br.com.fiap.gere.residuo.agenda.notificacao.ms.dto.http;

import br.com.fiap.gere.residuo.agenda.notificacao.ms.model.http.Caminhao;
import br.com.fiap.gere.residuo.agenda.notificacao.ms.model.http.StatusDisponivel;

public record RetornoCaminhaoDispExibicaoDto(
    StatusDisponivel disponivel
) {
    public RetornoCaminhaoDispExibicaoDto(Caminhao caminhao) {
        this(
                caminhao.getDisponivel()
        );

    }
}
