package com.crewsync.EMS.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crewsync.EMS.dto.BatchProgressDTO;
import com.crewsync.EMS.service.BatchProgressService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/trainers")
@RequiredArgsConstructor
public class TrainerController {

	 private final BatchProgressService progressService;
	 
	  @PostMapping("/progress")
	    public BatchProgressDTO addProgress(@RequestBody BatchProgressDTO dto) {
	        return progressService.addProgress(dto);
	    }

	    @GetMapping("/progress")
	    public List<BatchProgressDTO> getAllProgress() {
	        return progressService.getAllProgress();
	    }

	    @GetMapping("/progress/{id}")
	    public BatchProgressDTO getProgress(@PathVariable Long id) {
	        return progressService.getProgressById(id);
	    }

	    @DeleteMapping("/progress/{id}")
	    public String deleteProgress(@PathVariable Long id) {
	        progressService.deleteProgress(id);
	        return "Progress deleted";
	    }

}
