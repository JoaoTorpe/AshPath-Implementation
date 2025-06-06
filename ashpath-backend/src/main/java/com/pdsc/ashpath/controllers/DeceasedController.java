package com.pdsc.ashpath.controllers;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.pdsc.ashpath.domain.dto.request.CreateDeceasedRequest;
import com.pdsc.ashpath.domain.dto.response.DeceasedResponse;
import com.pdsc.ashpath.domain.entity.Deceased;
import com.pdsc.ashpath.domain.service.DeceasedService;

@RestController
@RequestMapping("/deceased")
public class DeceasedController
{
  private final DeceasedService deceasedService;

  public DeceasedController(DeceasedService deceasedService)
  {
    this.deceasedService = deceasedService;
  }

  @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  public ResponseEntity<?> createDeceased(
    @RequestPart(name = "deceasedData") CreateDeceasedRequest request,
    @RequestPart(name = "deceasedDeathCertificate") MultipartFile deathCertificateFile
  ){
    try {
      deceasedService.createDeceased(request,deathCertificateFile);
    } catch (IOException e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
    }

    return ResponseEntity.status(HttpStatus.OK).build();
  }

  @GetMapping("/findAllByCremation/{cremationEntryId}")
  public ResponseEntity<List<DeceasedResponse>> findAllByCremationEntryId(@PathVariable Long cremationEntryId )
  {
    if(Objects.isNull(cremationEntryId))
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);

    List<DeceasedResponse> response = deceasedService.findAllByCremationEntryId(cremationEntryId);
    return ResponseEntity.status(HttpStatus.OK).body(response);
  }

  @GetMapping("/findByGraveLocation/{location}")
  public ResponseEntity<List<DeceasedResponse>> findByGraveLocation(@PathVariable String location )
  {
    if(Objects.isNull(location))
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);

    List<DeceasedResponse> response = deceasedService.findByGraveLocation(location);
    return ResponseEntity.status(HttpStatus.OK).body(response);
  }

  @GetMapping("/findByDeathDate/{deathDate}")
  public ResponseEntity<List<DeceasedResponse>> findAllByDeathDate(@PathVariable LocalDate deathDate )
  {
    if(Objects.isNull(deathDate))
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    
    List<DeceasedResponse> response = deceasedService.findAllByDeathDate(deathDate);
    return ResponseEntity.status(HttpStatus.OK).body(response);
  }

  @GetMapping("/{deceasedId}")
  public ResponseEntity<DeceasedResponse> readDeceasedById(@PathVariable Long deceasedId)
  {
    DeceasedResponse response = deceasedService.readDeceasedById(deceasedId);

    return Objects.isNull(response) ?
      ResponseEntity.status(HttpStatus.NOT_FOUND).build() :
      ResponseEntity.status(HttpStatus.OK).body(response);
  }

  @GetMapping("/{deceasedId}/deathCertificate")
  public ResponseEntity<byte[]> readDeceasedDeathCertificate(@PathVariable Long deceasedId)
  {
    Optional <Deceased> opDeceased = deceasedService.findById(deceasedId);

    if (opDeceased.isPresent())
    {
      Deceased deceased = opDeceased.get();
        return ResponseEntity
                .status(HttpStatus.OK)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"DeathCertificate.pdf\"")
                .body(deceased.getDeathCertificate());
    }

    return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
  }
}
