package com.crewsync.EMS.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crewsync.EMS.dto.BatchDTO;
import com.crewsync.EMS.service.BatchService;


import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/analysts")
@RequiredArgsConstructor
public class AnalystController {

	private final BatchService batchService;

	@PostMapping("/batches")
	public BatchDTO createBatch(@RequestBody BatchDTO batchDTO) {
		return batchService.createBatch(batchDTO);
	}

	@GetMapping("/batches")
	public List<BatchDTO> getAllBatches() {
		return batchService.getAllBatches();
	}

	@GetMapping("/batches/{id}")
	public BatchDTO getBatch(@PathVariable Long id) {
		return batchService.getBatchById(id);
	}

	@PutMapping("/batches/{batchId}/assign-trainer/{trainerId}")
	public String assignTrainer(
			@PathVariable Long batchId,
			@PathVariable Long trainerId) {

		batchService.assignTrainer(batchId, trainerId);
		return "Trainer assigned successfully";
	}

	@PutMapping("/batches/{id}")
	public BatchDTO updateBatch(@PathVariable Long id, @RequestBody BatchDTO batchDTO) {
		return batchService.updateBatch(id, batchDTO);
	}

	@DeleteMapping("/batches/{id}")
	public String deleteBatch(@PathVariable Long id) {
		batchService.deleteBatch(id);
		return "Batch deleted successfully";
	}

}
