package br.com.fiap.gere.residuo.api.gateway.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.authentication.HttpStatusServerEntryPoint;
import org.springframework.security.web.server.authorization.HttpStatusServerAccessDeniedHandler;

@Configuration
@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
public class SecurityConfig {


    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private SecurityContextRepository securityContextRepository;

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
        return http
                .exceptionHandling((exceptions) -> exceptions
                        .authenticationEntryPoint(
                                new HttpStatusServerEntryPoint(HttpStatus.UNAUTHORIZED)
                        )
                        .accessDeniedHandler(new HttpStatusServerAccessDeniedHandler(HttpStatus.FORBIDDEN))
                )
                .csrf(csrf -> csrf.disable())
                .authenticationManager(authenticationManager)
                .securityContextRepository(securityContextRepository)
                .authorizeExchange(exchanges -> exchanges
                        .pathMatchers(HttpMethod.POST, "/login").permitAll()
                        .pathMatchers(HttpMethod.POST,"/registrar").permitAll()
                        .pathMatchers(HttpMethod.PUT,"/atualizarUsuario").hasAuthority("ADMIN")
                        .pathMatchers(HttpMethod.GET,"/listarUsuarios").hasAuthority("ADMIN")
                        .pathMatchers(HttpMethod.DELETE,"/excluirUsuario").hasAuthority("ADMIN")
                        .pathMatchers(HttpMethod.GET,"/actuator/health").permitAll()

                        .pathMatchers(HttpMethod.GET,"/morador.bairro.ms/**").hasAnyAuthority("USER", "OPERATOR", "ADMIN")
                        .pathMatchers(HttpMethod.GET,"/motorista.caminhao.ms/**").hasAnyAuthority("USER", "OPERATOR", "ADMIN")
                        .pathMatchers(HttpMethod.GET,"/agenda.notificacao.ms/**").hasAnyAuthority("USER", "OPERATOR", "ADMIN")

                        .pathMatchers(HttpMethod.POST,"/morador.bairro.ms/**").hasAnyAuthority("OPERATOR", "ADMIN")
                        .pathMatchers(HttpMethod.POST,"/motorista.caminhao.ms/**").hasAnyAuthority("OPERATOR", "ADMIN")
                        .pathMatchers(HttpMethod.POST,"/agenda.notificacao.ms/**").hasAnyAuthority("OPERATOR", "ADMIN")

                        .pathMatchers(HttpMethod.PUT,"/morador.bairro.ms/**").hasAnyAuthority("OPERATOR", "ADMIN")
                        .pathMatchers(HttpMethod.PUT,"/motorista.caminhao.ms/**").hasAnyAuthority("OPERATOR", "ADMIN")
                        .pathMatchers(HttpMethod.PUT,"/agenda.notificacao.ms/**").hasAnyAuthority("OPERATOR", "ADMIN")

                        .pathMatchers(HttpMethod.PATCH,"/morador.bairro.ms/**").hasAnyAuthority("OPERATOR", "ADMIN")
                        .pathMatchers(HttpMethod.PATCH,"/motorista.caminhao.ms/**").hasAnyAuthority("OPERATOR", "ADMIN")
                        .pathMatchers(HttpMethod.PATCH,"/agenda.notificacao.ms/**").hasAnyAuthority("OPERATOR", "ADMIN")

                        .pathMatchers(HttpMethod.DELETE,"/morador.bairro.ms/**").hasAnyAuthority("OPERATOR", "ADMIN")
                        .pathMatchers(HttpMethod.DELETE,"/motorista.caminhao.ms/**").hasAnyAuthority("OPERATOR", "ADMIN")
                        .pathMatchers(HttpMethod.DELETE,"/agenda.notificacao.ms/**").hasAnyAuthority("OPERATOR", "ADMIN")

                        .anyExchange().authenticated()
                )
                .build();
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}


