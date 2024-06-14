package br.com.fiap.gere.residuo.motorista.caminhao.ms.dto.caminhao;

import br.com.fiap.gere.residuo.motorista.caminhao.ms.model.StatusDisponivel;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.UniqueElements;

import java.time.LocalDate;

public record CaminhaoAtualizarDto(
        @NotNull(message = "O Id do caminhao é obrigatório!")
        Long id,

        @NotBlank(message = "A placa do caminhão é obrigatória!")
        @Size(min = 7, max = 7, message = "A placa do caminhão deve ter 7 caracteres!")
        String placa,

        @NotNull(message = "O ano do modelo do caminhão é obrigatório!")
        LocalDate anoModelo,

        @Size(max = 20, message = "A marca do caminhão deve ter no máximo 20 caracteres!")
        @NotBlank(message = "A marca do caminhão é obrigatória!")
        String marca,

        @Size(max = 20, message = "O modelo do caminhão deve ter no máximo 20 caracteres!")
        @NotBlank(message = "O modelo caminhão é obrigatório!")
        String modelo
) {
}
