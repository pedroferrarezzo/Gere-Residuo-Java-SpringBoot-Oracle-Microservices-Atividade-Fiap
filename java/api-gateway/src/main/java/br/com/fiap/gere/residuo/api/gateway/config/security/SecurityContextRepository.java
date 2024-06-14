package br.com.fiap.gere.residuo.api.gateway.config.security;

import br.com.fiap.gere.residuo.api.gateway.exception.TokenJwtExpiradoException;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.security.SignatureException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.web.server.context.ServerSecurityContextRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.time.Instant;

@AllArgsConstructor
@Component
public class SecurityContextRepository implements ServerSecurityContextRepository {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public Mono<Void> save(ServerWebExchange swe, SecurityContext sc) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Mono<SecurityContext> load(ServerWebExchange swe) {
        return Mono.justOrEmpty(swe.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION))
                .filter(authHeader -> authHeader.startsWith("Bearer "))
                .flatMap(authHeader -> {

                    String authToken = authHeader.substring(7);
                    Authentication auth = new UsernamePasswordAuthenticationToken(authToken, authToken);


                    try {
                        return this.authenticationManager.authenticate(auth).map(SecurityContextImpl::new);
                    }

                    catch (MalformedJwtException | SignatureException erro) {
                        throw new MalformedJwtException("Erro ao tentar validar a assinatura do Token passado!");
                    }

                    catch (ExpiredJwtException erro) {
                        Instant dataExpiracao = erro.getClaims().getExpiration().toInstant();
                        Instant dataAtual = Instant.now();
                        long diferencaMilissegundos = Duration.between(dataExpiracao, dataAtual).toMillis();
                        String mensagem = String.format("JWT expirado em %s. Hora atual: %s, uma diferença de %d milissegundos.",
                                dataExpiracao, dataAtual, diferencaMilissegundos);
                        throw new TokenJwtExpiradoException(mensagem);
                    }
                });
    }
}
