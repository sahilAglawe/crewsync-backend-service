package com.crewsync.EMS.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.crewsync.EMS.dto.AnalystDTO;
import com.crewsync.EMS.entity.Analyst;
import com.crewsync.EMS.repository.AdminRepository;
import com.crewsync.EMS.repository.AnalystRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AnalystServiceImple implements AnalystService {

	
	private final AnalystRepository analystRepository;
	
	@Override
	public AnalystDTO createAnalyst(AnalystDTO analystDTO) {
		Analyst analyst = new Analyst();
		analyst.setName(analystDTO.getName());
		analyst.setEmail(analystDTO.getEmail());
		analyst.setPassword(analystDTO.getPassword());
		
		analyst = analystRepository.save(analyst);
		
		analystDTO.setId(analyst.getId());
		return analystDTO;
		}

	@Override
	public List<AnalystDTO> getAllAnalysts() {
		 return analystRepository.findAll()
	                .stream()
	                .map(a -> new AnalystDTO(a.getId(), a.getName(), a.getEmail(), a.getPassword()))
	                .toList();
	}

	@Override
	public AnalystDTO getAnalystById(Long id) {

        Analyst analyst = analystRepository.findById(id).orElseThrow();

        return new AnalystDTO(analyst.getId(), analyst.getName(), analyst.getEmail(), analyst.getPassword());
	}

	@Override
	public void deleteAnalyst(Long id) {
		 analystRepository.deleteById(id);

	}

}
