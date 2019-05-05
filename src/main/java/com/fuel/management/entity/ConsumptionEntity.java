package com.fuel.management.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Getter
@Setter
@Builder
@Table(name = "CONSUMPTION")
@NoArgsConstructor
@AllArgsConstructor
public class ConsumptionEntity {

  @Id @GeneratedValue private long id;
  private String fuelType;
  private double fuelPrice;
  private double fuelVolume;
  private Date date;
  private long driverId;
}
