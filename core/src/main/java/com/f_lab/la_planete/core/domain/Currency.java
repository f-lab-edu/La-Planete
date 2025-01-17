package com.f_lab.la_planete.core.domain;
import com.f_lab.la_planete.core.domain.base.BaseTimeEntity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.RoundingMode;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "currency")
public class Currency extends BaseTimeEntity {

  @Id @GeneratedValue(strategy = IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String currencyCode;

  @Column(nullable = false)
  private String currencySymbol;

  @Column(nullable = false)
  private int roundingScale;

  @Enumerated(EnumType.STRING)
  @Column(columnDefinition = "VARCHAR(20) NOT NULL DEFAULT 'FLOOR'")
  private RoundingMode roundingMode;
}
