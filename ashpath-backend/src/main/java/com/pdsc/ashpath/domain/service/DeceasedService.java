package com.pdsc.ashpath.domain.service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

  public DeceasedService(DeceasedRepository deceasedRepository)
  {
    this.deceasedRepository = deceasedRepository;
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

    deceasedRepository.save(deceased);
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

  private DeceasedResponse generateDeceasedResponse(Deceased deceased){
    String server = ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString();
    DeceasedResponse deceasedResponse = new DeceasedResponse(deceased);
    deceasedResponse.setDeathCertificateDownloadLink(server +"/deceased/"+ deceased.getId() +"/deathCertificate");
    return deceasedResponse;
  }
}
