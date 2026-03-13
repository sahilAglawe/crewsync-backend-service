package com.crewsync.EMS.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crewsync.EMS.dto.CounsellorDTO;
import com.crewsync.EMS.entity.Counsellor;
import com.crewsync.EMS.exception.ResourceNotFoundException;
import com.crewsync.EMS.repository.CounsellorRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CounsellorServiceImple implements CounsellorService {
	
	@Autowired
	private final CounsellorRepository counsellorRepository;

	@Override
	public CounsellorDTO createCounsellor(CounsellorDTO counsellorDTO) {
		
		Counsellor counsellor = new Counsellor();
		counsellor.setName(counsellorDTO.getName());
		counsellor.setEmail(counsellorDTO.getEmail());
		counsellor.setPassword(counsellorDTO.getPassword());
		
		Counsellor savedCounsellor = counsellorRepository.save(counsellor);
		
		counsellorDTO.setId(savedCounsellor.getId());
		counsellorDTO.setName(savedCounsellor.getName());
		counsellorDTO.setEmail(savedCounsellor.getEmail());
		return counsellorDTO;
	}

	@Override
	public List<CounsellorDTO> getAllCounsellors() {
		return counsellorRepository.findAll().stream()
				.map(c -> new CounsellorDTO(c.getId(), c.getName(), c.getEmail(), c.getPassword()))
				.toList();
	}

	@Override
	public CounsellorDTO getCounsellorById(Long id) {
		Counsellor counsellor = counsellorRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Counsellor", id));
		
		return new CounsellorDTO(counsellor.getId(), counsellor.getName(), counsellor.getEmail(), counsellor.getPassword());
	}

	@Override
	public void deleteCounsellor(Long id) {
		if (!counsellorRepository.existsById(id)) {
			throw new ResourceNotFoundException("Counsellor", id);
		}
		counsellorRepository.deleteById(id);
	}

}
