package com.fuel.management.controller;

import com.fuel.management.ConsumptionStatistic;
import com.fuel.management.FuelConsumptionRegistry;
import com.fuel.management.MonthlyConsumption;
import com.fuel.management.entity.ConsumptionEntity;
import com.fuel.management.entity.ConsumptionEntityConverter;
import com.fuel.management.service.ConsumptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Validated
@RestController
public class FuelConsumptionRegistrationController {

  @Autowired private ConsumptionService consumptionService;

  @Autowired private ConsumptionEntityConverter consumptionEntityConverter;

  @PostMapping("/consumption")
  public void saveConsumption(@RequestBody @Valid FuelConsumptionRegistry fuelConsumptionRegistry) {
    final ConsumptionEntity consumption =
        consumptionEntityConverter.convert(fuelConsumptionRegistry);
    consumptionService.saveConsumption(consumption);
  }

  @PostMapping("/consumptions")
  public void saveConsumptions(
      @RequestBody @Valid List<FuelConsumptionRegistry> fuelConsumptionRegistries) {
    final List<ConsumptionEntity> consumptions = new ArrayList<>();
    fuelConsumptionRegistries.forEach(
        fuelConsumptionRegistry ->
            consumptions.add(consumptionEntityConverter.convert(fuelConsumptionRegistry)));
    consumptionService.saveConsumptions(consumptions);
  }

  @GetMapping("/totalAmountByMonth")
  public List<MonthlyConsumption> getTotalSpentByMonth() {
    return consumptionService.getTotalConsumptionAmountSpentByMonth();
  }

  @GetMapping("/totalAmountByMonth/{driverId}")
  public List<MonthlyConsumption> getTotalSpentByMonthByDriver(
      @PathVariable final String driverId) {
    return consumptionService.getTotalConsumptionAmountSpentByMonthForDriver(driverId);
  }

  @GetMapping("/recordsForMonth")
  public List<ConsumptionEntity> getConsumptionForMonth(@RequestParam("month") String month) {
    return consumptionService.getConsumptionRecordsForMonth(month);
  }

  @GetMapping("/recordsForMonth/{driverId}")
  public List<ConsumptionEntity> getConsumptionForEachMonthByDriver(
      @PathVariable final String driverId, @RequestParam("month") String month) {
    return consumptionService.getConsumptionRecordsForMonthAndDriver(month, driverId);
  }

  @GetMapping("/statistics")
  public List<ConsumptionStatistic> getStatistics() {
    return consumptionService.getConsumptionStatistics();
  }

  @GetMapping("/statistics/{driverId}")
  public List<ConsumptionStatistic> getStatisticsForDriver(@PathVariable final String driverId) {
    return consumptionService.getConsumptionStatisticsForDriver(driverId);
  }
}
