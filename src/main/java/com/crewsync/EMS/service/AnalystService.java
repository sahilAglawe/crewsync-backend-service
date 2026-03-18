package com.crewsync.EMS.service;

import java.util.List;

import com.crewsync.EMS.dto.AnalystDTO;

public interface AnalystService {

	    AnalystDTO createAnalyst(AnalystDTO analystDTO);

	    List<AnalystDTO> getAllAnalysts();

	    AnalystDTO getAnalystById(Long id);

	    void deleteAnalyst(Long id);

	    AnalystDTO updateAnalyst(Long id, AnalystDTO analystDTO);
}
