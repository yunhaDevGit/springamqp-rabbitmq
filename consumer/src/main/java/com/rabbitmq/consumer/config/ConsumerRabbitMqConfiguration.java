package com.rabbitmq.consumer.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConsumerRabbitMqConfiguration {

  private static final String queueName = "spring-boot";

  private static final String topicExchangeName = "spring-boot-exchange";

  // 지정된 이름으로 queue 등록
  @Bean
  Queue queue() {
    return new Queue(queueName, false);
  }

  // exchange 설정. TopicExchange를 사용해 주어진 패턴과 일치하는 Queue에게 메세지 전달
  @Bean
  TopicExchange exchange() {
    return new TopicExchange(topicExchangeName);
  }

  // 빈으로 등록한 Queue와 Exchange를 바인딩하면서 Exchange에서 사용될 패턴을 설정
  @Bean
  Binding binding(Queue queue, TopicExchange exchange) {
    return BindingBuilder.bind(queue).to(exchange).with("foo.bar.#");
  }

  // Spring Boot에서 자동으로 빈 등록을 해주지만 받은 메세지 처리를 위한 messageConverter을 설정하기 위해 오버라이딩
  @Bean
  RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory,
      MessageConverter messageConverter) {
    RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
    rabbitTemplate.setMessageConverter(messageConverter);
    return rabbitTemplate;
  }

  @Bean
  MessageConverter messageConverter() {
    return new Jackson2JsonMessageConverter();
  }

}
