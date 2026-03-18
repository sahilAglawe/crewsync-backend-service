package com.crewsync.EMS.service;

import java.util.List;

import com.crewsync.EMS.dto.BatchDTO;

public interface BatchService {

	    BatchDTO createBatch(BatchDTO batchDTO);

	    List<BatchDTO> getAllBatches();

	    BatchDTO getBatchById(Long id);
	    
	    void assignTrainer(Long batchId, Long trainerId);

	    void deleteBatch(Long id);

	    BatchDTO updateBatch(Long id, BatchDTO batchDTO);
}
