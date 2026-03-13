package com.crewsync.EMS.service;

import lombok.RequiredArgsConstructor;
import java.util.List;

import com.crewsync.EMS.dto.AdminDTO;
import com.crewsync.EMS.entity.Admin;
import com.crewsync.EMS.exception.ResourceAlreadyExistsException;
import com.crewsync.EMS.exception.ResourceNotFoundException;
import com.crewsync.EMS.repository.AdminRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminServiceImple implements AdminService {

	@Autowired
	private final AdminRepository adminRepository;
	
	@Override
	public AdminDTO createAdmin(AdminDTO adminDTO) {
		
		List<Admin> admins = adminRepository.findAll();
		if (admins.size() >= 1) {
			throw new ResourceAlreadyExistsException("Admin already exists");
		}
		Admin admin = new Admin();
		admin.setName(adminDTO.getName());
		admin.setEmail(adminDTO.getEmail());
		admin.setPassword(adminDTO.getPassword());
		
		Admin savedAdmin = adminRepository.save(admin);
		
		adminDTO.setId(savedAdmin.getId());
		adminDTO.setName(savedAdmin.getName());
		adminDTO.setEmail(savedAdmin.getEmail());
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
		Admin admin = adminRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Admin", id));

        return new AdminDTO(admin.getId(), admin.getName(), admin.getEmail(), admin.getPassword());
	}

	@Override
	public void deleteAdmin(Long id) {
		if (!adminRepository.existsById(id)) {
			throw new ResourceNotFoundException("Admin", id);
		}
		adminRepository.deleteById(id);
	}

}