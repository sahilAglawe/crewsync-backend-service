package com.crewsync.EMS.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.crewsync.EMS.dto.AnalystDTO;
import com.crewsync.EMS.entity.Analyst;
import com.crewsync.EMS.entity.Batch;
import com.crewsync.EMS.exception.ResourceNotFoundException;
import com.crewsync.EMS.repository.AnalystRepository;
import com.crewsync.EMS.repository.BatchRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AnalystServiceImple implements AnalystService {

    private final AnalystRepository analystRepository;
    private final BatchRepository batchRepository;

    @Override
    public AnalystDTO createAnalyst(AnalystDTO analystDTO) {

        Analyst analyst = new Analyst();
        analyst.setName(analystDTO.getName());
        analyst.setEmail(analystDTO.getEmail());
        analyst.setPassword(analystDTO.getPassword());
        if (analystDTO.getPhone() != null)
            analyst.setPhone(analystDTO.getPhone());
        if (analystDTO.getJoiningDate() != null)
            analyst.setJoiningDate(analystDTO.getJoiningDate());
        if (analystDTO.getSalary() != null)
            analyst.setSalary(analystDTO.getSalary());
        analyst.setEmpstatus(analystDTO.getEmpstatus() != null ? analystDTO.getEmpstatus()
                : com.crewsync.EMS.enums.EmpStatus.ACTIVE);

        Analyst savedAnalyst = analystRepository.save(analyst);

        return new AnalystDTO(savedAnalyst.getId(), savedAnalyst.getName(), savedAnalyst.getEmail(),
                savedAnalyst.getPhone(), savedAnalyst.getPassword(), savedAnalyst.getJoiningDate(),
                savedAnalyst.getSalary(), savedAnalyst.getEmpstatus());
    }

    @Override
    public List<AnalystDTO> getAllAnalysts() {

        return analystRepository.findAll()
                .stream()
                .map(a -> new AnalystDTO(
                        a.getId(),
                        a.getName(),
                        a.getEmail(),
                        a.getPhone(),
                        a.getPassword(),
                        a.getJoiningDate(),
                        a.getSalary(),
                        a.getEmpstatus()))
                .toList();
    }

    @Override
    public AnalystDTO getAnalystById(Long id) {

        Analyst analyst = analystRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Analyst", id));

        return new AnalystDTO(
                analyst.getId(),
                analyst.getName(),
                analyst.getEmail(),
                analyst.getPhone(),
                analyst.getPassword(),
                analyst.getJoiningDate(),
                analyst.getSalary(),
                analyst.getEmpstatus());
    }

    @Override
    @Transactional
    public void deleteAnalyst(Long id) {

        if (!analystRepository.existsById(id)) {
            throw new ResourceNotFoundException("Analyst", id);
        }

        // De-associate from batches using repository query (avoids lazy loading issues)
        List<Batch> batches = batchRepository.findByAnalystId(id);
        for (Batch b : batches) {
            b.setAnalyst(null);
            batchRepository.save(b);
        }

        analystRepository.deleteById(id);
    }

    @Override
    @Transactional
    public AnalystDTO updateAnalyst(Long id, AnalystDTO analystDTO) {
        Analyst analyst = analystRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Analyst", id));
        if (analystDTO.getName() != null) analyst.setName(analystDTO.getName());
        if (analystDTO.getEmail() != null) analyst.setEmail(analystDTO.getEmail());
        if (analystDTO.getPhone() != null) analyst.setPhone(analystDTO.getPhone());
        if (analystDTO.getPassword() != null && !analystDTO.getPassword().isBlank()) {
            analyst.setPassword(analystDTO.getPassword());
        }
        if (analystDTO.getJoiningDate() != null) analyst.setJoiningDate(analystDTO.getJoiningDate());
        if (analystDTO.getSalary() != null) analyst.setSalary(analystDTO.getSalary());
        if (analystDTO.getEmpstatus() != null) analyst.setEmpstatus(analystDTO.getEmpstatus());
        Analyst saved = analystRepository.save(analyst);
        return new AnalystDTO(saved.getId(), saved.getName(), saved.getEmail(), saved.getPhone(),
                saved.getPassword(), saved.getJoiningDate(), saved.getSalary(), saved.getEmpstatus());
    }
}