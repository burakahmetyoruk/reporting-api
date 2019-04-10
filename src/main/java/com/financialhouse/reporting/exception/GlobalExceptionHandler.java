package com.financialhouse.reporting.exception;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InvalidAuthenticationException.class)
    public void handleInvalidAuthenticationException(HttpServletResponse res, InvalidAuthenticationException ex) throws IOException {
        res.sendError(HttpStatus.UNAUTHORIZED.value(), ex.getMessage());
    }

    @ExceptionHandler(UserAlreadyExistException.class)
    public void handleUserAlreadyExistException(HttpServletResponse res, UserAlreadyExistException ex) throws IOException {
        res.sendError(HttpStatus.CONFLICT.value(), ex.getMessage());
    }

    @ExceptionHandler(TransactionNotFoundException.class)
    public void handleTransactionNotFoundException(HttpServletResponse res, TransactionNotFoundException ex) throws IOException {
        res.sendError(HttpStatus.NOT_FOUND.value(), ex.getMessage());
    }

    @ExceptionHandler(AccessDeniedException.class)
    public void handleAccessDeniedException(HttpServletResponse res) throws IOException {
        res.sendError(HttpStatus.FORBIDDEN.value(), "Access denied");
    }

    @ExceptionHandler(InvalidRequestException.class)
    public void handleInvalidRequestException(HttpServletResponse res, InvalidRequestException ex) throws IOException {
        res.sendError(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public void handleException(HttpServletResponse res, Exception ex) throws IOException {
        res.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(), "An exception occurred");
    }
}