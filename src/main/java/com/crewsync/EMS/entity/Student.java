package com.crewsync.EMS.entity;



import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(exclude = {"batch", "counsellor"})
public class Student {

	    @Id
	    @EqualsAndHashCode.Include
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    private String name;
	    private String email;
	    private String phone;
	    private String password;
	    private String course;

	    @ManyToOne
	    @JoinColumn(name = "batch_id")
	    private Batch batch;

	    @ManyToOne
	    @JoinColumn(name = "counsellor_id")
	    private Counsellor counsellor;
}
