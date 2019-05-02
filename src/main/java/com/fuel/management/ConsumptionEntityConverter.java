package com.fuel.management;

import org.springframework.stereotype.Component;

@Component
public class ConsumptionEntityConverter {

  public ConsumptionEntity convert(final FuelConsumptionRegistry fuelConsumptionRegistry) {
    return ConsumptionEntity.builder()
        .fuelType(fuelConsumptionRegistry.getFuelType())
        .fuelPrice(fuelConsumptionRegistry.getFuelPrice())
        .fuelVolume(fuelConsumptionRegistry.getFuelVolume())
        .date(fuelConsumptionRegistry.getDate())
        .driverId(fuelConsumptionRegistry.getDriverId())
        .build();
  }
}
