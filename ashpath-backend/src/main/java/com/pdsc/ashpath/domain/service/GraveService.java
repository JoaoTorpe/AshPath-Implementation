package com.pdsc.ashpath.domain.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.pdsc.ashpath.domain.dto.request.CreateGraveRequest;
import com.pdsc.ashpath.domain.dto.response.GraveResponse;
import com.pdsc.ashpath.domain.entity.Deceased;
import com.pdsc.ashpath.domain.entity.Grave;
import com.pdsc.ashpath.domain.enums.DeceasedStatus;
import com.pdsc.ashpath.repository.DeceasedRepository;
import com.pdsc.ashpath.repository.GraveRepository;

@Service
public class GraveService
{
  private final GraveRepository graveRepository;
  private final DeceasedRepository deceasedRepository;

  public GraveService(GraveRepository graveRepository, DeceasedRepository deceasedRepository)
  {
    this.graveRepository = graveRepository;
    this.deceasedRepository = deceasedRepository;
  }

  public void createGrave(CreateGraveRequest createGraveRequest)
  {
    Grave grave = new Grave();
    grave.setLocation(createGraveRequest.getLocation());

    this.graveRepository.save(grave);
  }

  public List<GraveResponse> readAllGraves()
  {
    List<Grave> graves = this.graveRepository.findAll();
    List<GraveResponse> response = graves.stream()
      .<GraveResponse>map(grave -> new GraveResponse(grave))
      .collect(Collectors.toList());
    
    return response;
  }

  public GraveResponse readGraveById(Long graveId)
  {
    Optional<Grave> optionalGrave = this.graveRepository.findById(graveId);

    if (optionalGrave.isPresent())
    {
      GraveResponse response = new GraveResponse(optionalGrave.get());
      return response;
    }

    return null;
  }

  public void buryDeceased(Long graveId, Long deceasedId)
  {
    Optional<Grave> optionalGrave       = this.graveRepository.findById(graveId);
    Optional<Deceased> optionalDeceased = this.deceasedRepository.findById(deceasedId);

    if (optionalGrave.isPresent() && optionalDeceased.isPresent())
    {
      Grave grave       = optionalGrave.get();
      Deceased deceased = optionalDeceased.get();

      grave.addDeceased(deceased);
      deceased.setStatus(DeceasedStatus.GRAVED);

      this.graveRepository.save(grave);
    }
  }
}
