package br.com.fiap.gere.residuo.morador.bairro.ms.dto.morador;

import br.com.fiap.gere.residuo.morador.bairro.ms.model.Bairro;
import br.com.fiap.gere.residuo.morador.bairro.ms.model.Morador;
import br.com.fiap.gere.residuo.morador.bairro.ms.model.StatusDisponivel;

public record MoradorExibicaoDto(
        Long id,

        Long bairro,

        String nomeMorador,

        String emailMorador
) {

        public MoradorExibicaoDto(Morador morador) {
                this(
                        morador.getId(),
                        morador.getBairro().getId(),
                        morador.getNomeMorador(),
                        morador.getEmailMorador()
                );
        }
}
