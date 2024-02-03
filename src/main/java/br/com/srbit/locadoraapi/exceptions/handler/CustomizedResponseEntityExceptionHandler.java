package br.com.srbit.locadoraapi.exceptions.handler;

import br.com.srbit.locadoraapi.exceptions.DataBaseException;
import br.com.srbit.locadoraapi.exceptions.ExceptionResponse;
import br.com.srbit.locadoraapi.exceptions.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.Instant;

@RestController
@ControllerAdvice
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(NotFoundException.class)
    public final ResponseEntity<ExceptionResponse> handleNotFoundException(Exception ex, WebRequest request) {
        var exceptionResponse = new ExceptionResponse(Instant.now(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DataBaseException.class)
    public final ResponseEntity<ExceptionResponse> dataBaseIntegrityViolarion(Exception ex, WebRequest request){
        var exceptionResponse = new ExceptionResponse(Instant.now(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }
}
