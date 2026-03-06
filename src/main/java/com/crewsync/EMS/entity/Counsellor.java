package com.crewsync.EMS.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.crewsync.EMS.enums.EmpStatus;


import jakarta.persistence.*;
import lombok.*;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Counsellor {


	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	    
	    private String name;
	    private String email;
	    private String phone;
	    private String password;
	    private LocalDate joiningDate;
	    private BigDecimal salary;
	    
	    @Enumerated(EnumType.STRING)
	    private EmpStatus empstatus;
}
