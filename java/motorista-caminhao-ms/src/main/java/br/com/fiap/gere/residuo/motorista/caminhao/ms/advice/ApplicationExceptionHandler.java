package br.com.fiap.gere.residuo.motorista.caminhao.ms.advice;

import br.com.fiap.gere.residuo.motorista.caminhao.ms.exception.CaminhaoNaoEncontradoException;
import br.com.fiap.gere.residuo.motorista.caminhao.ms.exception.MotoristaNaoEncontradoException;
import org.springframework.core.codec.DecodingException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.UnsatisfiedServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class ApplicationExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(DecodingException.class)
    public Map<String, String> manusearDecodingException(DecodingException erro) {
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("message", "Erro ao tentar deserializar o JSON passado. Você está escrevendo corretamente o status de disponibilidade? ([DISPONIVEL, OCUPADO])");

        return errorMap;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> manusearArgumentosInvalidos(MethodArgumentNotValidException erro) {
        Map<String, String> errorMap = new HashMap<>();
        List<FieldError> campos = erro.getBindingResult().getFieldErrors();
        for(FieldError campo : campos) {
            errorMap.put(campo.getField(), campo.getDefaultMessage());
        }

        return errorMap;
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(DataIntegrityViolationException.class)
    public Map<String, String> manusearViolacaoDeIntegridade(DataIntegrityViolationException erro) {
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("message", "Erro de integridade no banco de dados!");

        return errorMap;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(MotoristaNaoEncontradoException.class)
    public Map<String, String> manusearMotoristaNaoEncontrado(MotoristaNaoEncontradoException erro) {
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("message", erro.getMessage());

        return errorMap;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(CaminhaoNaoEncontradoException.class)
    public Map<String, String> manusearCaminhaoNaoEncontrado(CaminhaoNaoEncontradoException erro) {
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("message", erro.getMessage());

        return errorMap;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public Map<String, String> manusearMessagemNaoLegivel(HttpMessageNotReadableException erro) {
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("message", "Verifique a sintaxe do body da requisição!");

        return errorMap;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoResourceFoundException.class)
    public Map<String, String> manusearRecursoNaoEncontrado(NoResourceFoundException erro) {
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("message", "Recurso não encontrado no servidor!");

        return errorMap;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public Map<String, String> manusearTipoDeArgumentoIncorreto(MethodArgumentTypeMismatchException erro) {
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("message", "Verifique o tipo de dado passado como parâmetro!");
        return errorMap;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(UnsatisfiedServletRequestParameterException.class)
    public Map<String, String> manusearArgumentoNaoSatisfeitoServlet(UnsatisfiedServletRequestParameterException erro) {
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("message", "Parametros obrigatórios estão faltando!");
        return errorMap;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public Map<String, String> manusearFaltaDeArgumentoIncorretoServlet(MissingServletRequestParameterException erro) {
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("message", "Verifique a sintaxe dos argumentos passados, algum argumento está faltando!");
        return errorMap;
    }
}
