package br.com.fiap.gere.residuo.motorista.caminhao.ms.dto.caminhao;

import br.com.fiap.gere.residuo.motorista.caminhao.ms.model.Caminhao;
import br.com.fiap.gere.residuo.motorista.caminhao.ms.model.StatusDisponivel;

public record CaminhaoDispExibicaoDto(
        StatusDisponivel disponivel
) {
    public CaminhaoDispExibicaoDto(Caminhao caminhao) {
        this(
                caminhao.getDisponivel()
        );
    }
}
