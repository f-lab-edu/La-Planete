package com.f_lab.la_planete.foods.repository;

import com.f_lab.la_planete.core.aspect.RetryOnLockFailure;
import com.f_lab.la_planete.core.domain.Food;
import jakarta.persistence.LockModeType;
import jakarta.persistence.QueryHint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.query.Param;

import static com.f_lab.la_planete.core.util.time.TimeConstantsString.FIVE_SECONDS;


public interface FoodRepository extends JpaRepository<Food, Long> {

  @RetryOnLockFailure
  @Lock(LockModeType.PESSIMISTIC_WRITE)
  @Query("SELECT f FROM Food f WHERE f.id = :id")
  @QueryHints({ @QueryHint(name = "jakarta.persistence.lock.timeout", value = FIVE_SECONDS) })
  Food findFoodByFoodIdWithPessimisticLock(@Param("id") Long id);
}
