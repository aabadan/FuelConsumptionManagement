package com.fuel.management.repository;

import com.fuel.management.ConsumptionStatistic;
import com.fuel.management.MonthlyConsumption;
import com.fuel.management.entity.ConsumptionEntity;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.text.SimpleDateFormat;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@DataJpaTest
public class ConsumptionRepositoryIntegrationTest {

  @Autowired private TestEntityManager entityManager;

  @Autowired private ConsumptionRepository consumptionRepository;

  @Before
  public void setUp() throws Exception {
    final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    entityManager.clear();
    final ConsumptionEntity consumptionEntity =
        ConsumptionEntity.builder()
            .fuelType("95")
            .fuelPrice(3.5)
            .fuelVolume(4)
            .date(sdf.parse("2019-03-18"))
            .driverId(222L)
            .build();
    entityManager.persistAndFlush(consumptionEntity);
    final ConsumptionEntity consumptionEntity1 =
        ConsumptionEntity.builder()
            .fuelType("98")
            .fuelPrice(6)
            .fuelVolume(12)
            .date(sdf.parse("2019-04-21"))
            .driverId(222L)
            .build();
    entityManager.persistAndFlush(consumptionEntity1);
    final ConsumptionEntity consumptionEntity2 =
        ConsumptionEntity.builder()
            .fuelType("98")
            .fuelPrice(7)
            .fuelVolume(14)
            .date(sdf.parse("2019-04-18"))
            .driverId(111L)
            .build();
    entityManager.persistAndFlush(consumptionEntity2);
  }

  @Test
  public void shouldFindTotalAmountSpentGroupedByMonth() {
    // when
    List<MonthlyConsumption> totalAmountSpentGroupedByMonth =
        consumptionRepository.findTotalAmountSpentGroupedByMonth();

    // then
    assertThat(totalAmountSpentGroupedByMonth.get(0).getTotalPrice()).isEqualTo("14.0");
  }

  @Test
  public void shouldFindTotalAmountSpentGroupedByMonthForDriver() {
    // when
    List<MonthlyConsumption> totalAmountSpentGroupedByMonthForDriver =
        consumptionRepository.findTotalAmountSpentGroupedByMonthForDriver("111");

    // then
    assertThat(totalAmountSpentGroupedByMonthForDriver.get(0).getTotalPrice()).isEqualTo("98.0");
  }

  @Test
  public void shouldFindRecordsOnSpecifiedMonth() {
    // when
    List<ConsumptionEntity> recordsOnMonth = consumptionRepository.findRecordsOnMonth("2019-03");

    // then
    assertThat(recordsOnMonth.get(0).getFuelType()).isEqualTo("95");
    assertThat(recordsOnMonth.get(0).getFuelVolume()).isEqualTo(4.0);
  }

  @Test
  public void shouldFindStatistics() {
    // when
    List<ConsumptionStatistic> statistics = consumptionRepository.findStatistics();

    // then
    assertThat(statistics.get(1).getMonth()).isEqualTo("2019-04");
    assertThat(statistics.get(1).getAveragePrice()).isEqualTo("6.5");
  }
}
