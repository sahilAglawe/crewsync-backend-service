package com.crewsync.EMS.controller;

import com.crewsync.EMS.dto.BatchDTO;
import com.crewsync.EMS.dto.BatchProgressDTO;
import com.crewsync.EMS.entity.BatchProgress;
import com.crewsync.EMS.service.BatchProgressService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/batchprogress")
@RequiredArgsConstructor
public class BatchProgressController {


    private final BatchProgressService batchProgressService;


    @PostMapping
    public BatchProgressDTO createBatchProgress(@RequestBody BatchProgressDTO batchProgressDTO) {
        return batchProgressService.addProgress(batchProgressDTO);
    }

    @GetMapping
    public List<BatchProgressDTO> getAllProgress() {
        return batchProgressService.getAllProgress();
    }

    @GetMapping("/{id}")
    public BatchProgressDTO getProgressById(@PathVariable Long id) {
        return batchProgressService.getProgressById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteProgress(@PathVariable Long id) {
        batchProgressService.deleteProgress(id);
    }

}
