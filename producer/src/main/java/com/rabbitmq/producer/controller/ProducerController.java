package com.rabbitmq.producer.controller;

import com.rabbitmq.core.global.dto.TestDto;
import com.rabbitmq.producer.service.ProducerService;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ProducerController {

  private final ProducerService producerService;

  @PostMapping("/send/compute")
  public String testCompute(@RequestBody TestDto testDto){
    System.out.println("ProducerController - testCompute");
    String id = UUID.randomUUID().toString();
    testDto.setId(id);
    return producerService.testCompute(testDto);
  }

  @PostMapping("/send/network")
  public String testNetwork(@RequestBody TestDto testDto){
    System.out.println("ProducerController - testNetwork");
    String id = UUID.randomUUID().toString();
    testDto.setId(id);
    return producerService.testNetwork(testDto);
  }
}
