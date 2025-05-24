package com.pdsc.ashpath.domain.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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

  public void createCremationQueue(Long necrotomistId)
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

  public void addDeceasedToCremationQueue(Long cremationQueueId, Long deceasedId)
  {
    Optional<CremationEntry> optionalCremationQueue = cremationEntryRepository.findById(cremationQueueId);
    Optional<Deceased> optionalDeceased = deceasedRepository.findById(deceasedId);

    if (optionalCremationQueue.isPresent() && optionalDeceased.isPresent())
    {
      CremationEntry cremationEntry = optionalCremationQueue.get();
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

  public List<CremationEntry> findAll (){
    return cremationEntryRepository.findAll();
  }
}
