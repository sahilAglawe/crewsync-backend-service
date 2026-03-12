package com.crewsync.EMS.service;

import java.util.List;

import com.crewsync.EMS.dto.AdminDTO;
import com.crewsync.EMS.dto.TrainerDTO;
import com.crewsync.EMS.entity.Admin;
import com.crewsync.EMS.entity.Trainer;
import com.crewsync.EMS.repository.AdminRepository;
import com.crewsync.EMS.repository.TrainerRepository;

public class TrainerServiceImple implements TrainerService {
	
	private final TrainerRepository trainerRepository = null;

	@Override
	public TrainerDTO createTrainer(TrainerDTO trainerDTO) {
		Trainer trainer = new Trainer();
		trainer.setName(trainerDTO.getName());
		trainer.setEmail(trainerDTO.getEmail());
		trainer.setPassword(trainerDTO.getPassword());
		
		trainer = trainerRepository.save(trainer);
		
		trainerDTO.setId(trainer.getId());
		return trainerDTO;
	}

	@Override
	public List<TrainerDTO> getAllTrainers() {
		 return trainerRepository.findAll()
	                .stream()
	                .map(t -> new TrainerDTO(t.getId(), t.getName(), t.getEmail(), t.getPassword()))
	                .toList();
	}

	@Override
	public TrainerDTO getTrainerById(Long id) {
		Trainer trainer = trainerRepository.findById(id).orElseThrow();

        return new TrainerDTO(trainer.getId(), trainer.getName(), trainer.getEmail(), trainer.getPassword());
	}

	@Override
	public void deleteTrainer(Long id) {
		
		trainerRepository.deleteById(id);
	}

}
