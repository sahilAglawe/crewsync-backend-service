package com.crewsync.EMS.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    // Helper to map Batch entity → BatchDTO (includes trainerId, analystId, batchName, trainer name)
    private BatchDTO toDTO(Batch b) {
        BatchDTO dto = new BatchDTO();
        dto.setId(b.getId());
        dto.setName(b.getBatchName());
        dto.setBatchName(b.getBatchName());
        dto.setCourse(b.getCourse());
        dto.setMaxStudents(b.getMaxStudents());
        dto.setStartDate(b.getStartDate());
        dto.setEndDate(b.getEndDate());
        dto.setMode(b.getMode());
        dto.setBatchstatus(b.getBatchstatus());
        dto.setTrainerId(b.getTrainer() != null ? b.getTrainer().getId() : null);
        dto.setTrainer(b.getTrainer() != null ? b.getTrainer().getName() : null);
        dto.setAnalystId(b.getAnalyst() != null ? b.getAnalyst().getId() : null);
        dto.setStudentsEnrolled(b.getStudents() != null ? b.getStudents().size() : 0);
        return dto;
    }

    @Override
    public BatchDTO createBatch(BatchDTO batchDTO) {
        Batch batch = new Batch();
        batch.setBatchName(batchDTO.getName() != null ? batchDTO.getName() : batchDTO.getBatchName());
        batch.setCourse(batchDTO.getCourse());
        batch.setMaxStudents(batchDTO.getMaxStudents() != null ? batchDTO.getMaxStudents() : 0);
        batch.setStartDate(batchDTO.getStartDate());
        batch.setEndDate(batchDTO.getEndDate());
        batch.setMode(batchDTO.getMode());
        batch.setBatchstatus(batchDTO.getBatchstatus());
        return toDTO(batchRepository.save(batch));
    }

    @Override
    @Transactional(readOnly = true)
    public List<BatchDTO> getAllBatches() {
        return batchRepository.findAll().stream().map(this::toDTO).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public BatchDTO getBatchById(Long id) {
        return toDTO(batchRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Batch", id)));
    }

    @Override
    public void assignTrainer(Long batchId, Long trainerId) {
        Batch batch = batchRepository.findById(batchId)
                .orElseThrow(() -> new ResourceNotFoundException("Batch", batchId));
        Trainer trainer = trainerRepository.findById(trainerId)
                .orElseThrow(() -> new ResourceNotFoundException("Trainer", trainerId));
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

    @Override
    public BatchDTO updateBatch(Long id, BatchDTO batchDTO) {
        Batch batch = batchRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Batch", id));
        String newName = batchDTO.getName() != null ? batchDTO.getName() : batchDTO.getBatchName();
        if (newName != null) batch.setBatchName(newName);
        if (batchDTO.getCourse() != null) batch.setCourse(batchDTO.getCourse());
        if (batchDTO.getMaxStudents() != null && batchDTO.getMaxStudents() > 0) batch.setMaxStudents(batchDTO.getMaxStudents());
        if (batchDTO.getStartDate() != null) batch.setStartDate(batchDTO.getStartDate());
        if (batchDTO.getEndDate() != null) batch.setEndDate(batchDTO.getEndDate());
        if (batchDTO.getMode() != null) batch.setMode(batchDTO.getMode());
        if (batchDTO.getBatchstatus() != null) batch.setBatchstatus(batchDTO.getBatchstatus());
        // Update trainer assignment if trainer ID provided
        if (batchDTO.getTrainerId() != null) {
            Trainer trainer = trainerRepository.findById(batchDTO.getTrainerId())
                    .orElseThrow(() -> new ResourceNotFoundException("Trainer", batchDTO.getTrainerId()));
            batch.setTrainer(trainer);
        }
        return toDTO(batchRepository.save(batch));
    }
}