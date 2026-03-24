package com.crewsync.EMS.config;

import com.crewsync.EMS.entity.Admin;
import com.crewsync.EMS.repository.AdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final AdminRepository adminRepository;

    
    @Override
    public void run(String... args) {
        Admin admin = adminRepository.findByEmail("admin@crewsync.com").orElse(new Admin());
        admin.setName("Admin");
        admin.setEmail("admin@crewsync.com");
        admin.setPassword("admin123");
        adminRepository.save(admin);
        System.out.println("✅ Default admin ensured: admin@crewsync.com / admin123");
    }
}
