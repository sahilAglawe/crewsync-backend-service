package com.crewsync.EMS.dto;

import java.time.LocalDate;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BatchDTO {

	private Long id;
	private String name; 
	private String course; 
	private Long trainerId; 
	private Integer maxStudents; 
	private LocalDate startDate;
	private LocalDate endDate; 
	private String mode;
	private String status;
}
