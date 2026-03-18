package com.crewsync.EMS.service;

import java.util.List;

import org.springframework.stereotype.Service;


import com.crewsync.EMS.dto.TrainerDTO;
import com.crewsync.EMS.entity.Trainer;
import com.crewsync.EMS.exception.ResourceNotFoundException;
import com.crewsync.EMS.repository.TrainerRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TrainerServiceImple implements TrainerService {

    private final TrainerRepository trainerRepository;

    @Override
    public TrainerDTO createTrainer(TrainerDTO trainerDTO) {

        Trainer trainer = new Trainer();
        trainer.setName(trainerDTO.getName());
        trainer.setEmail(trainerDTO.getEmail());
        trainer.setPassword(trainerDTO.getPassword());

        Trainer savedTrainer = trainerRepository.save(trainer);

        trainerDTO.setId(savedTrainer.getId());
        trainerDTO.setName(savedTrainer.getName());
        trainerDTO.setEmail(savedTrainer.getEmail());

        return trainerDTO;
    }

    @Override
    public List<TrainerDTO> getAllTrainers() {

        return trainerRepository.findAll()
                .stream()
                .map(t -> new TrainerDTO(
                        t.getId(),
                        t.getName(),
                        t.getEmail(),
                        t.getPassword()))
                .toList();
    }

    @Override
    public TrainerDTO getTrainerById(Long id) {

        Trainer trainer = trainerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Trainer", id));

        return new TrainerDTO(
                trainer.getId(),
                trainer.getName(),
                trainer.getEmail(),
                trainer.getPassword());
    }

    @Override
    public void deleteTrainer(Long id) {

        if (!trainerRepository.existsById(id)) {
            throw new ResourceNotFoundException("Trainer", id);
        }

        trainerRepository.deleteById(id);
    }
}
