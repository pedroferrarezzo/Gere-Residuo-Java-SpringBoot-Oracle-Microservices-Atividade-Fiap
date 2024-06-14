package br.com.fiap.gere.residuo.morador.bairro.ms.dto.bairro;

import br.com.fiap.gere.residuo.morador.bairro.ms.model.StatusDisponivel;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record BairroAtualizarDto(
        @NotNull(message = "O ID do bairro é obrigatório!")
        Long id,

        @NotBlank(message = "O nome do bairro é obrigatório!")
        @Size(min = 1, max = 30, message = "O nome do morador deve estar entre 1 e 30 caracteres!")
        String nomeBairro,

        @NotNull(message = "A quantidade de lixeiras do bairro é obrigatória!")
        Integer qtdLixeiras,


        @NotNull(message = "O peso médio de cad lixeira do bairro em KG é obrigatório!")
        Integer pesoMLixeirasKg,

        @NotNull(message = "O percentual de coleta atual do bairro é obrigatório!")
        Integer prcColeta
) {


}
