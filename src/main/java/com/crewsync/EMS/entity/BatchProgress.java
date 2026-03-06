package com.crewsync.EMS.entity;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BatchProgress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Column(length = 2000)
    private String description;

    private String documentUrl;

    private LocalDate date;

    // Select batch from batch table
    @ManyToOne
    @JoinColumn(name = "batch_id")
    private Batch batch;

    // Trainer who added progress
    @ManyToOne
    @JoinColumn(name = "trainer_id")
    private Trainer trainer;
}