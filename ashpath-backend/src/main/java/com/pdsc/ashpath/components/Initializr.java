package com.pdsc.ashpath.components;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import com.pdsc.ashpath.domain.entity.AppRole;
import com.pdsc.ashpath.domain.entity.CremationEntry;
import com.pdsc.ashpath.domain.entity.Deceased;
import com.pdsc.ashpath.domain.entity.Grave;
import com.pdsc.ashpath.domain.entity.User;
import com.pdsc.ashpath.domain.enums.DeceasedStatus;
import com.pdsc.ashpath.domain.enums.UserAppRole;
import com.pdsc.ashpath.repository.AppRoleRepository;
import com.pdsc.ashpath.repository.CremationEntryRepository;
import com.pdsc.ashpath.repository.DeceasedRepository;
import com.pdsc.ashpath.repository.GraveRepository;
import com.pdsc.ashpath.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import net.datafaker.Faker;

@Component
@RequiredArgsConstructor
@SuppressWarnings("deprecation")
public class Initializr implements CommandLineRunner
{
  private final AppRoleRepository appRoleRepository;
  private final UserRepository userRepository;
  private final CremationEntryRepository cremationEntryRepository;
  private final DeceasedRepository deceasedRepository;
  private final GraveRepository graveRepository;

  private final Faker faker = new Faker(new Locale("pt-BR"));

  @Override
  public void run(String ...args)
  {
    this.addDefaultAppRoles();
    this.seedDatabase();
  }

  private void addDefaultAppRoles()
  {
    AppRole adminRoleApp = new AppRole();
    adminRoleApp.setName(UserAppRole.ADMIN);

    AppRole necrotomistRoleApp = new AppRole();
    necrotomistRoleApp.setName(UserAppRole.NECROTOMIST);

    appRoleRepository.save(adminRoleApp);
    appRoleRepository.save(necrotomistRoleApp);
  }

  private void seedDatabase()
  {
    this.addFakeUsers();
    this.addFakeCremationEntries();
    this.addFakeDeceasedsToCremationEntries();
    this.addFakeDeceasedsToGraves();
  }

  private void addFakeUsers()
  {
    for (int counter = 0; counter < 5; counter++)
    {
      User fakeUser = this.generateFakeUser(counter % 2 == 0);
      userRepository.save(fakeUser);
    }
  }

  private void addFakeCremationEntries()
  {
    List<User> fakeNecrotomists = userRepository.findAllNecrotomistUsers();

    for (User fakeNecrotomist: fakeNecrotomists)
    {
      CremationEntry fakeCremationEntry1 = this.generateFakeCremationEntry(fakeNecrotomist);
      CremationEntry fakeCremationEntry2 = this.generateFakeCremationEntry(fakeNecrotomist);

      cremationEntryRepository.save(fakeCremationEntry1);
      cremationEntryRepository.save(fakeCremationEntry2);
    }
  }

  private void addFakeDeceasedsToCremationEntries()
  {
    List<CremationEntry> cremationEntries = cremationEntryRepository.findAll();

    for (CremationEntry cremationEntry: cremationEntries)
    {
      Deceased deceased1 = this.generateFakeDeceasedToCremationEntry(cremationEntry);
      Deceased deceased2 = this.generateFakeDeceasedToCremationEntry(cremationEntry);

      deceasedRepository.save(deceased1);
      deceasedRepository.save(deceased2);
    }
  }

  private void addFakeDeceasedsToGraves()
  {
    for (int counter = 0; counter < 5; counter++)
    {
      Deceased deceased = this.generateFakeDeceasedToGrave();

      deceasedRepository.save(deceased);
    }
  }

  private User generateFakeUser(boolean isNecrotomist)
  {
    User user = new User();

    AppRole adminAppRole       = appRoleRepository.findById(1L).get();
    AppRole necrotomistAppRole = appRoleRepository.findById(2L).get();
    
    user.setFullname(faker.name().fullName());
    user.setEmail(this.generateFakeEmailAddress(user.getFullname()));
    user.setPassword("senhafoda123");
    user.setRegistrationDate(LocalDateTime.now());
    user.setLastActivityDate(LocalDateTime.now());
    user.setSpecialization(isNecrotomist ? "Autopsia" : null);
    
    user.addAppRole(isNecrotomist ? necrotomistAppRole : adminAppRole);

    return user;
  }

  private CremationEntry generateFakeCremationEntry(User necrotomist)
  {
    CremationEntry cremationEntry = new CremationEntry();

    cremationEntry.setCreationDate(LocalDateTime.now());
    cremationEntry.setNecrotomist(necrotomist);

    return cremationEntry;
  }

  private Deceased generateFakeDeceasedToCremationEntry(CremationEntry cremationEntry)
  {
    Deceased deceased = new Deceased();

    String familyLastName = faker.name().lastName();
    LocalDate birthDate   = faker.date().birthday(50, 70).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    
    deceased.setFullname(faker.name().fullName() +" "+ familyLastName);
    deceased.setBirthDate(birthDate);
    deceased.setDeathDate(faker.date().past(30, TimeUnit.DAYS).toLocalDateTime().toLocalDate());
    deceased.setCauseOfDeath("Morte Morrida");
    deceased.setDeathCertificate(this.readDeathCertificate());
    deceased.setFatherName(faker.name().malefirstName()   +" "+ faker.name().lastName() +" "+ familyLastName);
    deceased.setMotherName(faker.name().femaleFirstName() +" "+ faker.name().lastName() +" "+ familyLastName);

    deceased.setCremationEntry(cremationEntry);
    deceased.setCremationEnteredDate(LocalDateTime.now());
    deceased.setStatus(DeceasedStatus.WAITING_CREMATION);

    return deceased;
  }

  private Deceased generateFakeDeceasedToGrave()
  {
    Deceased deceased = new Deceased();

    String familyLastName = faker.name().lastName();
    LocalDate birthDate   = faker.date().birthday(50, 70).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

    deceased.setFullname(faker.name().fullName() +" "+ familyLastName);
    deceased.setBirthDate(birthDate);
    deceased.setDeathDate(faker.date().past(30, TimeUnit.DAYS).toLocalDateTime().toLocalDate());
    deceased.setCauseOfDeath("Morte Morrida");
    deceased.setDeathCertificate(this.readDeathCertificate());
    deceased.setFatherName(faker.name().malefirstName()   +" "+ faker.name().lastName() +" "+ familyLastName);
    deceased.setMotherName(faker.name().femaleFirstName() +" "+ faker.name().lastName() +" "+ familyLastName);

    Grave grave = new Grave();
    grave.setLocation("Cemitério "+ faker.name().fullName());
    graveRepository.save(grave);

    deceased.setGrave(grave);
    deceased.setStatus(DeceasedStatus.GRAVED);

    return deceased;
  }

  private byte[] readDeathCertificate()
  {
    ClassPathResource resource = new ClassPathResource("certidao-de-obito_modelo.pdf");

    try
    {
      InputStream inputStream = resource.getInputStream();
      byte[] fileBytes = inputStream.readAllBytes();

      return fileBytes;
    }
    catch(IOException exception)
    {
      System.err.println("Não foi possível ler o arquivo de certidão de óbito!");

      return null;
    }
  }

  private String generateFakeEmailAddress(String fullname)
  {
    return fullname.toLowerCase().replaceAll(" ", "-") +"@"+ "gmail.com";
  }
}
