package com.fuel.management;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
public class FuelConsumptionRegistrationController {

    @Autowired
    private ConsumptionService consumptionService;

    @PostMapping("/consumption")
    public void saveConsumption(@Valid @RequestBody FuelConsumptionRegistry fuelConsumptionRegistry) {
        consumptionService.saveConsumption(fuelConsumptionRegistry);
    }

    @GetMapping("/consumptions")
    public List<ConsumptionEntity> getConsumptions() {
        return consumptionService.getAllConsumptions();
    }

    @GetMapping("/totalByMonth")
    public List<Object[]> getTotalSpentByMonth() {
        return consumptionService.getTotalConsumptionAmountSpentByMonth();
    }

    @GetMapping("/totalByMonth/{driverId}")
    public List<MonthlyConsumption> getTotalSpentByMonthByDriver(@PathVariable final String driverId) {
        return consumptionService.getTotalConsumptionAmountSpentByMonthForDriver(driverId);
    }

    @GetMapping("/reportByMonth")
    public List<Object[]> getConsumptionForEachMonth() {
        return consumptionService.getTotalConsumptionAmountSpentByMonth();
    }
}
