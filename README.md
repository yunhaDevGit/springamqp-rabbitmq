# Spring AMQP (RabbitMQ) 예제

### Spring Boot Project 생성

프로젝트 생성 시 아래의 의존성을 추가해줍니다.

![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/8596ec29-6a37-43f0-a7bb-08dc874ac607/Untitled.png)

그 외에도 필요한 의존성들을 추가해주면 아래 항목들이 추가됩니다.

```bash
dependencies {

    implementation 'org.springframework.boot:spring-boot-starter-amqp'
    implementation 'org.springframework.boot:spring-boot-starter-web'
    compileOnly 'org.projectlombok:lombok'
    developmentOnly 'org.springframework.boot:spring-boot-devtools'
    annotationProcessor 'org.projectlombok:lombok'
    testImplementation 'org.springframework.amqp:spring-rabbit-test'
}
```

### Message Receiver 생성

메세징 기반 애플리케이션에서는 publish 된 메세지를 받을 receiver가 필요합니다.

아래와 같이 Receiver 클래스를 생성해줍니다.

```java
import java.util.concurrent.CountDownLatch;
import org.springframework.stereotype.Component;

@Component
public class Receiver {

  private CountDownLatch countDownLatch = new CountDownLatch(1);

  public void receiveMessage(String message) {
    System.out.println("Received <" + message + ">");
    countDownLatch.countDown();
  }

  public CountDownLatch getCountDownLatch() {
    return countDownLatch;
  }
}
```

`Receiver`는 메세지 수신을 위한 메서드가 정의되어 있는 POJO입니다.

> 해당 POJO는 `CountDownLatch`를 가지고 있는데 이는 메세지를 수신 되었음을 알려줍니다.
> 

### Listener 등록 및 Send Message

Spring AMQP의 `RabbitTemplate`은 RabbitMQ와 메세지를 주고 받기 위한 모든 것들을 제공해줍니다. 

- Message Listener Container 설정
- Queue, Exchange 선언과 Queue, Exchange 바인딩
- 리스너를 테스트하기 위한 메세지를 보낼 구성 요소 설정

> Spring Boot는 자동으로 connection factory와 RabbitTemplate을 생성합니다.
> 

메세지를 전송하기 위해 `RabbitTemplate`을 사용하고, 메세지를 수신하기 위해 Message Listener Container와 함께 `Receiver`를 등록할 것입니다. 

Connection Factory는 이 둘을 구동하여 RabbitMQ 서버에 연결해줍니다. 

아래는 SpringBoot의 Container class입니다.

```java
@SpringBootApplication
public class MessagingRabbitMqApplication {

  static final String topicExchangeName = "spring-boot-exchange";

  static final String queueName = "spring-boot";

  @Bean
  Queue queue() {
    return new Queue(queueName, false);
  }

  @Bean
  TopicExchange exchange() {
    return new TopicExchange(topicExchangeName);
  }

  @Bean
  Binding binding(Queue queue, TopicExchange exchange) {
    return BindingBuilder.bind(queue).to(exchange).with("foo.bar.#");
  }

  @Bean
  SimpleMessageListenerContainer container(ConnectionFactory connectionFactory,
      MessageListenerAdapter listenerAdapter) {
    SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
    container.setConnectionFactory(connectionFactory);
    container.setQueueNames(queueName);
    container.setMessageListener(listenerAdapter);
    return container;
  }

  @Bean
  MessageListenerAdapter listenerAdapter(Receiver receiver) {
    return new MessageListenerAdapter(receiver, "receiveMessage");
  }

  public static void main(String[] args) throws InterruptedException {
    SpringApplication.run(MessagingRabbitMqApplication.class, args).close();
  }
}
```

[https://spring.io/guides/gs/messaging-rabbitmq/](https://spring.io/guides/gs/messaging-rabbitmq/)

[https://dev-gorany.tistory.com/324](https://dev-gorany.tistory.com/324)

[https://velog.io/@hellozin/Spring-Boot와-RabbitMQ-초간단-설명서](https://velog.io/@hellozin/Spring-Boot%EC%99%80-RabbitMQ-%EC%B4%88%EA%B0%84%EB%8B%A8-%EC%84%A4%EB%AA%85%EC%84%9C)
