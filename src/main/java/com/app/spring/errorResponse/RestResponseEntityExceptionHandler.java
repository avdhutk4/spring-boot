package com.app.spring.errorResponse;

import com.app.spring.controller.DepartmentController;
import com.app.spring.error.DepartmentNotDeletedSuccessfullyException;
import com.app.spring.error.DepartmentNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice(assignableTypes = {DepartmentController.class})
@ResponseStatus
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {DepartmentNotFoundException.class})
    public ResponseEntity<Map<String,Object>>
    departmentNotFoundExceptionHandler(DepartmentNotFoundException exception,
                                                                           WebRequest request) {

        String uri = request.getDescription(false);
        System.out.println("uri = " + uri);

        String departmentId = uri.substring(uri.lastIndexOf('/') + 1);

        System.out.println(departmentId);

        Map<String,Object> errorResponse = new HashMap<>();
        errorResponse.put("message", exception.getMessage());
        errorResponse.put("status" ,HttpStatus.NOT_FOUND);
        errorResponse.put("departmentId",departmentId);

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(errorResponse);

    }

    @ExceptionHandler(value = {DepartmentNotDeletedSuccessfullyException.class})
    public ResponseEntity<Map<String,Object>> departmentNotDeletedSuccessfullyExceptionHandler(
            DepartmentNotDeletedSuccessfullyException exception,
            WebRequest request
    ){
        String uri = request.getDescription(false);

        String departmentId = uri.substring(uri.lastIndexOf('/') + 1);
        Map<String,Object> errorResponse = new HashMap<>();
        errorResponse.put("status",HttpStatus.BAD_REQUEST);
        errorResponse.put("message",exception.getMessage());
        errorResponse.put("departmentId",departmentId);

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(errorResponse);
    }
}
