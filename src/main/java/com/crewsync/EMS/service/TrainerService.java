package com.crewsync.EMS.service;

import java.util.List;

import com.crewsync.EMS.dto.TrainerDTO;

public interface TrainerService {

	    TrainerDTO createTrainer(TrainerDTO trainerDTO);
	

	    List<TrainerDTO> getAllTrainers();

	    TrainerDTO getTrainerById(Long id);

	    void deleteTrainer(Long id);
}
