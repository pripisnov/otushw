package ru.otus.framework.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.*;

@Target(value=FIELD)
@Retention(value=RUNTIME)
public @interface Column {
    String name();
}
