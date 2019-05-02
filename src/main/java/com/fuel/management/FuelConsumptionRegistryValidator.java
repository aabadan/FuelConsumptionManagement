package com.fuel.management;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class FuelConsumptionRegistryValidator
    implements ConstraintValidator<ValidFuelConsumptionRegistry, String> {
  @Override
  public void initialize(ValidFuelConsumptionRegistry constraintAnnotation) {}

  @Override
  public boolean isValid(String value, ConstraintValidatorContext context) {
    return false;
  }
}
