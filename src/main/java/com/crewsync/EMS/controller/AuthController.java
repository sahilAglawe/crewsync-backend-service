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
import com.crewsync.EMS.security.JwtUtil;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AdminRepository adminRepository;
    private final TrainerRepository trainerRepository;
    private final AnalystRepository analystRepository;
    private final CounsellorRepository counsellorRepository;
    private final JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> loginRequest) {
        String email    = loginRequest.get("email");
        String password = loginRequest.get("password");
        String role     = loginRequest.get("role");

        if (email == null || password == null || role == null) {
            return ResponseEntity.badRequest()
                    .body(Map.of("error", "Email, password and role are required"));
        }

        switch (role.toUpperCase()) {
            case "ADMIN": {
                Admin admin = adminRepository.findByEmail(email).orElse(null);
                if (admin != null && password.equals(admin.getPassword())) {
                    return ResponseEntity.ok(buildResponse(admin.getId(), admin.getName(), admin.getEmail(), "ADMIN"));
                }
                break;
            }
            case "TRAINER": {
                Trainer trainer = trainerRepository.findByEmail(email).orElse(null);
                if (trainer != null && password.equals(trainer.getPassword())) {
                    return ResponseEntity.ok(buildResponse(trainer.getId(), trainer.getName(), trainer.getEmail(), "TRAINER"));
                }
                break;
            }
            case "ANALYST": {
                Analyst analyst = analystRepository.findByEmail(email).orElse(null);
                if (analyst != null && password.equals(analyst.getPassword())) {
                    return ResponseEntity.ok(buildResponse(analyst.getId(), analyst.getName(), analyst.getEmail(), "ANALYST"));
                }
                break;
            }
            case "COUNSELOR": {
                Counsellor counsellor = counsellorRepository.findByEmail(email).orElse(null);
                if (counsellor != null && password.equals(counsellor.getPassword())) {
                    return ResponseEntity.ok(buildResponse(counsellor.getId(), counsellor.getName(), counsellor.getEmail(), "COUNSELOR"));
                }
                break;
            }
            default:
                return ResponseEntity.badRequest().body(Map.of("error", "Invalid role"));
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(Map.of("error", "Invalid email or password"));
    }

    /** Shared helper: build a login response map with JWT token included. */
    private Map<String, Object> buildResponse(Long id, String name, String email, String role) {
        String token = jwtUtil.generateToken(id, email, role);
        Map<String, Object> response = new HashMap<>();
        response.put("id", id);
        response.put("name", name);
        response.put("email", email);
        response.put("role", role);
        response.put("token", token);
        return response;
    }
}
