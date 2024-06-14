package br.com.fiap.gere.residuo.agenda.notificacao.ms.exception;

public class AgendaNaoEncontradaException extends RuntimeException{
    public AgendaNaoEncontradaException(String mensagem) {
        super(mensagem);
    }
}
