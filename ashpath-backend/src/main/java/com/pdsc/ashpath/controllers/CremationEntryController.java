package com.pdsc.ashpath.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pdsc.ashpath.domain.dto.request.AddDeceasedToCremationEntryRequest;
import com.pdsc.ashpath.domain.dto.request.CreateCremationEntryRequest;
import com.pdsc.ashpath.domain.dto.response.CremationEntryResponse;
import com.pdsc.ashpath.domain.service.CremationEntryService;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/cremationEntry")
public class CremationEntryController
{
  private final CremationEntryService cremationEntryService;

  public CremationEntryController(CremationEntryService cremationEntryService)
  {
    this.cremationEntryService = cremationEntryService;
  }

  @GetMapping ("/findAll")
  public ResponseEntity<List<CremationEntryResponse>> findAll()
  {
    List<CremationEntryResponse> response = cremationEntryService.findAll();

    if (response.isEmpty())
      return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);

    return ResponseEntity.status(HttpStatus.OK).body(response);
  }

  @GetMapping("/find/{id}")
  public ResponseEntity<?> findById(@PathVariable("id") Long id){
    try {
    return ResponseEntity.status(HttpStatus.OK).body(cremationEntryService.findById(id));
    }
    catch (EntityNotFoundException e){
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Entity with the specified ID was not found");
    }
  }

  @PostMapping("/create")
  public ResponseEntity<Void> createCremationEntry(@Valid @RequestBody CreateCremationEntryRequest request)
  {
    cremationEntryService.createCremationEntry(request.getNecrotomistId());
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  @PostMapping("/add_deceased")
  public ResponseEntity<Void> addDeceasedToCremationEntry(@Valid @RequestBody AddDeceasedToCremationEntryRequest request)
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
