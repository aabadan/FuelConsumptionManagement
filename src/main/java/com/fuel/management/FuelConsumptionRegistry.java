package com.fuel.management;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.util.Date;

@Getter
@Setter
public class FuelConsumptionRegistry {

  @NotBlank(message = "Please provide fuel type")
  private String fuelType;

//  @NotBlank(message = "Please provide fuel price")
  private double fuelPrice;

//  @NotBlank(message = "Please provide fuel volume")
  private double fuelVolume;

//  @NotBlank(message = "Please provide date")
  private Date date;

//  @NotBlank(message = "Please provide driver id")
  private long driverId;
}
