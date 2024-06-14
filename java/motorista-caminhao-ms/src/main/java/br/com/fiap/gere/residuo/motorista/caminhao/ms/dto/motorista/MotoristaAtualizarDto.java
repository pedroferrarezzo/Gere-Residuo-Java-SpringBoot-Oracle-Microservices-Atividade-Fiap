package br.com.fiap.gere.residuo.motorista.caminhao.ms.dto.motorista;

import br.com.fiap.gere.residuo.motorista.caminhao.ms.model.StatusDisponivel;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.UniqueElements;

public record MotoristaAtualizarDto(
        @NotNull(message = "O Id do motorista é obrigatório!")
        Long id,

        @Size(max = 30, message = "O nome do motorista deve ter no máximo 30 caracteres!")
        @NotBlank(message = "O nome do motorista(a) é obrigatório!")
        String nomeMotorista,


        @Size(min = 11, max = 11, message = "O número de CPF deve ter 11 caracteres!")
        @NotBlank(message = "O número de CPF é obrigatório!")
        String nrCpf,

        @Size(min = 9, max = 9, message = "O número de celular deve ter 9 caracteres!")
        @NotBlank(message = "O número de celular é obrigatório!")
        String nrCelular,


        @Size(min = 1, max = 2, message = "O número de DDD deve ter entre 1 e 2 caracteres!")
        @NotBlank(message = "O número de DDD é obrigatório!")
        String nrCelularDdd,

        @Size(min = 1, max = 3, message = "O número de DDD deve ter entre 1 e 2 caracteres!")
        @NotBlank(message = "O número de DDI é obrigatório!")
        String nrCelularDdi

) {
}
