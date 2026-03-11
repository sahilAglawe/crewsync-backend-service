package com.crewsync.EMS.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TrainerDTO {

	private Long id;
	private String name;
	private String email;
	private String password;
}
