package com.crewsync.EMS.service;

import lombok.RequiredArgsConstructor;
import java.util.List;

import com.crewsync.EMS.service.AdminService;
import com.crewsync.EMS.dto.AdminDTO;
import com.crewsync.EMS.entity.Admin;
import com.crewsync.EMS.repository.AdminRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminServiceImple implements AdminService {

	private final AdminRepository adminRepository;
	
	@Override
	public AdminDTO createAdmin(AdminDTO adminDTO) {
		Admin admin = new Admin();
		admin.setName(adminDTO.getName());
		admin.setEmail(adminDTO.getEmail());
		admin.setPassword(adminDTO.getPassword());
		
		admin = adminRepository.save(admin);
		
		adminDTO.setId(admin.getId());
		return adminDTO;
	}

	@Override
	public List<AdminDTO> getAllAdmins() {
		return adminRepository.findAll()
                .stream()
                .map(admin -> new AdminDTO(admin.getId(), admin.getName(), admin.getEmail(), admin.getPassword()))
                .toList();
    }


	@Override
	public AdminDTO getAdminById(Long id) {
		Admin admin = adminRepository.findById(id).orElseThrow();

        return new AdminDTO(admin.getId(), admin.getName(), admin.getEmail(), admin.getPassword());
	}

	@Override
	public void deleteAdmin(Long id) {
		  adminRepository.deleteById(id);

	}

}