package com.crewsync.EMS.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.crewsync.EMS.dto.BatchProgressDTO;
import com.crewsync.EMS.entity.BatchProgress;
import com.crewsync.EMS.exception.ResourceNotFoundException;
import com.crewsync.EMS.repository.BatchProgressRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BatchProgressServiceImple implements BatchProgressService {

    private final BatchProgressRepository repository;

    @Override
    public BatchProgressDTO addProgress(BatchProgressDTO dto) {

        BatchProgress progress = new BatchProgress();
        progress.setTitle(dto.getTitle());
        progress.setDescription(dto.getDescription());
        progress.setDocumentUrl(dto.getDocumentUrl());

        BatchProgress saved = repository.save(progress);

        return new BatchProgressDTO(
                saved.getId(),
                saved.getTitle(),
                saved.getDescription(),
                saved.getDocumentUrl()
        );
    }

    @Override
    public List<BatchProgressDTO> getAllProgress() {

        return repository.findAll()
                .stream()
                .map(p -> new BatchProgressDTO(
                        p.getId(),
                        p.getTitle(),
                        p.getDescription(),
                        p.getDocumentUrl()
                ))
                .toList();
    }

    @Override
    public BatchProgressDTO getProgressById(Long id) {

        BatchProgress progress = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("BatchProgress", id));

        return new BatchProgressDTO(
                progress.getId(),
                progress.getTitle(),
                progress.getDescription(),
                progress.getDocumentUrl()
        );
    }

    @Override
    public void deleteProgress(Long id) {

        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("BatchProgress", id);
        }

        repository.deleteById(id);
    }
}