package com.fuel.management.controller;

import com.fuel.management.MonthlyConsumption;
import com.fuel.management.entity.ConsumptionEntityConverter;
import com.fuel.management.service.ConsumptionService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.projection.ProjectionFactory;
import org.springframework.data.projection.SpelAwareProxyProjectionFactory;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(FuelConsumptionRegistrationController.class)
public class FuelConsumptionRegistrationControllerUnitTest {

  private ProjectionFactory factory = new SpelAwareProxyProjectionFactory();
  @Autowired private MockMvc mockMvc;
  @MockBean private ConsumptionService consumptionService;
  @MockBean private ConsumptionEntityConverter consumptionEntityConverter;

  @Test
  public void shouldReturnTotalSpentByMonth() throws Exception {
    // given
    List<MonthlyConsumption> monthlyConsumptions = new ArrayList<>();
    final MonthlyConsumption monthlyConsumption =
        factory.createProjection(MonthlyConsumption.class);
    monthlyConsumption.setMonth("2019-JAN");
    monthlyConsumption.setTotalPrice("22.54");
    monthlyConsumptions.add(monthlyConsumption);
    given(consumptionService.getTotalConsumptionAmountSpentByMonth())
        .willReturn(monthlyConsumptions);

    // when
    final MvcResult result =
        mockMvc
            .perform(get("/totalAmountByMonth").contentType(MediaType.APPLICATION_JSON))
            .andReturn();

    // then
    JSONAssert.assertEquals(
        "[{\"totalPrice\":\"22.54\",\"month\":\"2019-JAN\"}]",
        result.getResponse().getContentAsString(),
        false);
  }
}
