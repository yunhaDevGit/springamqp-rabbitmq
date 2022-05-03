package com.rabbitmq.producer.controller;

import com.rabbitmq.producer.service.ProducerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ProducerController {

  private final ProducerService producerService;

  @GetMapping("/hello/{name}")
  public String sayHello(@PathVariable String name) {
    producerService.sayHello(name);
    return "Hello " + name + "!";
  }
}
