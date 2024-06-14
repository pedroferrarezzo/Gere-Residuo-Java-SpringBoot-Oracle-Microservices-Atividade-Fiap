package br.com.fiap.gere.residuo.api.gateway.exception;

public class TokenJwtExpiradoException extends RuntimeException {
    public TokenJwtExpiradoException(String mensagem) {
        super(mensagem);
    }
}
