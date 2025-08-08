package com.pdsc.ashpath.component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import com.pdsc.ashpath.domain.entity.Deceased;
import com.pdsc.ashpath.repository.DeceasedRepository;

@Component
public class Initializer implements CommandLineRunner
{
  private final DeceasedRepository deceasedRepository;

  public Initializer(DeceasedRepository deceasedRepository)
  {
    this.deceasedRepository = deceasedRepository;
  }

  @Override
  public void run(String... args) throws Exception
  {
    this.addPdfDeathCertificateModel();
  }

  private void addPdfDeathCertificateModel() throws IOException
  {
    Path path = ResourceUtils.getFile("classpath:certidao-de-obito_modelo.pdf").toPath();
    byte[] pdfBytes = Files.readAllBytes(path);

    List<Deceased> deceaseds =  this.deceasedRepository.findAll();

    for (Deceased deceased: deceaseds)
    {
      deceased.setDeathCertificate(pdfBytes);
      this.deceasedRepository.save(deceased);
    }
  }
}
