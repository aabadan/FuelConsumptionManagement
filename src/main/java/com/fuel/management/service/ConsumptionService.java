package com.fuel.management.service;

import com.fuel.management.ConsumptionStatistic;
import com.fuel.management.MonthlyConsumption;
import com.fuel.management.entity.ConsumptionEntity;
import com.fuel.management.repository.ConsumptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsumptionService {

  @Autowired private ConsumptionRepository consumptionRepository;

  public void saveConsumption(final ConsumptionEntity consumptionEntity) {
    consumptionRepository.save(consumptionEntity);
  }

  public void saveConsumptions(final List<ConsumptionEntity> consumptionEntities) {
    consumptionRepository.saveAll(consumptionEntities);
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
