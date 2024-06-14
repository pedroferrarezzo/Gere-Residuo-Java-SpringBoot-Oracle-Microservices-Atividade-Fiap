package br.com.fiap.gere.residuo.morador.bairro.ms.dto.morador;

import br.com.fiap.gere.residuo.morador.bairro.ms.model.Bairro;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record MoradorCadastroDto(
        Long id,

        @NotNull(message = "O bairro do morador é obrigatório!")
        Bairro bairro,

        @NotBlank(message = "O nome do morador é obrigatório!")
        @Size(min = 1, max = 30, message = "O nome do morador deve estar entre 1 e 30 caracteres!")
        String nomeMorador,

        @NotBlank(message = "O email do morador é obrigatório!")
        @Email(message = "O email do morador deve estar no formato: meuemail@domain.com!")
        String emailMorador
) {

}
