package com.pdsc.ashpath.domain.service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.hibernate.service.spi.ServiceException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.pdsc.ashpath.domain.dto.request.CreateDeceasedRequest;
import com.pdsc.ashpath.domain.dto.response.DeceasedResponse;
import com.pdsc.ashpath.domain.entity.Deceased;
import com.pdsc.ashpath.repository.DeceasedRepository;

@Service
public class DeceasedService
{
  private final DeceasedRepository deceasedRepository;
  private final CremationEntryService cremationEntryService;
  private final GraveService graveService;

  public DeceasedService(DeceasedRepository deceasedRepository, CremationEntryService cremationEntryService, GraveService graveService)
  {
    this.deceasedRepository = deceasedRepository;
    this.cremationEntryService = cremationEntryService;
    this.graveService = graveService;
  }

  public void createDeceased(CreateDeceasedRequest request, MultipartFile certificate) throws IOException
  {
    Deceased deceased = new Deceased();

    deceased.setFullname(request.getFullname());
    deceased.setBirthDate(request.getBirthDate());
    deceased.setDeathDate(request.getDeathDate());
    deceased.setCauseOfDeath(request.getCauseOfDeath());
    deceased.setFatherName(request.getFatherName());
    deceased.setMotherName(request.getMotherName());
    deceased.setDeathCertificate(certificate.getBytes());

    Deceased createdDeceased = deceasedRepository.save(deceased);

    if (request.getCremationEntryId() != null)
    {
      this.cremationEntryService.addDeceasedToCremationEntry(request.getCremationEntryId(), createdDeceased.getId());
    }

    if (request.getGraveID() != null)
    {
      this.graveService.buryDeceased(request.getGraveID(), createdDeceased.getId());
    }
  }

  public DeceasedResponse readDeceasedById(Long deceasedId)
  {
    Optional<Deceased> optionalDeceased = deceasedRepository.findById(deceasedId);

    if (optionalDeceased.isPresent())
    {
      Deceased deceased = optionalDeceased.get();
      return generateDeceasedResponse(deceased);
    }

    return null;
  }

  public List<DeceasedResponse> findAllByCremationEntryId(Long cremationId)
  {
    List<Deceased> deceaseds = deceasedRepository.findAllDeceasedByQueueId(cremationId);

      return deceaseds.stream()
        .<DeceasedResponse>map(this::generateDeceasedResponse)
        .collect(Collectors.toList());
  }

  public List<DeceasedResponse> findByGraveLocation(String graveLocation)
  {
    List<Deceased> deceaseds = deceasedRepository.findAllDeceasedByGraveLocation(graveLocation);

      return deceaseds.stream()
        .<DeceasedResponse>map(this::generateDeceasedResponse)
        .collect(Collectors.toList());
  }

  public List<DeceasedResponse> findAllByDeathDate(LocalDate deathDate)
  {
    List<Deceased> deceaseds = deceasedRepository.findAllDeceasedByDeathDate(deathDate);
    return deceaseds.stream()
      .<DeceasedResponse>map(this::generateDeceasedResponse)
      .collect(Collectors.toList());
  }

  public Optional <Deceased> findById(Long id )
  {
    return deceasedRepository.findById(id);
  }

  private DeceasedResponse generateDeceasedResponse(Deceased deceased) {
    if (deceased == null) {
      return null;
    }

    DeceasedResponse response = new DeceasedResponse();
    response.setId(deceased.getId());
    response.setFullname(deceased.getFullname());
    response.setBirthDate(deceased.getBirthDate());
    response.setDeathDate(deceased.getDeathDate());
    response.setCauseOfDeath(deceased.getCauseOfDeath());
    response.setFatherName(deceased.getFatherName());
    response.setMotherName(deceased.getMotherName());
    response.setStatus(deceased.getStatus());
    response.setCremationEnteredDate(deceased.getCremationEnteredDate());

    // Tratamento de relacionamentos
    if (deceased.getCremationEntry() != null) {
      response.setCremationEntryId(deceased.getCremationEntry().getId());
    }

    if (deceased.getGrave() != null) {
      response.setGraveLocation(deceased.getGrave().getLocation());
    }

    // URL do certificado
    String serverUrl = ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString();
    response.setDeathCertificateDownloadLink(serverUrl + "/deceased/" + deceased.getId() + "/deathCertificate");

    return response;
  }

  public List<DeceasedResponse> findAll() {
    try {
      List<Deceased> deceasedEntities = deceasedRepository.findAll();
      List<DeceasedResponse> responseList = new ArrayList<>(deceasedEntities.size());

      for (Deceased deceased : deceasedEntities) {
        DeceasedResponse response = generateDeceasedResponse(deceased);
        if (response != null) {
          responseList.add(response);
        }
      }

      return responseList;
    } catch (Exception e) {
      throw new ServiceException("Não foi possível listar os falecidos", e);
    }
  }


}
