package com.app.spring.error;

public class DepartmentNotDeletedSuccessfullyException extends RuntimeException{
    public DepartmentNotDeletedSuccessfullyException() {
    }

    public DepartmentNotDeletedSuccessfullyException(String message) {
        super(message);
    }

    public DepartmentNotDeletedSuccessfullyException(String message, Throwable cause) {
        super(message, cause);
    }

    public DepartmentNotDeletedSuccessfullyException(Throwable cause) {
        super(cause);
    }

    public DepartmentNotDeletedSuccessfullyException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
