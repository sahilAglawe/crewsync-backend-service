package com.crewsync.EMS.entity;

import java.time.LocalDate;
import java.util.List;

import com.crewsync.EMS.enums.BatchStatus;
import com.crewsync.EMS.enums.Mode;

import jakarta.persistence.*;
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
    private int maxStudents;
    private LocalDate startDate;
    private LocalDate endDate;

    @Enumerated(EnumType.STRING)
    private Mode mode;

    @Enumerated(EnumType.STRING)
    private BatchStatus batchstatus;

    // Trainer assigned to batch
    @ManyToOne
    @JoinColumn(name = "trainer_id")
    private Trainer trainer;

    // Analyst who created batch
    @ManyToOne
    @JoinColumn(name = "analyst_id")
    private Analyst analyst;

    // Students in batch
    @OneToMany(mappedBy = "batch", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Student> students;

    // Batch progress
    @OneToMany(mappedBy = "batch", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BatchProgress> progressList;
}
