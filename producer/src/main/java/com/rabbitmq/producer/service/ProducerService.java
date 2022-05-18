package com.rabbitmq.producer.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.rabbitmq.core.global.ActionQueueSender;
import com.rabbitmq.core.global.Constants.EVENT_CODE;
import com.rabbitmq.core.global.QueueAction;
import com.rabbitmq.core.global.dto.TestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProducerService {

  private static final String topicExchange = "spring-boot-exchange";

  private final ActionQueueSender actionQueueSender;

  public String testQueue(TestDto testDto) {

      System.out.println("Sending message...");

      QueueAction queueAction = QueueAction.builder()
          .actionCode(EVENT_CODE.VM_CREATE)
          .actionId("vm_create actionId")
          .dto(testDto)
          .build();

      try {
        actionQueueSender.sendAsync(topicExchange, queueAction);
      } catch (JsonProcessingException e) {

      }

      return queueAction.getActionId();
  }
}
