package com.pdsc.ashpath.domain.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.pdsc.ashpath.domain.dto.response.CremationEntryResponse;
import com.pdsc.ashpath.domain.entity.CremationEntry;
import com.pdsc.ashpath.domain.entity.Deceased;
import com.pdsc.ashpath.domain.entity.User;
import com.pdsc.ashpath.domain.enums.DeceasedStatus;
import com.pdsc.ashpath.repository.CremationEntryRepository;
import com.pdsc.ashpath.repository.DeceasedRepository;
import com.pdsc.ashpath.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CremationEntryService
{
  private final CremationEntryRepository cremationEntryRepository;
  private final UserRepository userRepository;
  private final DeceasedRepository deceasedRepository;

  public void createCremationEntry(Long necrotomistId)
  {
    Optional<User> optionalNecrotomist = userRepository.findById(necrotomistId);
    CremationEntry cremationEntry = new CremationEntry();

    if (optionalNecrotomist.isPresent())
    {
      User necrotomist = optionalNecrotomist.get();

      cremationEntry.setNecrotomist(necrotomist);
      cremationEntry.setCreationDate(LocalDateTime.now());

      cremationEntryRepository.save(cremationEntry);
    }
  }

  public void addDeceasedToCremationEntry(Long cremationEntryId, Long deceasedId)
  {
    Optional<CremationEntry> optionalCremationEntry = cremationEntryRepository.findById(cremationEntryId);
    Optional<Deceased> optionalDeceased = deceasedRepository.findById(deceasedId);

    if (optionalCremationEntry.isPresent() && optionalDeceased.isPresent())
    {
      CremationEntry cremationEntry = optionalCremationEntry.get();
      Deceased deceased = optionalDeceased.get();

      cremationEntry.addDeceased(deceased);
      deceased.setStatus(DeceasedStatus.WAITING_CREMATION);
      deceased.setCremationEnteredDate(LocalDateTime.now());

      cremationEntryRepository.save(cremationEntry);
    }
  }

  public void executeCremation(Long deceasedId)
  {
    Optional<Deceased> optionalDeceased = deceasedRepository.findById(deceasedId);

    if (optionalDeceased.isPresent())
    {
      Deceased deceased = optionalDeceased.get();

      if (deceased.getStatus() == DeceasedStatus.WAITING_CREMATION)
      {
        deceased.setStatus(DeceasedStatus.CREMATED);
        deceasedRepository.save(deceased);
      }
    }
  }

  public List<CremationEntryResponse> findAll()
  {
    List<CremationEntry> cremationEntries = cremationEntryRepository.findAll();
    List<CremationEntryResponse> response = cremationEntries.stream()
      .map(cremationEntry -> new CremationEntryResponse(cremationEntry))
      .collect(Collectors.toList());
    
    return response;
  }
}
