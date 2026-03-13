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
import com.crewsync.EMS.dto.TrainerDTO;
import com.crewsync.EMS.service.CounsellorService;
import com.crewsync.EMS.service.TrainerService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/trainers")
@RequiredArgsConstructor
public class TrainerController {

private final TrainerService trainerService;
	
	@PostMapping
	public TrainerDTO createCounsellor(@RequestBody TrainerDTO trainerDTO) {
		return trainerService.createTrainer(trainerDTO);
	}
	
	@GetMapping
    public List<TrainerDTO> getAllTrainers() {
        return trainerService.getAllTrainers();
    }
	
	@GetMapping("/{id}")
	public TrainerDTO getTrainerById(@PathVariable Long id) {
		return trainerService.getTrainerById(id);
	}
	
	@DeleteMapping("/{id}")
	public void deleteTrainer(@PathVariable Long id) {
	  trainerService.deleteTrainer(id);
	}
}
