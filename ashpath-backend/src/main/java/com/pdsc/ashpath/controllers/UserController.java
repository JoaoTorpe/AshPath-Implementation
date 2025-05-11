package com.pdsc.ashpath.controllers;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pdsc.ashpath.domain.entity.Admin;
import com.pdsc.ashpath.domain.entity.Necrotomist;
import com.pdsc.ashpath.domain.entity.User;
import com.pdsc.ashpath.domain.enums.UserRole;
import com.pdsc.ashpath.dto.request.LoginRequest;
import com.pdsc.ashpath.dto.request.createAdminUserRequest;
import com.pdsc.ashpath.dto.request.createNecrotomistUserRequest;
import com.pdsc.ashpath.repository.NecrotomistRepository;
import com.pdsc.ashpath.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    
    private final UserRepository userRepository;
    private final NecrotomistRepository necrotomistRepository;

    @PostMapping("/admin")
    public ResponseEntity<Void> createAdminUser(@RequestBody createAdminUserRequest request)
    {
        if (request.getEmail().isEmpty() || request.getFullname().isEmpty())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

        Admin admin = new Admin();

        admin.setEmail(request.getEmail());
        admin.setPassword(request.getPassword());
        admin.setFullName(request.getFullname());
        admin.setRegistrationDate(LocalDateTime.now());
        admin.setLastActivityDate(LocalDateTime.now());
        admin.setUserRole(UserRole.ADMIN);

        userRepository.save(admin);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/necrotomist")
    public ResponseEntity<Void> createNecrotomistUser(@RequestBody createNecrotomistUserRequest request)
    {
        if (request.getEmail().isEmpty() || request.getFullname().isEmpty())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        
        Necrotomist necrotomist = new Necrotomist();

        necrotomist.setEmail(request.getEmail());
        necrotomist.setPassword(request.getPassword());
        necrotomist.setFullName(request.getFullname());
        necrotomist.setRegistrationDate(LocalDateTime.now());
        necrotomist.setLastActivityDate(LocalDateTime.now());
        necrotomist.setUserRole(UserRole.NECROTOMIST);
        necrotomist.setSpecialization(request.getSpecialization());

        userRepository.save(necrotomist);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/necrotomist")
    public ResponseEntity<List<Necrotomist>> readAllNecrotomistUsers(@RequestParam(required = false) String specialization)
    {
        List<Necrotomist> necrotomists = necrotomistRepository.findAll();

        if (specialization != null)
        {
            necrotomists = necrotomists
                            .stream()
                            .filter(necrotomist -> necrotomist.getSpecialization().toLowerCase().contains(specialization))
                            .collect(Collectors.toList());
        }

        return ResponseEntity.status(HttpStatus.OK).body(necrotomists);
    }

    @PostMapping("/login")
    public ResponseEntity<Void> login(@RequestBody LoginRequest request)
    {
        Optional<User> user = userRepository.findByEmail(request.getEmail());

        if (user.isPresent())
        {
            User retrievedUser = user.get();
            if (retrievedUser.getPassword().equals(request.getPassword()))
                return ResponseEntity.status(HttpStatus.OK).build();
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
}
