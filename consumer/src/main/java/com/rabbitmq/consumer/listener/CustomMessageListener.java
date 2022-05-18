package com.rabbitmq.consumer.listener;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.core.global.Constants.EVENT_CODE;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomMessageListener {

  private final ObjectMapper objectMapper;

  @RabbitListener(queues = "spring-boot")
  public void receiveMessage(byte[] msg) throws ParseException {

    String message = new String(msg);

    System.out.println(message);
    JSONParser parser = new JSONParser();
    JSONObject jsonObject = (JSONObject) parser.parse(message);
    EVENT_CODE eventCode = EVENT_CODE.valueOf((String) jsonObject.get("actionCode"));
    System.out.println("<<<< EVENT_CODE : " + eventCode);
  }
}
