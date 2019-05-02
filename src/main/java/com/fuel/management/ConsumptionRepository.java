package com.fuel.management;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ConsumptionRepository extends CrudRepository<ConsumptionEntity, Integer> {
  @Query(nativeQuery = true, value = "SELECT to_char(date, 'MONTH') , sum(fuel_price)  FROM CONSUMPTION group by to_char(date, 'MONTH')")
  List<String> findTotalAmountSpentOnEachMonth();
}
