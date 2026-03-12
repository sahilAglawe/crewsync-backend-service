package com.crewsync.EMS.service;

import java.util.List;

import com.crewsync.EMS.dto.BatchDTO;

public interface BatchService {

	    BatchDTO createBatch(BatchDTO batchDTO);

	    List<BatchDTO> getAllBatches();

	    BatchDTO getBatchById(Long id);

	    void deleteBatch(Long id);
}
