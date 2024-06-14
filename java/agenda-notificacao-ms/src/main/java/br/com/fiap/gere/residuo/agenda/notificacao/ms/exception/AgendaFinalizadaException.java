package br.com.fiap.gere.residuo.agenda.notificacao.ms.exception;

public class AgendaFinalizadaException extends RuntimeException{
    public AgendaFinalizadaException(String mensagem) {
        super(mensagem);
    }
}
