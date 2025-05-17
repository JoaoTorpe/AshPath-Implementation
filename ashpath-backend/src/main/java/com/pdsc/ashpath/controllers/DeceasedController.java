package com.pdsc.ashpath.controllers;

import java.io.IOException;
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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.pdsc.ashpath.domain.entity.Deceased;
import com.pdsc.ashpath.dto.request.CreateDeceasedRequest;
import com.pdsc.ashpath.dto.response.DeceasedResponse;
import com.pdsc.ashpath.repository.DeceasedRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/deceased")
@RequiredArgsConstructor
public class DeceasedController
{

  private final DeceasedRepository deceasedRepository;

  @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  public ResponseEntity<Void> createDeceased(
    @RequestPart(name = "deceasedData") CreateDeceasedRequest request,
    @RequestPart(name = "deceasedDeathCertificate") MultipartFile deathCertificateFile
  ) throws IOException {

    Deceased deceased = new Deceased();

    deceased.setFullname(request.getFullname());
    deceased.setBirthDate(request.getBirthDate());
    deceased.setDeathDate(request.getDeathDate());
    deceased.setCauseOfDeath(request.getCauseOfDeath());
    deceased.setFatherName(request.getFatherName());
    deceased.setMotherName(request.getMotherName());

    deceased.setDeathCertificate(deathCertificateFile.getBytes());

    deceasedRepository.save(deceased);

    return ResponseEntity.status(HttpStatus.OK).build();
  }

  @GetMapping("/{deceasedId}")
  public ResponseEntity<DeceasedResponse> readDeceasedById(@PathVariable Long deceasedId)
  {
    Optional<Deceased> optionalDeceased = deceasedRepository.findById(deceasedId);

    if (optionalDeceased.isPresent())
    {
        Deceased deceased = optionalDeceased.get();

        String server = ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString();
        
        DeceasedResponse deceasedResponse = new DeceasedResponse(deceased);
        deceasedResponse.setDeathCertificateDownloadLink(server +"/deceased/"+ deceased.getId() +"/deathCertificate");

        return ResponseEntity.status(HttpStatus.OK).body(deceasedResponse);
    }

    return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
  }

  @GetMapping("/{deceasedId}/deathCertificate")
  public ResponseEntity<byte[]> readDeceasedDeathCertificate(@PathVariable Long deceasedId)
  {
    Optional<Deceased> optionalDeceased = deceasedRepository.findById(deceasedId);

    if (optionalDeceased.isPresent())
    {
        Deceased deceased = optionalDeceased.get();

        return ResponseEntity
                .status(HttpStatus.OK)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"DeathCertificate.png\"")
                .body(deceased.getDeathCertificate());
    }

    return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
  }

}
