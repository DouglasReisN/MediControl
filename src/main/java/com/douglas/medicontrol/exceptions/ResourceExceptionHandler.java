package com.douglas.medicontrol.exceptions;


import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice//Evita o uso do try/cacth ,e monitora todos os controllers do sistema
public class ResourceExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)//Se qualquer lugar do código lançar uma ResourceNotFoundException, o Spring automaticamente desvia a rota e cai dentro desse metodo.
    public ResponseEntity<StandardError> resourceNotFound(ResourceNotFoundException e, HttpServletRequest request) {

        String error = "Recurso não encontrado";
        HttpStatus status = HttpStatus.NOT_FOUND;

        //montando o formulário de erro com os dados exatos do momento
        StandardError err = new StandardError(
                Instant.now(),
                status.value(),
                error,
                e.getMessage(),
                request.getRequestURI()
        );
        return ResponseEntity.status(status).body(err);

    }

}
