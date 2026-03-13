package com.crewsync.EMS.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.crewsync.EMS.dto.BatchProgressDTO;
import com.crewsync.EMS.entity.BatchProgress;
import com.crewsync.EMS.repository.BatchProgressRepository;
import com.crewsync.EMS.repository.BatchRepository;

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
                .collect(Collectors.toList());
	}

	@Override
	public BatchProgressDTO getProgressById(Long id) {
		
		BatchProgress p = repository.findById(id).orElseThrow();

        return new BatchProgressDTO(
                p.getId(),
                p.getTitle(),
                p.getDescription(),
                p.getDocumentUrl()
        );
	}

	@Override
	public void deleteProgress(Long id) {
		
		repository.deleteById(id);

	}

}