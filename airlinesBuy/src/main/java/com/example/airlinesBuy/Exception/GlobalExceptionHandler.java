package com.example.airlinesBuy.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(FailedToFetchUserException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ErrorResponse handleFailedToFetchUserException(Exception exc, HttpServletResponse response) {
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        return new ErrorResponse("Authentication error: " + exc.getMessage());
    }


    @ExceptionHandler({OrderNotFoundException.class, StationNotFoundException.class})
    @ResponseBody
    public ErrorResponse handleNotFoundException(Exception exc, HttpServletResponse response) {
        response.setStatus(HttpStatus.NOT_FOUND.value());
        return new ErrorResponse("Resource not found");
    }

    private static class ErrorResponse {
        private String message;

        public ErrorResponse(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
}

