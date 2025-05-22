package com.pdsc.ashpath.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pdsc.ashpath.domain.dto.request.CreateCremationQueueRequest;
import com.pdsc.ashpath.domain.service.CremationQueueService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/cremation_queue")
@RequiredArgsConstructor
public class CremationQueueController
{
  private final CremationQueueService cremationQueueService;

  @PostMapping("/create")
  public ResponseEntity<Void> createCremationQueue(@RequestBody CreateCremationQueueRequest request)
  {
    cremationQueueService.createCremationQueue(request.getNecrotomistId());
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }
}
