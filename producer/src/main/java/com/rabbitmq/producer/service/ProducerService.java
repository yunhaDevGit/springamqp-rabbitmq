package com.rabbitmq.producer.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.rabbitmq.core.global.ActionQueueSender;
import com.rabbitmq.core.global.Constants;
import com.rabbitmq.core.global.Constants.EVENT_CODE;
import com.rabbitmq.core.global.QueueAction;
import com.rabbitmq.core.global.dto.TestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProducerService {


  private final ActionQueueSender actionQueueSender;

  public String testCompute(TestDto testDto) {

      System.out.println("Sending network message...");

      QueueAction queueAction = QueueAction.builder()
          .actionCode(EVENT_CODE.VM_CREATE)
          .actionId("vm_create actionId")
          .dto(testDto)
          .build();

      try {
        actionQueueSender.sendAsync(Constants.COMPUTE_QUEUE, queueAction);
      } catch (JsonProcessingException e) {

      }

      return queueAction.getActionId();
  }

  public String testNetwork(TestDto testDto) {

    System.out.println("Sending network message...");

    QueueAction queueAction = QueueAction.builder()
        .actionCode(EVENT_CODE.VM_CREATE)
        .actionId("vm_create actionId")
        .dto(testDto)
        .build();

    try {
      actionQueueSender.sendAsync(Constants.NETWORK_QUEUE, queueAction);
    } catch (JsonProcessingException e) {

    }

    return queueAction.getActionId();
  }
}
