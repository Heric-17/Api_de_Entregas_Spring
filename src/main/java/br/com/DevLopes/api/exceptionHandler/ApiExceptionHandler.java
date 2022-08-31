package br.com.DevLopes.api.exceptionHandler;

import br.com.DevLopes.domain.exception.EntidadeNaoEncontradaException;
import br.com.DevLopes.domain.exception.NegocioException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {


    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        List<Problema.Campo> campos = new ArrayList<>();

        ex.getBindingResult().getAllErrors().forEach(err -> {

            String nome = ((FieldError) err).getField();
            String mensagem = err.getDefaultMessage();

            campos.add(new Problema.Campo(nome, mensagem));
        });


        Problema problema = new Problema();
        problema.setTitulo("Um ou mais campos estão inválidos. Preencha corretamente e tente novamente.");
        problema.setDataHora(OffsetDateTime.now());
        problema.setStatus(status.value());
        problema.setCampos(campos);

        return handleExceptionInternal(ex, problema, headers, status, request);
    }

    @ExceptionHandler(NegocioException.class)
    protected ResponseEntity<Object> handleExcepetion(NegocioException ex, WebRequest request) {

        HttpStatus status = HttpStatus.BAD_REQUEST;

        Problema problema = new Problema();
        problema.setTitulo(ex.getMessage());
        problema.setDataHora(OffsetDateTime.now());
        problema.setStatus(status.value());

        return handleExceptionInternal(ex, problema, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(EntidadeNaoEncontradaException.class)
    protected ResponseEntity<Object> handleEntidadeNaoEncontrada(EntidadeNaoEncontradaException ex, WebRequest request) {

        HttpStatus status = HttpStatus.NOT_FOUND;

        Problema problema = new Problema();
        problema.setTitulo(ex.getMessage());
        problema.setDataHora(OffsetDateTime.now());
        problema.setStatus(status.value());

        return handleExceptionInternal(ex, problema, new HttpHeaders(), status, request);
    }


}
