package com.crewsync.EMS.entity;

import java.util.List;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Batch {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String batchName;
    private String course;
    private String startDate;
    private String endDate;

    @ManyToOne
    @JoinColumn(name = "analyst_id")
    private Analyst analyst;

    @ManyToOne
    @JoinColumn(name = "trainer_id")
    private Trainer trainer;

    @OneToMany(mappedBy = "batch")
    private List<Student> students;

    @OneToMany(mappedBy = "batch")
    private List<BatchProgress> progressList;
}
