package com.example.demoSpringboot.exception;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class HandleException {
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public Map<String, String> handleValidationException(MethodArgumentNotValidException ex) {
//        List<String> list = new ArrayList<>();
//        Map<String, String> errors = new HashMap<>();
//        ex.getBindingResult().getAllErrors().forEach((error) -> {
//            String fieldName = ((FieldError) error).getField();
//            String errorMessage = error.getDefaultMessage();
//            errors.put(fieldName, errorMessage);
//        });
//        list.add(String.valueOf(errors));
//        System.out.println(list);
//        System.out.println(errors);
//        return errors;
//    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, List> handleValidationException(MethodArgumentNotValidException ex) {
        List<Map> listError = new ArrayList<>();
        Map<String, List> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            Map<String, String> errorsDetail = new HashMap<>();
            String fieldName = ((FieldError) error).getField();
            errorsDetail.put("field", fieldName);
            String errorMessage = error.getDefaultMessage();
            errorsDetail.put("message", errorMessage);
            listError.add(errorsDetail);
//            System.out.println(listError);
        });
        errors.put("errors", listError);
//        System.out.println(errors);
        return errors;
    }
}
