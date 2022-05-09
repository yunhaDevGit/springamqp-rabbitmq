package com.rabbitmq.producer.service;

import com.rabbitmq.core.global.CustomMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProducerService {

  private static final String topicExchange = "spring-boot-exchange";
  private final RabbitTemplate rabbitTemplate;

  public void sayHello(String name) {
    System.out.println("Sending message...");
    CustomMessage message = new CustomMessage("hello message");
    rabbitTemplate.convertAndSend(topicExchange, "foo.bar.baz", message);
  }
}
