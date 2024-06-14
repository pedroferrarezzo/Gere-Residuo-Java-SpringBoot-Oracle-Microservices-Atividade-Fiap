package br.com.fiap.gere.residuo.api.gateway.dto;

import br.com.fiap.gere.residuo.api.gateway.Model.Usuario;
import br.com.fiap.gere.residuo.api.gateway.Model.UsuarioRoles;

public record UsuarioExibicaoDto(
        Long id,
        String nome,
        String email,
        UsuarioRoles role
) {
    public UsuarioExibicaoDto(Usuario usuario) {
        this(usuario.getId(), usuario.getNome(), usuario.getEmail(), usuario.getRole());
    }
}
