package com.pdsc.ashpath.service;

import com.pdsc.ashpath.domain.dto.request.LoginRequest;
import com.pdsc.ashpath.domain.entity.User;
import com.pdsc.ashpath.domain.service.AuthService;
import com.pdsc.ashpath.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class AuthServiceTest {

    private User user;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthService authService;

    @BeforeEach
    public void setUp() {
        user = new User();

        user.setEmail("test@example.com");
        user.setPassword("$2a$12$UeqET5NXiGIvHU3OkuHGTO.YsgvGwteii01Fs0YnYsDgrvlnndBXS");
        user.setFullname("Test User");
        user.setRegistrationDate(LocalDateTime.now());
        user.setLastActivityDate(LocalDateTime.now());
        user.setSpecialization("Specialist");
        user.setApproved(true);
        user.setAppRoleSet(new HashSet<>());
        user.setCremationEntrySet(new HashSet<>());

        userRepository.saveAndFlush(user);
    }

    @Test
    public void loginSuccess(){
        String email = "test@example.com";
        String password = "s3nh4@S";

        User u =  authService.login(new LoginRequest(email,password));

        assertNotNull(u);
        assertEquals(u.getId(),user.getId());
        assertEquals(u.getEmail(),user.getEmail());
        assertEquals(u.getPassword(),user.getPassword());
    }

    @Test
    public void loginFailWrongPassword(){
        String email = "test@example.com";
        String password = "senha";
        User u =  authService.login(new LoginRequest(email,password));
        assertNull(u);
    }

    @Test
    public void loginFailWrongEmail(){
        String email = "wrong@example.com";
        String password = "s3nh4@S";
        User u =  authService.login(new LoginRequest(email,password));
        assertNull(u);
    }

    @Test
    public void loginFailCabaNotApproved(){
        String email = "test@example.com";
        String password = "s3nh4@S";
        User user1 = userRepository.findByEmail("test@example.com").orElse(null);
        assert user1 != null;
        user1.setApproved(false);
        userRepository.saveAndFlush(user1);
        User u =  authService.login(new LoginRequest(email,password));
        assertNull(u);
    }

}
