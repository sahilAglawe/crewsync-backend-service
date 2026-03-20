package com.crewsync.EMS.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.crewsync.EMS.dto.TrainerDTO;
import com.crewsync.EMS.entity.Batch;
import com.crewsync.EMS.entity.BatchProgress;
import com.crewsync.EMS.entity.Trainer;
import com.crewsync.EMS.exception.ResourceNotFoundException;
import com.crewsync.EMS.repository.BatchProgressRepository;
import com.crewsync.EMS.repository.BatchRepository;
import com.crewsync.EMS.repository.TrainerRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TrainerServiceImple implements TrainerService {

    private final TrainerRepository trainerRepository;
    private final BatchRepository batchRepository;
    private final BatchProgressRepository batchProgressRepository;

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
                        t.getPhone(),
                        t.getPassword(),
                        t.getJoiningDate(),
                        t.getSalary(),
                        t.getEmpstatus()))
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
                trainer.getPhone(),
                trainer.getPassword(),
                trainer.getJoiningDate(),
                trainer.getSalary(),
                trainer.getEmpstatus()
            );
    }

    @Override
    @Transactional
    public void deleteTrainer(Long id) {

        if (!trainerRepository.existsById(id)) {
            throw new ResourceNotFoundException("Trainer", id);
        }

        Trainer trainer = trainerRepository.findById(id).get();

        // De-associate from batches (set trainer to null)
        if (trainer.getBatches() != null) {
            for (Batch b : trainer.getBatches()) {
                b.setTrainer(null);
                batchRepository.save(b);
            }
        }

        // De-associate from batch progress (set trainer to null)
        if (trainer.getProgressList() != null) {
            for (BatchProgress p : trainer.getProgressList()) {
                p.setTrainer(null);
                batchProgressRepository.save(p);
            }
        }

        trainerRepository.deleteById(id);
    }

    @Override
    public TrainerDTO updateTrainer(Long id, TrainerDTO trainerDTO) {
        Trainer trainer = trainerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Trainer", id));
        trainer.setName(trainerDTO.getName());
        trainer.setEmail(trainerDTO.getEmail());
        if (trainerDTO.getPhone() != null) trainer.setPhone(trainerDTO.getPhone());
        if (trainerDTO.getPassword() != null) trainer.setPassword(trainerDTO.getPassword());
        if (trainerDTO.getJoiningDate() != null) trainer.setJoiningDate(trainerDTO.getJoiningDate());
        if (trainerDTO.getSalary() != null) trainer.setSalary(trainerDTO.getSalary());
        if (trainerDTO.getEmpstatus() != null) trainer.setEmpstatus(trainerDTO.getEmpstatus());
        Trainer saved = trainerRepository.save(trainer);
        return new TrainerDTO(saved.getId(), saved.getName(), saved.getEmail(), saved.getPhone(),
                saved.getPassword(), saved.getJoiningDate(), saved.getSalary(), saved.getEmpstatus());
    }
}
