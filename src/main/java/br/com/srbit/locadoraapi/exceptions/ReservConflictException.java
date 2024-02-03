package br.com.srbit.locadoraapi.exceptions;

public class ReservConflictException extends RuntimeException{
    public ReservConflictException(String message) {
        super(message);
    }
}
