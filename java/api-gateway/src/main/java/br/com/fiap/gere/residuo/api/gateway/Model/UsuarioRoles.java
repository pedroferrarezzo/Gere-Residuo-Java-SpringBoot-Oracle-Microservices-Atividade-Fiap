package br.com.fiap.gere.residuo.api.gateway.Model;

public enum UsuarioRoles {
    ADMIN("admin"),
    OPERATOR("operator"),
    USER("user");

    private String role;

    UsuarioRoles(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
