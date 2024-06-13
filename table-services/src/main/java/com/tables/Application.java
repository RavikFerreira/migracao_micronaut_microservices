package com.tables;

import io.micronaut.core.annotation.Introspected;
import io.micronaut.runtime.Micronaut;
import jakarta.persistence.Entity;

@Introspected(packages = "com.tables.models", includedAnnotations = Entity.class)
public class Application {

    public static void main(String[] args) {
        Micronaut.run(com.tables.Application.class, args);
    }
}