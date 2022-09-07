package com.heitor.venda.exceptions.resources;

import com.heitor.venda.exceptions.ObjectNotFoundExceptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@ControllerAdvice
public class ResourcesExceptionsHandlers {

    private final Logger LOG = LoggerFactory.getLogger(ResourcesExceptionsHandlers.class);

    @ExceptionHandler(ObjectNotFoundExceptions.class)
    public ResponseEntity<MensagemErrorPadrao> objectNotFoundExceptions(ObjectNotFoundExceptions e, HttpServletRequest request){

        HttpStatus status = HttpStatus.NOT_FOUND;

        MensagemErrorPadrao padrao = new MensagemErrorPadrao();
        padrao.setStatusHttp(status.toString());
        padrao.setMensagem(e.getMessage());
        padrao.setPath(request.getRequestURI());
        padrao.setData(LocalDateTime.now());

        LOG.error(e.getMessage());
        e.getStackTrace();

        return ResponseEntity.status(status).body(padrao);
    }
}
