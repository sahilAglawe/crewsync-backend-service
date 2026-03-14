package com.crewsync.EMS.controller;

import java.util.List;


import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crewsync.EMS.dto.AnalystDTO;
import com.crewsync.EMS.dto.CounsellorDTO;
import com.crewsync.EMS.dto.TrainerDTO;
import com.crewsync.EMS.service.AnalystService;
import com.crewsync.EMS.service.CounsellorService;
import com.crewsync.EMS.service.TrainerService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {

	
	    private final TrainerService trainerService;
	    private final CounsellorService counsellorService;
	    private final AnalystService analystService;
	
	    // -------- Trainer APIs --------

	    @PostMapping("/trainers")
	    public TrainerDTO addTrainer(@RequestBody TrainerDTO trainerDTO) {
	        return trainerService.createTrainer(trainerDTO);
	    }

	    @GetMapping("/trainers")
	    public List<TrainerDTO> getAllTrainers() {
	        return trainerService.getAllTrainers();
	    }

	    @GetMapping("/trainers/{id}")
	    public TrainerDTO getTrainerById(@PathVariable Long id) {
	        return trainerService.getTrainerById(id);
	    }

	    @DeleteMapping("/trainers/{id}")
	    public String deleteTrainer(@PathVariable Long id) {
	        trainerService.deleteTrainer(id);
	        return "Trainer deleted successfully";
	    }
	    
	 // -------- Counsellor APIs --------

	    @PostMapping("/counsellors")
	    public CounsellorDTO addCounsellor(@RequestBody CounsellorDTO dto) {
	        return counsellorService.createCounsellor(dto);
	    }

	    @GetMapping("/counsellors")
	    public List<CounsellorDTO> getAllCounsellors() {
	        return counsellorService.getAllCounsellors();
	    }

	    @GetMapping("/counsellors/{id}")
	    public CounsellorDTO getCounsellor(@PathVariable Long id) {
	        return counsellorService.getCounsellorById(id);
	    }

	    @DeleteMapping("/counsellors/{id}")
	    public String deleteCounsellor(@PathVariable Long id) {
	        counsellorService.deleteCounsellor(id);
	        return "Counsellor deleted successfully";
	    }
	    
	    // -------- Analyst APIs --------

	    @PostMapping("/analysts")
	    public AnalystDTO addAnalyst(@RequestBody AnalystDTO dto) {
	        return analystService.createAnalyst(dto);
	    }

	    @GetMapping("/analysts")
	    public List<AnalystDTO> getAllAnalysts() {
	        return analystService.getAllAnalysts();
	    }

	    @GetMapping("/analysts/{id}")
	    public AnalystDTO getAnalyst(@PathVariable Long id) {
	        return analystService.getAnalystById(id);
	    }

	    @DeleteMapping("/analysts/{id}")
	    public String deleteAnalyst(@PathVariable Long id) {
	        analystService.deleteAnalyst(id);
	        return "Analyst deleted successfully";
	    }
	    
}
