package com.pdsc.ashpath.controllers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pdsc.ashpath.domain.entity.Deceased;
import com.pdsc.ashpath.dto.request.createDeceasedRequest;
import com.pdsc.ashpath.dto.response.DeceasedResponse;
import com.pdsc.ashpath.repository.DeceasedRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/deceased")
@RequiredArgsConstructor
public class DeceasedController
{
    private final DeceasedRepository deceasedRepository;

    @PostMapping
    public ResponseEntity<Void> createDeceased(@RequestBody createDeceasedRequest request)
    {
        Deceased deceased = new Deceased();

        deceased.setFullname(request.getFullname());
        deceased.setCauseOfDeath(request.getCauseOfDeath());
        deceased.setBirthDate(request.getBirthDate());
        deceased.setDeathDate(request.getDeathDate());

        deceasedRepository.save(deceased);

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/{deceasedId}")
    public ResponseEntity<DeceasedResponse> readDeceasedById(@PathVariable Long deceasedId)
    {
        Optional<Deceased> deceasedOptional = deceasedRepository.findById(deceasedId);

        if (deceasedOptional.isPresent())
        {
            Deceased deceased = deceasedOptional.get();
            DeceasedResponse response = new DeceasedResponse(deceased);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    // You could also use this solution in the signature method: 
    // @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start –   

    @GetMapping
    public ResponseEntity<List<Deceased>> readAllDeceaseds(
        @RequestParam(name = "startDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,
        @RequestParam(name = "endDate"  , required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end
    ) {
        List<Deceased> deceaseds = deceasedRepository.findAll();

        
        if (start != null)
        {
            deceaseds = deceaseds
                            .stream()
                            .filter(deceased -> deceased.getDeathDate().isAfter(start))
                            .collect(Collectors.toList());
        }
        
        if (end != null)
        {
            deceaseds = deceaseds
                            .stream()
                            .filter(deceased -> deceased.getDeathDate().isBefore(end))
                            .collect(Collectors.toList());
        }

        return ResponseEntity.status(HttpStatus.OK).body(deceaseds);
    }
}
