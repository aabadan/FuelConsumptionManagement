package com.fuel.management.repository;

import com.fuel.management.ConsumptionStatistic;
import com.fuel.management.MonthlyConsumption;
import com.fuel.management.entity.ConsumptionEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ConsumptionRepository extends CrudRepository<ConsumptionEntity, Integer> {
  @Query(
      nativeQuery = true,
      value =
          "SELECT to_char(date, 'YYYY-MM') as month , round(sum(fuel_price * fuel_volume),2) as totalPrice "
              + "FROM CONSUMPTION group by to_char(date, 'YYYY-MM')")
  List<MonthlyConsumption> findTotalAmountSpentGroupedByMonth();

  @Query(
      nativeQuery = true,
      value =
          "SELECT to_char(date, 'YYYY-MM') as month, round(sum(fuel_price * fuel_volume),2) as totalPrice "
              + "FROM CONSUMPTION WHERE DRIVER_ID = :driverId group by to_char(date, 'YYYY-MM')")
  List<MonthlyConsumption> findTotalAmountSpentGroupedByMonthForDriver(
      @Param("driverId") final String driverId);

  @Query(
      nativeQuery = true,
      value = "SELECT * FROM CONSUMPTION WHERE to_char(date, 'YYYY-MM') = :month")
  List<ConsumptionEntity> findRecordsOnMonth(@Param("month") final String month);

  @Query(
      nativeQuery = true,
      value =
          "SELECT * FROM CONSUMPTION WHERE to_char(date, 'YYYY-MM') = :month AND DRIVER_ID = :driverId")
  List<ConsumptionEntity> findRecordsOnMonthForDriver(
      @Param("driverId") final String driverId, @Param("month") final String month);

  @Query(
      nativeQuery = true,
      value =
          "SELECT to_char(date, 'YYYY-MM') as month ,fuel_type as fuelType,round(sum(fuel_volume),2) as volume, round(avg(fuel_price),2) as averagePrice, "
              + "round(sum(fuel_price * fuel_volume),2) as totalPrice  FROM CONSUMPTION group by to_char(date, 'YYYY-MM'),fuel_type")
  List<ConsumptionStatistic> findStatistics();

  @Query(
      nativeQuery = true,
      value =
          "SELECT to_char(date, 'YYYY-MM') as month ,fuel_type as fuelType,round(sum(fuel_volume),2) as volume, round(avg(fuel_price),2) as averagePrice, "
              + "round(sum(fuel_price * fuel_volume),2) as totalPrice  FROM CONSUMPTION WHERE DRIVER_ID = :driverId group by to_char(date, 'YYYY-MM'),fuel_type")
  List<ConsumptionStatistic> findStatisticsForDriver(@Param("driverId") final String driverId);
}
