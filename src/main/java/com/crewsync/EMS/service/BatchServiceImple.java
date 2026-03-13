package com.crewsync.EMS.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crewsync.EMS.dto.BatchDTO;
import com.crewsync.EMS.entity.Batch;
import com.crewsync.EMS.repository.BatchRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BatchServiceImple implements BatchService {
	
	@Autowired
	private final BatchRepository batchRepository;

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
	                .collect(Collectors.toList());
	}

	@Override
	public BatchDTO getBatchById(Long id) {
		Batch b = batchRepository.findById(id).orElseThrow();

        return new BatchDTO(
                b.getId(),
                b.getBatchName(),
                b.getCourse(),
                b.getMaxStudents(),
                b.getStartDate(),
                b.getEndDate(),
                b.getMode(),
                b.getBatchstatus()
        );
	}

	@Override
	public void deleteBatch(Long id) {
		
		batchRepository.deleteById(id);
	}

}
