package com.pdsc.ashpath.components;

import org.springframework.stereotype.Component;

import com.pdsc.ashpath.domain.entity.AppRole;
import com.pdsc.ashpath.domain.enums.UserAppRole;
import com.pdsc.ashpath.repository.AppRoleRepository;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class Initializr
{
  private final AppRoleRepository appRoleRepository;

  @PostConstruct
  public void init()
  {
    this.addDefaultAppRoles();
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
}
