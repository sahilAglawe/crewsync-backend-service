package com.crewsync.EMS.dto;

import lombok.*;

@Data
public class BatchProgressDTO {

	private Long id;
	private Long batchId; 
	private String title;
	private String description; 
	private String documentUrl;
}
