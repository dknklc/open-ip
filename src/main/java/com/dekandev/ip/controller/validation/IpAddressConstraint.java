package com.dekandev.ip.controller.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Constraint(validatedBy = {IpAddressValidator.class})
@Target({ METHOD, FIELD, PARAMETER })
@Retention(RUNTIME)
public @interface IpAddressConstraint {

    String message() default "Invalid IP Address";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
