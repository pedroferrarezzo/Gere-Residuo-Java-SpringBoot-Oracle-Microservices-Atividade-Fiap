package br.com.fiap.gere.residuo.api.gateway.advice;

import br.com.fiap.gere.residuo.api.gateway.exception.UsuarioNaoEncontradoException;
import com.fasterxml.jackson.core.JsonParseException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.io.DeserializationException;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.core.codec.DecodingException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.UnsatisfiedServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.support.WebExchangeBindException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.reactive.resource.NoResourceFoundException;
import org.springframework.web.reactive.result.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.server.MissingRequestValueException;
import org.springframework.web.server.UnsatisfiedRequestParameterException;

import java.net.ConnectException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class ApplicationExceptionHandler {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MalformedJwtException.class)
    public Map<String, String> manusearJWTMalFormado(MalformedJwtException erro) {
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("message", "O JWT passado está mal formado!");
        return errorMap;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(DeserializationException.class)
    public Map<String, String> manusearJWTMalFormadoDois(DeserializationException erro) {
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("message", "O JWT passado está mal formado!");
        return errorMap;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(JsonParseException.class)
    public Map<String, String> manusearJWTMalFormadoTres(JsonParseException erro) {
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("message", "O JWT passado está mal formado!");
        return errorMap;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> manusearArgumentosInvalidos(MethodArgumentNotValidException erro) {
        Map<String, String> errorMap = new HashMap<>();
        List<FieldError> campos = erro.getBindingResult().getFieldErrors();
        for (FieldError campo : campos) {
            errorMap.put(campo.getField(), campo.getDefaultMessage());
        }

        return errorMap;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(WebExchangeBindException.class)
    public Map<String, String> manusearArgumentosInvalidosWebExchange(WebExchangeBindException erro) {
        Map<String, String> errorMap = new HashMap<>();
        List<FieldError> campos = erro.getBindingResult().getFieldErrors();
        for (FieldError campo : campos) {
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

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(DecodingException.class)
    public Map<String, String> manusearDecodingException(DecodingException erro) {
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("message", "Erro ao tentar deserializar o JSON passado. Você está escrevendo corretamente as ROLES? ([USER, OPERATOR, ADMIN])");

        return errorMap;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public Map<String, String> manusearMessagemNaoLegivel(HttpMessageNotReadableException erro) {
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("message", "Verifique a sintaxe do body da requisição!");

        return errorMap;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public Map<String, String> manusearTipoDeArgumentoIncorreto(MethodArgumentTypeMismatchException erro) {
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("message", "Verifique a sintaxe dos argumentos passados!");
        return errorMap;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(UsuarioNaoEncontradoException.class)
    public Map<String, String> manusearTipoDeArgumentoIncorreto(UsuarioNaoEncontradoException erro) {
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("message", erro.getMessage());
        return errorMap;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoResourceFoundException.class)
    public Map<String, String> manusearRecursoNaoEncontrado(NoResourceFoundException erro) {
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("message", "Recurso não encontrado no servidor! Ou pode ainda não estar pronto.");

        return errorMap;
    }

    @ResponseStatus(HttpStatus.BAD_GATEWAY)
    @ExceptionHandler(ConnectException.class)
    public Map<String, String> manusearConexaoException(ConnectException erro) {
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("message", "Aguarde, o endpoint que deseja acessar pode ainda não estar pronto! (conexão recusada)");

        return errorMap;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(UnsatisfiedRequestParameterException.class)
    public Map<String, String> manusearTipoDeArgumentoIncorreto(UnsatisfiedRequestParameterException erro) {
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("message", "Parametros obrigatórios estão faltando!");
        return errorMap;
    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MissingRequestValueException.class)
    public Map<String, String> manusearFaltaDeArgumentoIncorreto(MissingRequestValueException erro) {
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("message", "Verifique a sintaxe dos argumentos passados, algum argumento está faltando!");
        return errorMap;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(TypeMismatchException.class)
    public Map<String, String> manusearTipodeArgumentoIncorreto(TypeMismatchException erro) {
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("message", "Verifique o tipo de dado passado como parâmetro!");
        return errorMap;
    }





}
