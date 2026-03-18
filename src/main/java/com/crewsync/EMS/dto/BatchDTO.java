package com.crewsync.EMS.dto;

import java.time.LocalDate;

import com.crewsync.EMS.enums.BatchStatus;
import com.crewsync.EMS.enums.Mode;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BatchDTO {

	private Long id;
	private String name;       // used internally for set/get
	private String batchName;  // alias sent to frontend
	private String course;
	private Integer maxStudents;
	private LocalDate startDate;
	private LocalDate endDate;
	private Mode mode;
	private BatchStatus batchstatus;
	private Long trainerId;
	private Long analystId;
}
