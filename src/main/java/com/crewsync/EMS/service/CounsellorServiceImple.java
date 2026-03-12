package com.crewsync.EMS.service;

import java.util.List;

import com.crewsync.EMS.dto.CounsellorDTO;
import com.crewsync.EMS.entity.Counsellor;
import com.crewsync.EMS.entity.Trainer;
import com.crewsync.EMS.repository.CounsellorRepository;
import com.crewsync.EMS.repository.TrainerRepository;

public class CounsellorServiceImple implements CounsellorService {
	
	private final CounsellorRepository counsellorRepository = null;

	@Override
	public CounsellorDTO createCounsellor(CounsellorDTO counsellorDTO) {
		
		Counsellor counsellor = new Counsellor();
		counsellor.setName(counsellorDTO.getName());
		counsellor.setEmail(counsellorDTO.getEmail());
		counsellor.setPassword(counsellorDTO.getPassword());
		
		counsellor = counsellorRepository.save(counsellor);
		
		counsellorDTO.setId(counsellor.getId());
		return counsellorDTO;
	}

	@Override
	public List<CounsellorDTO> getAllCounsellors() {
		return counsellorRepository.findAll().stream().map(c -> new CounsellorDTO(c.getId(), c.getName(), c.getEmail(), c.getPassword())).toList();
				}

	@Override
	public CounsellorDTO getCounsellorById(Long id) {
		Counsellor counsellor = counsellorRepository.findById(id).orElseThrow();
		
		return new CounsellorDTO(counsellor.getId(), counsellor.getName(), counsellor.getEmail(), counsellor.getPassword());
	}

	@Override
	public void deleteCounsellor(Long id) {
		
		counsellorRepository.deleteById(id);

	}

}
