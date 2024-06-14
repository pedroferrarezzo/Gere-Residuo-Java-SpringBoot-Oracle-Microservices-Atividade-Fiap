package br.com.fiap.gere.residuo.motorista.caminhao.ms.dto.motorista;

import br.com.fiap.gere.residuo.motorista.caminhao.ms.model.Caminhao;
import br.com.fiap.gere.residuo.motorista.caminhao.ms.model.Motorista;
import br.com.fiap.gere.residuo.motorista.caminhao.ms.model.StatusDisponivel;

public record MotoristaDispExibicaoDto(
        StatusDisponivel disponivel
) {
    public MotoristaDispExibicaoDto(Motorista motorista) {
        this(
                motorista.getDisponivel()
        );
    }
}
