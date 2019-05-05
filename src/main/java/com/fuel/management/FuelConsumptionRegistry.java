package com.fuel.management;

import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
@Valid
public class FuelConsumptionRegistry {

  @NotEmpty(message = "Please provide fuel type")
  private String fuelType;

  @NotNull(message = "Please provide fuel price")
  @DecimalMin("0.01")
  private double fuelPrice;

  @NotNull(message = "Please provide fuel volume")
  @DecimalMin("0.01")
  private double fuelVolume;

  @NotNull(message = "Please provide date")
  private Date date;

  @NotNull(message = "Please provide driver id")
  private long driverId;
}
