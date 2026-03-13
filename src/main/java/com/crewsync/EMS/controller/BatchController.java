package com.crewsync.EMS.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.crewsync.EMS.dto.BatchDTO;
import com.crewsync.EMS.service.BatchService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/batches")
@RequiredArgsConstructor
public class BatchController {

	private final BatchService batchService;
	
	 @PostMapping
	    public BatchDTO createBatch(@RequestBody BatchDTO batchDTO) {
	        return batchService.createBatch(batchDTO);
	    }
	    
	    @GetMapping
	    public List<BatchDTO> getAllBatches() {
	        return batchService.getAllBatches();
	    }

	    @GetMapping("/{id}")
	    public BatchDTO getBatchById(@PathVariable Long id) {
	        return batchService.getBatchById(id);
	    }

	    @DeleteMapping("/{id}")
	    public void deleteBatch(@PathVariable Long id) {
	        batchService.deleteBatch(id);
	    }
}
