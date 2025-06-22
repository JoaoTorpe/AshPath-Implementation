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

import com.pdsc.ashpath.domain.dto.request.CreateGraveRequest;
import com.pdsc.ashpath.domain.dto.response.GraveResponse;
import com.pdsc.ashpath.domain.service.GraveService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/grave")
public class GraveController
{
  private final GraveService graveService;

  public GraveController(GraveService graveService)
  {
    this.graveService = graveService;
  }

  @PostMapping
  public ResponseEntity<Void> createGrave(@Valid @RequestBody CreateGraveRequest request)
  {
    this.graveService.createGrave(request);
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  @GetMapping
  public ResponseEntity<List<GraveResponse>> readAllGraves()
  {
    List<GraveResponse> response = this.graveService.readAllGraves();
    return ResponseEntity.status(HttpStatus.OK).body(response);
  }

  @GetMapping("/{graveId}")
  public ResponseEntity<GraveResponse> readGraveById(@PathVariable Long graveId)
  {
    GraveResponse response = this.graveService.readGraveById(graveId);
    return ResponseEntity.status(HttpStatus.OK).body(response);
  }

  @PostMapping("/buryIn/{graveId}/deceased/{deceasedId}")
  public ResponseEntity<Void> buryDeceased(@PathVariable Long graveId, @PathVariable Long deceasedId)
  {
    this.graveService.buryDeceased(graveId, deceasedId);
    return ResponseEntity.status(HttpStatus.OK).build();
  }
}
