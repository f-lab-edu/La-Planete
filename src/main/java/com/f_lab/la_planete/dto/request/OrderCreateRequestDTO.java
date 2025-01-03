package com.f_lab.la_planete.dto.request;

import com.f_lab.la_planete.domain.Food;
import com.f_lab.la_planete.domain.Order;
import com.f_lab.la_planete.domain.OrderStatus;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderCreateRequestDTO {

  @JsonProperty("food_id")
  private Long foodId;

  private BigDecimal price;

  private int quantity;

  public Order toEntity(Food food) {
    return Order.builder()
        .status(OrderStatus.READY)
        .food(food)
        .quantity(quantity)
        .build();
  }
}
