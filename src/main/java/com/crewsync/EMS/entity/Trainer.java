package com.crewsync.EMS.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.hibernate.engine.jdbc.batch.spi.Batch;

import ch.qos.logback.core.status.Status;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Trainer {

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
	    private Status status;
	    
	    @OneToMany(mappedBy = "trainer")
	    private List<Batch> batches;

	    @OneToMany(mappedBy = "trainer")
	    private List<BatchProgress> progressList;
}
