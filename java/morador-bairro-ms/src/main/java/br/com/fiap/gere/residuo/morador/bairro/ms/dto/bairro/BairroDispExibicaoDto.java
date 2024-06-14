package br.com.fiap.gere.residuo.morador.bairro.ms.dto.bairro;

import br.com.fiap.gere.residuo.morador.bairro.ms.model.Bairro;
import br.com.fiap.gere.residuo.morador.bairro.ms.model.StatusDisponivel;

public record BairroDispExibicaoDto(
        StatusDisponivel disponivel
) {
    public BairroDispExibicaoDto(Bairro bairro) {
        this(
                bairro.getDisponivel()
        );

    }
}
