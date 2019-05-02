package com.fuel.management;

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

  public List<String> getTotalConsumptionAmountSpentByMonth() {
    return consumptionRepository.findTotalAmountSpentOnEachMonth();
  }
}
