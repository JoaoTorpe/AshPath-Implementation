package com.pdsc.ashpath.controllers;

import com.pdsc.ashpath.domain.entity.CremationEntry;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.pdsc.ashpath.domain.dto.request.AddDeceasedToCremationQueueRequest;
import com.pdsc.ashpath.domain.dto.request.CreateCremationQueueRequest;
import com.pdsc.ashpath.domain.service.CremationEntryService;

import lombok.RequiredArgsConstructor;

import java.util.List;

@RestController
@RequestMapping("/cremation_queue")
@RequiredArgsConstructor
public class CremationEntryController
{
  private final CremationEntryService cremationEntryService;

  @GetMapping ("/findAll")
  public ResponseEntity<List<CremationEntry>> findAll(){
    List<CremationEntry> result = cremationEntryService.findAll();

    if (result.isEmpty())
      return ResponseEntity.status(HttpStatus.NO_CONTENT).body(result);

    return ResponseEntity.status(HttpStatus.OK).body(result);
  }

  @PostMapping("/create")
  public ResponseEntity<Void> createCremationQueue(@RequestBody CreateCremationQueueRequest request)
  {
    cremationEntryService.createCremationQueue(request.getNecrotomistId());
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  @PostMapping("/add_deceased")
  public ResponseEntity<Void> addDeceasedToCremationQueue(@RequestBody AddDeceasedToCremationQueueRequest request)
  {
    cremationEntryService.addDeceasedToCremationQueue(request.getCremationQueueId(), request.getDeceasedId());
    return ResponseEntity.status(HttpStatus.OK).build();
  }

  @PostMapping("/execute_cremation/{deceasedId}")
  public ResponseEntity<Void> executeDeceasedCremation(@PathVariable Long deceasedId)
  {
    cremationEntryService.executeCremation(deceasedId);
    return ResponseEntity.status(HttpStatus.OK).build();
  }
}
