package com.rabbitmq.core.global.config;

import static com.rabbitmq.core.global.Constants.COMPUTE_EXCHANGE;
import static com.rabbitmq.core.global.Constants.COMPUTE_QUEUE;
import static com.rabbitmq.core.global.Constants.NETWORK_EXCHANGE;
import static com.rabbitmq.core.global.Constants.NETWORK_QUEUE;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfiguration {
  // 지정된 이름으로 queue 등록
  @Bean
  Queue computeQueue() {
    return new Queue(COMPUTE_QUEUE, true, false, false);
  }

  @Bean
  Queue networkQueue() {
    return new Queue(NETWORK_QUEUE, true, false, false);
  }

  @Bean
  public FanoutExchange computeExchange() {
    return new FanoutExchange(COMPUTE_EXCHANGE);
  }

  // 빈으로 등록한 Queue와 Exchange를 바인딩하면서 Exchange에서 사용될 패턴을 설정
  @Bean
  Binding computeinding(FanoutExchange computeExchange, Queue computeQueue) {
    return BindingBuilder.bind(computeQueue).to(computeExchange);
  }
  @Bean
  public FanoutExchange networkExchange() {
    return new FanoutExchange(NETWORK_EXCHANGE);
  }

  // 빈으로 등록한 Queue와 Exchange를 바인딩하면서 Exchange에서 사용될 패턴을 설정
  @Bean
  Binding networkBinding(FanoutExchange networkExchange, Queue networkQueue) {
    return BindingBuilder.bind(networkQueue).to(networkExchange);
  }

  // Spring Boot에서 자동으로 빈 등록을 해주지만 받은 메세지 처리를 위한 messageConverter을 설정하기 위해 오버라이딩
  @Bean
  RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
    RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
//    rabbitTemplate.setMessageConverter(messageConverter);
    return rabbitTemplate;
  }

//  @Bean
//  MessageConverter messageConverter() {
//    return new Jackson2JsonMessageConverter();
//  }

}
