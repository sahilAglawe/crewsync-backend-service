package com.crewsync.EMS.service;

import java.util.List;

import com.crewsync.EMS.dto.BatchProgressDTO;

public interface BatchProgressService {

	BatchProgressDTO addProgress(BatchProgressDTO dto);

    List<BatchProgressDTO> getAllProgress();

    BatchProgressDTO getProgressById(Long id);

    void deleteProgress(Long id);

    BatchProgressDTO updateProgress(Long id, BatchProgressDTO dto);
}
