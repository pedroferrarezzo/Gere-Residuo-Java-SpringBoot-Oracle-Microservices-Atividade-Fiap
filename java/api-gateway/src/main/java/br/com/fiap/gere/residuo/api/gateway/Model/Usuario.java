package br.com.fiap.gere.residuo.api.gateway.Model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;


@Entity
@Table(name = "T_USUARIO")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode


public class Usuario implements UserDetails {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "T_USUARIO_SEQUENCE"
    )
    @SequenceGenerator(
            name = "T_USUARIO_SEQUENCE",
            sequenceName = "T_USUARIO_SEQUENCE",
            allocationSize = 1
    )
    private Long id;

    private String nome;

    private String email;

    private String senha;

    @Enumerated(EnumType.STRING)
    private UsuarioRoles role;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (this.role == UsuarioRoles.ADMIN) {
            return List.of(
                    new SimpleGrantedAuthority("ROLE_ADMIN"),
                    new SimpleGrantedAuthority("ROLE_OPERATOR"),
                    new SimpleGrantedAuthority("ROLE_USER")
            );
        }


        else if(this.role == UsuarioRoles.OPERATOR){
            return List.of(
                    new SimpleGrantedAuthority("ROLE_OPERATOR"),
                    new SimpleGrantedAuthority("ROLE_USER")
            );
        }

        else {
            return List.of(
                    new SimpleGrantedAuthority("ROLE_USER")
            );}
    }

    @Override
    public String getPassword() {
        return this.senha;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}