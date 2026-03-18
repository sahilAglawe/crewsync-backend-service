package com.crewsync.EMS.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crewsync.EMS.entity.Admin;
import com.crewsync.EMS.entity.Analyst;
import com.crewsync.EMS.entity.Counsellor;
import com.crewsync.EMS.entity.Trainer;
import com.crewsync.EMS.repository.AdminRepository;
import com.crewsync.EMS.repository.AnalystRepository;
import com.crewsync.EMS.repository.CounsellorRepository;
import com.crewsync.EMS.repository.TrainerRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AdminRepository adminRepository;
    private final TrainerRepository trainerRepository;
    private final AnalystRepository analystRepository;
    private final CounsellorRepository counsellorRepository;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> loginRequest) {
        String email = loginRequest.get("email");
        String password = loginRequest.get("password");
        String role = loginRequest.get("role");

        if (email == null || password == null || role == null) {
            return ResponseEntity.badRequest().body(Map.of("error", "Email, password and role are required"));
        }

        switch (role.toUpperCase()) {
            case "ADMIN": {
                Admin admin = adminRepository.findByEmail(email).orElse(null);
                if (admin != null && admin.getPassword().equals(password)) {
                    Map<String, Object> response = new HashMap<>();
                    response.put("id", admin.getId());
                    response.put("name", admin.getName());
                    response.put("email", admin.getEmail());
                    response.put("role", "ADMIN");
                    return ResponseEntity.ok(response);
                }
                break;
            }
            case "TRAINER": {
                Trainer trainer = trainerRepository.findByEmail(email).orElse(null);
                if (trainer != null && trainer.getPassword().equals(password)) {
                    Map<String, Object> response = new HashMap<>();
                    response.put("id", trainer.getId());
                    response.put("name", trainer.getName());
                    response.put("email", trainer.getEmail());
                    response.put("role", "TRAINER");
                    return ResponseEntity.ok(response);
                }
                break;
            }
            case "ANALYST": {
                Analyst analyst = analystRepository.findByEmail(email).orElse(null);
                if (analyst != null && analyst.getPassword().equals(password)) {
                    Map<String, Object> response = new HashMap<>();
                    response.put("id", analyst.getId());
                    response.put("name", analyst.getName());
                    response.put("email", analyst.getEmail());
                    response.put("role", "ANALYST");
                    return ResponseEntity.ok(response);
                }
                break;
            }
            case "COUNSELOR": {
                Counsellor counsellor = counsellorRepository.findByEmail(email).orElse(null);
                if (counsellor != null && counsellor.getPassword().equals(password)) {
                    Map<String, Object> response = new HashMap<>();
                    response.put("id", counsellor.getId());
                    response.put("name", counsellor.getName());
                    response.put("email", counsellor.getEmail());
                    response.put("role", "COUNSELOR");
                    return ResponseEntity.ok(response);
                }
                break;
            }
            default:
                return ResponseEntity.badRequest().body(Map.of("error", "Invalid role"));
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(Map.of("error", "Invalid email or password"));
    }
}
