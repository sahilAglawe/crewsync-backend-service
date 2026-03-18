package com.crewsync.EMS.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentDTO {

	private Long id;
	private String name;
	private String email;
	private String phone;
	private String password;
	private String course;
	private Long counsellorId;
	private Long batchId;
	private String batchName;
}
