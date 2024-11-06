package com.tables.config.exceptions;

public class UnableToDeleteAnOrderFromATable extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public UnableToDeleteAnOrderFromATable(String message) {
        super(message);
    }
}

