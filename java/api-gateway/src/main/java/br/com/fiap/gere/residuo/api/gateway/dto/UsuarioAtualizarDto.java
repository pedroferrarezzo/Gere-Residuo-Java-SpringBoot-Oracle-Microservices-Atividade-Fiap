package br.com.fiap.gere.residuo.api.gateway.dto;

import br.com.fiap.gere.residuo.api.gateway.Model.UsuarioRoles;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UsuarioAtualizarDto(
        @NotNull(message = "O ID do usuário é obrigatório!")
        Long id,

        @NotBlank(message = "O nome do usuário é obrigatório!")
        String nome,

        @NotBlank(message = "O e-mail do usuário é obrigatório!")
        @Email(message = "O email do usuário é inválido!")
        String email,

        @NotBlank(message = "A senha do usuário é obrigatório!")
        @Size(min = 6, max = 20, message = "A senha deve conter entre 6 e 20 caracteres.")
        String senha,

        @NotNull(message = "A role do usuário é obrigatório!")
        UsuarioRoles role
) {
}
