package br.com.srbit.locadoraapi.exceptions;
import java.time.Instant;

public record ExceptionResponse(Instant timestamp, String message, String details){ }