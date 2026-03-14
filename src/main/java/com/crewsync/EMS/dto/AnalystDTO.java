package com.crewsync.EMS.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.crewsync.EMS.enums.EmpStatus;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnalystDTO {
	
	private Long id;
    private String name;
    private String email;
    private String phone;
    private String password;
    private LocalDate joiningDate;
    private BigDecimal salary;
    private EmpStatus empstatus;

}
