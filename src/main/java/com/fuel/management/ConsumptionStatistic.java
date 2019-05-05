package com.fuel.management;

public interface ConsumptionStatistic {
  String getMonth();

  void setMonth(final String month);

  String getFuelType();

  void setFuelType(final String fuelType);

  String getVolume();

  void setVolume(final String volume);

  String getAveragePrice();

  void setAveragePrice(final String averagePrice);

  String getTotalPrice();

  void setTotalPrice(final String totalPrice);
}
