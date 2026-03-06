package com.crewsync.EMS.entity;

import org.hibernate.engine.jdbc.batch.spi.Batch;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {

	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    private String name;
	    private String email;
	    private String phone;
	    private String password;

	    @ManyToOne
	    @JoinColumn(name = "batch_id")
	    private Batch batch;

	    @ManyToOne
	    @JoinColumn(name = "counsellor_id")
	    private Counsellor counsellor;
}
