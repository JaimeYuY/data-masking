package com.ocean.angel.tool.annotation;

import com.ocean.angel.tool.constatnt.SensitiveType;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface MaskField {

    SensitiveType value();

    MaskCardType cardRef() default @MaskCardType();
}
