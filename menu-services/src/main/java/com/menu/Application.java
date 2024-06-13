package com.menu;

import io.micronaut.core.annotation.Introspected;
import io.micronaut.runtime.Micronaut;
import jakarta.persistence.Entity;

@Introspected(packages = "com.menu.models" , includedAnnotations = Entity.class )
public class Application {

    public static void main(String[] args) {
        Micronaut.run(Application.class, args);
    }
}