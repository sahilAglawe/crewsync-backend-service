package com.crewsync.EMS.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.crewsync.EMS.dto.BatchDTO;
import com.crewsync.EMS.entity.Batch;
import com.crewsync.EMS.entity.Trainer;
import com.crewsync.EMS.exception.ResourceNotFoundException;
import com.crewsync.EMS.repository.BatchRepository;
import com.crewsync.EMS.repository.TrainerRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BatchServiceImple implements BatchService {

    private final BatchRepository batchRepository;
    private final TrainerRepository trainerRepository;

    @Override
    public BatchDTO createBatch(BatchDTO batchDTO) {

        Batch batch = new Batch();
        batch.setBatchName(batchDTO.getName());
        batch.setCourse(batchDTO.getCourse());
        batch.setMaxStudents(batchDTO.getMaxStudents());
        batch.setStartDate(batchDTO.getStartDate());
        batch.setEndDate(batchDTO.getEndDate());
        batch.setMode(batchDTO.getMode());
        batch.setBatchstatus(batchDTO.getBatchstatus());

        Batch saved = batchRepository.save(batch);

        return new BatchDTO(
                saved.getId(),
                saved.getBatchName(),
                saved.getCourse(),
                saved.getMaxStudents(),
                saved.getStartDate(),
                saved.getEndDate(),
                saved.getMode(),
                saved.getBatchstatus()
        );
    }

    @Override
    public List<BatchDTO> getAllBatches() {

        return batchRepository.findAll()
                .stream()
                .map(b -> new BatchDTO(
                        b.getId(),
                        b.getBatchName(),
                        b.getCourse(),
                        b.getMaxStudents(),
                        b.getStartDate(),
                        b.getEndDate(),
                        b.getMode(),
                        b.getBatchstatus()
                ))
                .toList();
    }

    @Override
    public BatchDTO getBatchById(Long id) {

        Batch batch = batchRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Batch", id));

        return new BatchDTO(
                batch.getId(),
                batch.getBatchName(),
                batch.getCourse(),
                batch.getMaxStudents(),
                batch.getStartDate(),
                batch.getEndDate(),
                batch.getMode(),
                batch.getBatchstatus()
        );
    }
    
    @Override
    public void assignTrainer(Long batchId, Long trainerId) {

        Batch batch = batchRepository.findById(batchId)
                .orElseThrow(() -> new RuntimeException("Batch not found with id " + batchId));

        Trainer trainer = trainerRepository.findById(trainerId)
                .orElseThrow(() -> new RuntimeException("Trainer not found with id " + trainerId));

        batch.setTrainer(trainer);

        batchRepository.save(batch);
    }

    @Override
    public void deleteBatch(Long id) {

        if (!batchRepository.existsById(id)) {
            throw new ResourceNotFoundException("Batch", id);
        }

        batchRepository.deleteById(id);
    }
}