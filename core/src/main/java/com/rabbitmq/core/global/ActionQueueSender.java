package com.rabbitmq.core.global;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.nio.charset.StandardCharsets;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

@Service
@Configuration
public class ActionQueueSender {

  private final RabbitTemplate rabbitTemplate;
  private final ObjectMapper objectMapper;

  public void sendAsync(String topicExchange, QueueAction action) throws JsonProcessingException {
    System.out.println("ActionQueueSender - sendAsync");
    System.out.println("SendingMessage=" + action.toString() + ", topic=" + topicExchange);
    String value = this.objectMapper.writeValueAsString(action);
    byte[] msg = value.getBytes(StandardCharsets.UTF_8);
    this.rabbitTemplate.convertAndSend(topicExchange, "foo.bar.baz", msg);
    System.out.println("test");
  }

  public ActionQueueSender(final RabbitTemplate rabbitTemplate,
      final ObjectMapper objectMapper) {
    this.rabbitTemplate = rabbitTemplate;
    this.objectMapper = objectMapper;
  }
}
