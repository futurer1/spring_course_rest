package com.mikhail.spring.rest.exception_handling;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class EmployeeGlobalExceptionHandler {

    @ExceptionHandler // ловит все исключения типа NoSuchEmployeeException
    public ResponseEntity<EmployeeIncorrectData> /*Объект класса EmployeeIncorrectData будет преобразован в JSON*/ handleException(
            NoSuchEmployeeException exception // метод будет реагировать на этот тип Exception
    ) {
        EmployeeIncorrectData data = new EmployeeIncorrectData();
        data.setInfo(exception.getMessage());

        return new ResponseEntity<>(data, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<EmployeeIncorrectData> handleException(
            Exception exception
    ) {
        EmployeeIncorrectData data = new EmployeeIncorrectData();
        data.setInfo(exception.getMessage());

        return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
    }

}
