package com.fuel.management;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = FuelConsumptionRegistryValidator.class)
public @interface ValidFuelConsumptionRegistry {
  String message() default "{com.valid.fuel.consumption.message}";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}
