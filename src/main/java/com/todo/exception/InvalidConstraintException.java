package com.todo.exception;

public class InvalidConstraintException extends RuntimeException {

    public InvalidConstraintException(String field) {
        super(field);
    }

    public InvalidConstraintException(String field, String value) {
        super(String.format("Invalid Constraint Todo with field=[%s], value=[%s]", field, value));
    }
}
