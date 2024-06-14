package br.com.fiap.gere.residuo.agenda.notificacao.ms.exception;

public class NaoDisponivelException extends RuntimeException{
    public NaoDisponivelException(String mensagem) {
        super(mensagem);
    }
}
