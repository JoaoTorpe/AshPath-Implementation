package com.pdsc.ashpath.domain.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.pdsc.ashpath.domain.entity.CremationQueue;
import com.pdsc.ashpath.domain.entity.User;
import com.pdsc.ashpath.repository.CremationQueueRepository;
import com.pdsc.ashpath.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CremationQueueService
{
  private final CremationQueueRepository cremationQueueRepository;
  private final UserRepository userRepository;

  public void createCremationQueue(Long necrotomistId)
  {
    Optional<User> optionalNecrotomist = userRepository.findById(necrotomistId);
    CremationQueue cremationQueue = new CremationQueue();

    if (optionalNecrotomist.isPresent())
    {
      User necrotomist = optionalNecrotomist.get();

      cremationQueue.setNecrotomist(necrotomist);
      cremationQueue.setCreationDate(LocalDateTime.now());

      cremationQueueRepository.save(cremationQueue);
    }
  }
}
