package com.fuel.management.service;

import com.fuel.management.ConsumptionStatistic;
import com.fuel.management.FuelConsumptionRegistry;
import com.fuel.management.MonthlyConsumption;
import com.fuel.management.entity.ConsumptionEntity;
import com.fuel.management.entity.ConsumptionEntityConverter;
import com.fuel.management.repository.ConsumptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ConsumptionService {

  @Autowired private ConsumptionRepository consumptionRepository;

  @Autowired private ConsumptionEntityConverter consumptionEntityConverter;

  public List<ConsumptionEntity> getAllConsumptions() {
    final List<ConsumptionEntity> consumptionEntityList = new ArrayList<>();
    consumptionRepository.findAll().forEach(consumptionEntityList::add);
    return consumptionEntityList;
  }

  public void saveConsumption(final FuelConsumptionRegistry fuelConsumptionRegistry) {

    consumptionRepository.save(consumptionEntityConverter.convert(fuelConsumptionRegistry));
  }

  public void saveConsumptions(final List<FuelConsumptionRegistry> fuelConsumptionRegistries) {
    fuelConsumptionRegistries.forEach(
        fuelConsumptionRegistry ->
            consumptionRepository.save(
                consumptionEntityConverter.convert(fuelConsumptionRegistry)));
  }

  public List<MonthlyConsumption> getTotalConsumptionAmountSpentByMonth() {
    return consumptionRepository.findTotalAmountSpentGroupedByMonth();
  }

  public List<MonthlyConsumption> getTotalConsumptionAmountSpentByMonthForDriver(
      final String driverId) {
    return consumptionRepository.findTotalAmountSpentGroupedByMonthForDriver(driverId);
  }

  public List<ConsumptionEntity> getConsumptionRecordsForMonth(final String month) {
    return consumptionRepository.findRecordsOnMonth(month);
  }

  public List<ConsumptionEntity> getConsumptionRecordsForMonthAndDriver(
      final String driverId, final String month) {
    return consumptionRepository.findRecordsOnMonthForDriver(driverId, month);
  }

  public List<ConsumptionStatistic> getConsumptionStatistics() {
    return consumptionRepository.findStatistics();
  }

  public List<ConsumptionStatistic> getConsumptionStatisticsForDriver(final String driverId) {
    return consumptionRepository.findStatisticsForDriver(driverId);
  }
}
