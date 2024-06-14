package br.com.fiap.gere.residuo.morador.bairro.ms.dto.bairro;

import br.com.fiap.gere.residuo.morador.bairro.ms.model.Bairro;
import br.com.fiap.gere.residuo.morador.bairro.ms.model.StatusDisponivel;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record BairroExibicaoDto(
        Long id,

        String nomeBairro,

        Integer qtdLixeiras,

        Integer pesoMLixeirasKg,

        Integer prcColeta,

        StatusDisponivel disponivel
) {

        public BairroExibicaoDto(Bairro bairro) {
                this(
                        bairro.getId(),
                        bairro.getNomeBairro(),
                        bairro.getQtdLixeiras(),
                        bairro.getPesoMLixeirasKg(),
                        bairro.getPrcColeta(),
                        bairro.getDisponivel()
                );
        }

}
