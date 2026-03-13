package com.crewsync.EMS.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.crewsync.EMS.dto.AnalystDTO;

import com.crewsync.EMS.service.AnalystService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/analysts")
@RequiredArgsConstructor
public class AnalystController {

	private final AnalystService analystService;
	
	@PostMapping
	public AnalystDTO createAnalyst(@RequestBody AnalystDTO analystDTO) {
		return analystService.createAnalyst(analystDTO);
	}
	
	@GetMapping
    public List<AnalystDTO> getAllAnalysts() {
        return analystService.getAllAnalysts();
    }

	 @GetMapping("/{id}")
	    public AnalystDTO getAnalystById(@PathVariable Long id) {
	        return analystService.getAnalystById(id);
	    }
	 
	 @DeleteMapping("/{id}")
	    public void deleteAnalyst(@PathVariable Long id) {
	        analystService.deleteAnalyst(id);
	    }
}