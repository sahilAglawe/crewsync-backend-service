package com.crewsync.EMS.entity;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(exclude = {"batch", "trainer"})
public class BatchProgress {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Column(length = 2000)
    private String description;

    private String documentUrl;

    private String documentName;

    @Column(columnDefinition = "LONGTEXT")
    private String documentData;

    private LocalDate date;

    private String createdAt;

    private String updatedAt;

    // Select batch from batch table
    @ManyToOne
    @JoinColumn(name = "batch_id")
    private Batch batch;

    // Trainer who added progress
    @ManyToOne
    @JoinColumn(name = "trainer_id")
    private Trainer trainer;
}