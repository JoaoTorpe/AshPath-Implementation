package com.pdsc.ashpath.controller;

import java.time.LocalDateTime;

import static org.hamcrest.Matchers.hasItem;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.when;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.pdsc.ashpath.controllers.AuthController;
import com.pdsc.ashpath.domain.dto.request.LoginRequest;
import com.pdsc.ashpath.domain.entity.AppRole;
import com.pdsc.ashpath.domain.entity.User;
import com.pdsc.ashpath.domain.enums.UserAppRole;
import com.pdsc.ashpath.domain.service.AuthService;

@WebMvcTest(AuthController.class)
public class AuthControllerTest
{
  @Autowired
  private MockMvc mockMvc;

  @MockitoBean
  private AuthService authService;

  @Test
  public void loginAttempt_WithoutEmailField_ShouldReturn400() throws Exception
  {
    String jsonRequest = """
      {
        "password": "s3nh4@S"
      }
    """;

    mockMvc.perform(post("/auth/login").contentType(MediaType.APPLICATION_JSON).content(jsonRequest))
      .andExpect(status().isBadRequest())
      .andExpect(jsonPath("$.message").value("Erro de validação"))
      .andExpect(jsonPath("$.status").value(400))
      .andExpect(jsonPath("$.errors[0]").value("email: 'email' field is required."));
  }

  @Test
  public void loginAttempt_WithoutPasswordField_ShouldReturn400() throws Exception
  {
    String jsonRequest = """
      {
        "email": "mohg.silva@gmail.com"
      }
    """;

    mockMvc.perform(post("/auth/login").contentType(MediaType.APPLICATION_JSON).content(jsonRequest))
      .andExpect(status().isBadRequest())
      .andExpect(jsonPath("$.message").value("Erro de validação"))
      .andExpect(jsonPath("$.status").value(400))
      .andExpect(jsonPath("$.errors[0]").value("password: 'password' field is required."));
  }

  @Test
  public void loginAttempt_WithoutEmailFieldAndPasswordField_ShouldReturn400() throws Exception
  {
    String jsonRequest = """
      {}
    """;

    mockMvc.perform(post("/auth/login").contentType(MediaType.APPLICATION_JSON).content(jsonRequest))
      .andExpect(status().isBadRequest())
      .andExpect(jsonPath("$.message").value("Erro de validação"))
      .andExpect(jsonPath("$.status").value(400))
      .andExpect(jsonPath("$.errors", hasItem("email: 'email' field is required.")))
      .andExpect(jsonPath("$.errors", hasItem("password: 'password' field is required.")));
  }

  @Test
  public void failedLogin_WithWrongPassword_ShouldReturn401() throws Exception
  {
    // Configuração do Mock
    LoginRequest loginRequestDto = new LoginRequest("valid.email@email.com", "wrongpassword");
    when(authService.login(loginRequestDto)).thenReturn(null);

    // Corpo da Requisição
    String jsonRequest = """
      {
        "email": "valid.email@email.com",
        "password": "wrongpassword"
      }
    """;

    // Executa e Verifica
    mockMvc.perform(post("/auth/login").contentType(MediaType.APPLICATION_JSON).content(jsonRequest))
      .andExpect(status().isUnauthorized())
      .andExpect(content().string("Falha ao fazer login"));
  }

  @Test
  public void failedLogin_WithIncorrectEmail_ShouldReturn401() throws Exception
  {
    // Configuração do Mock
    LoginRequest loginRequestDto = new LoginRequest("invalid.email@email.com", "password");
    when(authService.login(loginRequestDto)).thenReturn(null);

    // Corpo da Requisição
    String jsonRequest = """
      {
        "email": "invalid.email@email.com",
        "password": "password"
      }
    """;

    // Executa e Verifica
    mockMvc.perform(post("/auth/login").contentType(MediaType.APPLICATION_JSON).content(jsonRequest))
      .andExpect(status().isUnauthorized())
      .andExpect(content().string("Falha ao fazer login"));
  }

  @Test
  public void failedLogin_WithNotApprovedUser_ShouldReturn403() throws Exception
  {
    // Configuração do Mock
    LoginRequest loginRequestDto = new LoginRequest("admin@email.com", "s3nh4@S");
    AppRole adminAppRole = new AppRole();
    User userResponse = new User();

    userResponse.setId(5L);
    userResponse.setEmail("admin@email.com");
    userResponse.setPassword("s3nh4@S");
    userResponse.setFullname("Admin");
    userResponse.setRegistrationDate(LocalDateTime.now());
    userResponse.setLastActivityDate(LocalDateTime.now());
    userResponse.setSpecialization(null);
    userResponse.setApproved(false);

    adminAppRole.setId(1L);
    adminAppRole.setName(UserAppRole.ADMIN);
    userResponse.addAppRole(adminAppRole);

    when(authService.login(loginRequestDto)).thenReturn(userResponse);

    // Corpo da Requisição
    String jsonRequest = """
      {
        "email": "admin@email.com",
        "password": "s3nh4@S"
      }    
    """;

    mockMvc.perform(post("/auth/login").contentType(MediaType.APPLICATION_JSON).content(jsonRequest))
      .andExpect(status().isForbidden())
      .andExpect(content().string("Usuário inativo"));
  }

  @Test
  public void successfulAdminLogin_ShouldReturn200() throws Exception
  {
    // Configuração do Mock
    LoginRequest loginRequestDto = new LoginRequest("admin@email.com", "s3nh4@S");
    AppRole adminAppRole = new AppRole();
    User userResponse = new User();

    userResponse.setId(1L);
    userResponse.setEmail("admin@email.com");
    userResponse.setPassword("s3nh4@S");
    userResponse.setFullname("Admin");
    userResponse.setRegistrationDate(LocalDateTime.now());
    userResponse.setLastActivityDate(LocalDateTime.now());
    userResponse.setSpecialization(null);
    userResponse.setApproved(true);

    adminAppRole.setId(1L);
    adminAppRole.setName(UserAppRole.ADMIN);
    userResponse.addAppRole(adminAppRole);

    when(authService.login(loginRequestDto)).thenReturn(userResponse);

    // Corpo da Requisição
    String jsonRequest = """
      {
        "email": "admin@email.com",
        "password": "s3nh4@S"
      }
    """;

    // Executa e Verifica
    mockMvc.perform(post("/auth/login").contentType(MediaType.APPLICATION_JSON).content(jsonRequest))
      .andExpect(status().isOk())
      .andExpect(jsonPath("$.loggedUserId").value(1L))
      .andExpect(jsonPath("$.appRoleSet[0].id").value(1L))
      .andExpect(jsonPath("$.appRoleSet[0].name").value("ADMIN"));
  }

  @Test
  public void successfulNecrotomistLogin_ShouldReturn200() throws Exception
  {
    // Configuração do Mock
    LoginRequest loginRequestDto = new LoginRequest("necrotomist@email.com", "s3nh4@S");
    AppRole necrotomistAppRole = new AppRole();
    User userResponse = new User();

    userResponse.setId(2L);
    userResponse.setEmail("necrotomist@email.com");
    userResponse.setPassword("s3nh4@S");
    userResponse.setFullname("Necrotomist");
    userResponse.setRegistrationDate(LocalDateTime.now());
    userResponse.setLastActivityDate(LocalDateTime.now());
    userResponse.setSpecialization("Forensic Pathology Support");
    userResponse.setApproved(true);

    necrotomistAppRole.setId(2L);
    necrotomistAppRole.setName(UserAppRole.NECROTOMIST);
    userResponse.addAppRole(necrotomistAppRole);

    when(authService.login(loginRequestDto)).thenReturn(userResponse);

    // Corpo da Requisição
    String jsonRequest = """
      {
        "email": "necrotomist@email.com",
        "password": "s3nh4@S"
      }
    """;

    // Executa e Verifica
    mockMvc.perform(post("/auth/login").contentType(MediaType.APPLICATION_JSON).content(jsonRequest))
      .andExpect(status().isOk())
      .andExpect(jsonPath("$.loggedUserId").value(2L))
      .andExpect(jsonPath("$.appRoleSet[0].id").value(2L))
      .andExpect(jsonPath("$.appRoleSet[0].name").value("NECROTOMIST"));
  }

  @Test
  public void successfulViewerLogin_ShouldReturn200() throws Exception
  {
    // Configuração do Mock
    LoginRequest loginRequestDto = new LoginRequest("viewer@email.com", "s3nh4@S");
    AppRole viewerAppRole = new AppRole();
    User userResponse = new User();

    userResponse.setId(3L);
    userResponse.setEmail("viewer@email.com");
    userResponse.setPassword("s3nh4@S");
    userResponse.setFullname("Viewer");
    userResponse.setRegistrationDate(LocalDateTime.now());
    userResponse.setLastActivityDate(LocalDateTime.now());
    userResponse.setSpecialization(null);
    userResponse.setApproved(true);

    viewerAppRole.setId(3L);
    viewerAppRole.setName(UserAppRole.VIEWER);
    userResponse.addAppRole(viewerAppRole);

    when(authService.login(loginRequestDto)).thenReturn(userResponse);

    // Corpo da Requisição
    String jsonRequest = """
      {
        "email": "viewer@email.com",
        "password": "s3nh4@S"
      }
    """;

    // Executa e Verifica
    mockMvc.perform(post("/auth/login").contentType(MediaType.APPLICATION_JSON).content(jsonRequest))
      .andExpect(status().isOk())
      .andExpect(jsonPath("$.loggedUserId").value(3L))
      .andExpect(jsonPath("$.appRoleSet[0].id").value(3L))
      .andExpect(jsonPath("$.appRoleSet[0].name").value("VIEWER"));
  }
}
