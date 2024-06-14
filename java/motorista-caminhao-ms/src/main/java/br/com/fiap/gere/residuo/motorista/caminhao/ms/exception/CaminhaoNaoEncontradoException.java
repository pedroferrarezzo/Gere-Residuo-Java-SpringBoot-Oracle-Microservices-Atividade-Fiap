package br.com.fiap.gere.residuo.motorista.caminhao.ms.exception;

public class CaminhaoNaoEncontradoException extends RuntimeException {
    public CaminhaoNaoEncontradoException(String mensagem) {
        super(mensagem);
    }
}
