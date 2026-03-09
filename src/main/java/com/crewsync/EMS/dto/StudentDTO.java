package com.crewsync.EMS.dto;

import lombok.*;

@Data
public class StudentDTO {

	private Long id;
	private String name;
	private String email;
	private String password;
	
	private long batchId;
}
