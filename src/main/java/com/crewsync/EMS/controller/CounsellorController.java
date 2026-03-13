package com.crewsync.EMS.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.crewsync.EMS.dto.CounsellorDTO;

import com.crewsync.EMS.service.CounsellorService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/counsellors")
@RequiredArgsConstructor
public class CounsellorController {

	private final CounsellorService counsellorService;
	
	@PostMapping
	public CounsellorDTO createCounsellor(@RequestBody CounsellorDTO counsellorDTO) {
		return counsellorService.createCounsellor(counsellorDTO);
	}
	
	@GetMapping
    public List<CounsellorDTO> getAllCounsellors() {
        return counsellorService.getAllCounsellors();
    }
	
	@GetMapping("/{id}")
	public CounsellorDTO getCounsellorById(@PathVariable Long id) {
		return counsellorService.getCounsellorById(id);
	}
	
	@DeleteMapping("/{id}")
	public void deleteCounsellor(@PathVariable Long id) {
	  counsellorService.deleteCounsellor(id);
	}
}
