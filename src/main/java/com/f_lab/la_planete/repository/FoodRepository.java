package com.f_lab.la_planete.repository;

import com.f_lab.la_planete.domain.Food;
import jakarta.persistence.LockModeType;
import jakarta.persistence.QueryHint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.query.Param;

import static com.f_lab.la_planete.util.time.TimeConstantsString.FIVE_SECONDS;


public interface FoodRepository extends JpaRepository<Food, Long> {

  @Lock(LockModeType.PESSIMISTIC_WRITE)
  @Query("SELECT f FROM Food f WHERE f.id = :id")
  @QueryHints({ @QueryHint(name = "jakarta.persistence.lock.timeout", value = FIVE_SECONDS) })
  Food findFoodByFoodIdWithPessimisticLock(@Param("id") Long id);
}

