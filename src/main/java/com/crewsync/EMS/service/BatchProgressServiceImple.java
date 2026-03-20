package com.crewsync.EMS.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.crewsync.EMS.dto.BatchProgressDTO;
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
public class BatchProgressServiceImple implements BatchProgressService {

    private final BatchProgressRepository repository;
    private final BatchRepository batchRepository;
    private final TrainerRepository trainerRepository;

    // Helper: entity -> DTO
    private BatchProgressDTO toDTO(BatchProgress p) {
        return new BatchProgressDTO(
                p.getId(),
                p.getTitle(),
                p.getDescription(),
                p.getDocumentUrl(),
                p.getDocumentName(),
                p.getDocumentData(),
                p.getBatch() != null ? p.getBatch().getId() : null,
                p.getTrainer() != null ? p.getTrainer().getId() : null,
                p.getTrainer() != null ? p.getTrainer().getName() : null,
                p.getCreatedAt(),
                p.getUpdatedAt()
        );
    }

    @Override
    public BatchProgressDTO addProgress(BatchProgressDTO dto) {
        BatchProgress progress = new BatchProgress();
        progress.setTitle(dto.getTitle());
        progress.setDescription(dto.getDescription());
        progress.setDocumentUrl(dto.getDocumentUrl());
        progress.setDocumentName(dto.getDocumentName());
        progress.setDocumentData(dto.getDocumentData());
        progress.setDate(LocalDate.now());
        progress.setCreatedAt(dto.getCreatedAt() != null ? dto.getCreatedAt() : java.time.Instant.now().toString());
        progress.setUpdatedAt(dto.getUpdatedAt());

        // Link to batch if batchId provided
        if (dto.getBatchId() != null) {
            Batch batch = batchRepository.findById(dto.getBatchId())
                    .orElseThrow(() -> new ResourceNotFoundException("Batch", dto.getBatchId()));
            progress.setBatch(batch);
        }

        // Link to trainer if trainerId provided
        if (dto.getTrainerId() != null) {
            Trainer trainer = trainerRepository.findById(dto.getTrainerId())
                    .orElseThrow(() -> new ResourceNotFoundException("Trainer", dto.getTrainerId()));
            progress.setTrainer(trainer);
        }

        return toDTO(repository.save(progress));
    }

    @Override
    public List<BatchProgressDTO> getAllProgress() {
        return repository.findAll().stream().map(this::toDTO).toList();
    }

    @Override
    public BatchProgressDTO getProgressById(Long id) {
        return toDTO(repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("BatchProgress", id)));
    }

    @Override
    public void deleteProgress(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("BatchProgress", id);
        }
        repository.deleteById(id);
    }

    @Override
    public BatchProgressDTO updateProgress(Long id, BatchProgressDTO dto) {
        BatchProgress progress = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("BatchProgress", id));
        if (dto.getTitle() != null) progress.setTitle(dto.getTitle());
        if (dto.getDescription() != null) progress.setDescription(dto.getDescription());
        if (dto.getDocumentUrl() != null && !dto.getDocumentUrl().isEmpty()) progress.setDocumentUrl(dto.getDocumentUrl());
        if (dto.getDocumentName() != null && !dto.getDocumentName().isEmpty()) progress.setDocumentName(dto.getDocumentName());
        if (dto.getDocumentData() != null && !dto.getDocumentData().isEmpty()) progress.setDocumentData(dto.getDocumentData());
        // Always set the updatedAt timestamp
        progress.setUpdatedAt(dto.getUpdatedAt() != null ? dto.getUpdatedAt() : java.time.Instant.now().toString());
        return toDTO(repository.save(progress));
    }
}