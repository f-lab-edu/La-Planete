package com.f_lab.la_planete.orders.service;



import com.f_lab.la_planete.orders.dto.request.OrderCreateRequestDTO;
import com.f_lab.la_planete.orders.dto.response.OrderCreateResponseDTO;
import com.f_lab.la_planete.orders.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {

  private final OrderRepository orderRepository;

  private final KafkaTemplate<String, Object> kafkaTemplate;

  @Value("${foods.commands.topic.name}")
  private String FOOD_COMMAND;

  @Transactional
  public OrderCreateResponseDTO createFoodOrder(OrderCreateRequestDTO request) {
    log.info("request={}", request);
    try {
      kafkaTemplate.send(FOOD_COMMAND, request.toFoodReserveCommand());
    } catch (Exception e) {
      throw new OrderCreatedFailureException(e);
    }

    return new OrderCreateResponseDTO("CREATED");
  }


  static class OrderCreatedFailureException extends RuntimeException {
    public OrderCreatedFailureException() {
    }

    public OrderCreatedFailureException(String message) {
      super(message);
    }

    public OrderCreatedFailureException(String message, Throwable cause) {
      super(message, cause);
    }

    public OrderCreatedFailureException(Throwable cause) {
      super(cause);
    }

    public OrderCreatedFailureException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
      super(message, cause, enableSuppression, writableStackTrace);
    }
  }
}

