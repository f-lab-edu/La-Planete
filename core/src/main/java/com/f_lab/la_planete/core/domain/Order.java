package com.f_lab.la_planete.core.domain;


import com.f_lab.la_planete.core.domain.base.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Builder
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "orders")
public class Order extends BaseEntity {

  @Id
  @GeneratedValue(strategy = IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "food_id")
  private Food food;

  private BigDecimal totalCost;

  private int quantity;

  @Enumerated(EnumType.STRING)
  private OrderStatus status;

  @OneToOne(fetch = LAZY)
  @JoinColumn(name = "payment_id")
  private Payment payment;

  @ManyToOne(fetch = LAZY)
  @JoinColumn(name = "voucher_id")
  private Voucher voucher;

  public BigDecimal calculateTotalCost() {
    return (voucher != null)
        ? voucher.apply(food.calculateCost(quantity), food.getCurrency())
        : food.calculateCost(quantity);
  }
}
