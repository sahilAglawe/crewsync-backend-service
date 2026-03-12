package com.crewsync.EMS.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BatchProgressDTO {

	private Long id;
	private String title;
	private String description; 
	private String documentUrl;
}
