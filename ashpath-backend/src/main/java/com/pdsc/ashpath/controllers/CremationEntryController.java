package com.pdsc.ashpath.controllers;

import com.pdsc.ashpath.domain.entity.CremationEntry;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.pdsc.ashpath.domain.dto.request.AddDeceasedToCremationEntryRequest;
import com.pdsc.ashpath.domain.dto.request.CreateCremationEntryRequest;
import com.pdsc.ashpath.domain.service.CremationEntryService;

import lombok.RequiredArgsConstructor;

import java.util.List;

@RestController
@RequestMapping("/cremation_entry")
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
  public ResponseEntity<Void> createCremationEntry(@RequestBody CreateCremationEntryRequest request)
  {
    cremationEntryService.createCremationEntry(request.getNecrotomistId());
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  @PostMapping("/add_deceased")
  public ResponseEntity<Void> addDeceasedToCremationEntry(@RequestBody AddDeceasedToCremationEntryRequest request)
  {
    cremationEntryService.addDeceasedToCremationEntry(request.getCremationEntryId(), request.getDeceasedId());
    return ResponseEntity.status(HttpStatus.OK).build();
  }

  @PostMapping("/execute_cremation/{deceasedId}")
  public ResponseEntity<Void> executeDeceasedCremation(@PathVariable Long deceasedId)
  {
    cremationEntryService.executeCremation(deceasedId);
    return ResponseEntity.status(HttpStatus.OK).build();
  }
}
