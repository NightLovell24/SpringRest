package com.n0rth.springrest.exceptionhandling;


import com.n0rth.springrest.exceptionhandling.exception.NoSuchEmployeeException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class EmployeeGlobalExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<EmployeeHandler> handleException(NoSuchEmployeeException exception) {

        EmployeeHandler employeeHandler = new EmployeeHandler();
        employeeHandler.setInfo(exception.getMessage());

        return new ResponseEntity<>(employeeHandler, HttpStatus.NOT_FOUND);
    }
}
