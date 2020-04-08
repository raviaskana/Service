package org.service.controller;

import org.service.data.ErrorMessage;
import org.service.exception.InvalidPhoneNumException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler({InvalidPhoneNumException.class})
    public ResponseEntity<Object> invalidPhoneNum(Exception exception, WebRequest webRequest){
        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setMessage("Invalid Phone Number");
        errorMessage.setStatusCode(HttpStatus.NOT_ACCEPTABLE.value());
        return new ResponseEntity<Object>(errorMessage,new HttpHeaders(), HttpStatus.NOT_ACCEPTABLE);
    }

}
