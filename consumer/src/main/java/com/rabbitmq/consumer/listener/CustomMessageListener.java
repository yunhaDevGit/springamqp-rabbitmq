package com.rabbitmq.consumer.listener;

import static com.rabbitmq.core.global.Constants.COMPUTE_QUEUE;
import static com.rabbitmq.core.global.Constants.NETWORK_QUEUE;

import com.rabbitmq.core.global.Constants.EVENT_CODE;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@ComponentScan(basePackages = {"com.rabbitmq.core.global","com.rabbitmq.producer"})
public class CustomMessageListener {

  @RabbitListener(queues = COMPUTE_QUEUE)
  public void receiveComputeMessage(byte[] msg) throws ParseException {

    String message = new String(msg);

    System.out.println(message);
    JSONParser parser = new JSONParser();
    JSONObject jsonObject = (JSONObject) parser.parse(message);
    EVENT_CODE eventCode = EVENT_CODE.valueOf((String) jsonObject.get("actionCode"));
    System.out.println("COMPUTE_QUEUE");
    System.out.println("<<<< EVENT_CODE : " + eventCode);
  }

  @RabbitListener(queues = NETWORK_QUEUE)
  public void receiveNetworkMessage(byte[] msg) throws ParseException {

    String message = new String(msg);

    System.out.println(message);
    JSONParser parser = new JSONParser();
    JSONObject jsonObject = (JSONObject) parser.parse(message);
    EVENT_CODE eventCode = EVENT_CODE.valueOf((String) jsonObject.get("actionCode"));
    System.out.println("NETWORK_QUEUE");
    System.out.println("<<<< EVENT_CODE : " + eventCode);
  }
}
