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

        Analyst savedAnalyst = analystRepository.save(analyst);

        analystDTO.setId(savedAnalyst.getId());
        analystDTO.setName(savedAnalyst.getName());
        analystDTO.setEmail(savedAnalyst.getEmail());

        return analystDTO;
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
                        a.getEmpstatus()
                    ))
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
                analyst.getEmpstatus()
            );
    }

    @Override
    @Transactional
    public void deleteAnalyst(Long id) {

        if (!analystRepository.existsById(id)) {
            throw new ResourceNotFoundException("Analyst", id);
        }

        Analyst analyst = analystRepository.findById(id).get();

        // De-associate from batches (set analyst to null)
        if (analyst.getBatches() != null) {
            for (Batch b : analyst.getBatches()) {
                b.setAnalyst(null);
                batchRepository.save(b);
            }
        }

        analystRepository.deleteById(id);
    }

    @Override
    public AnalystDTO updateAnalyst(Long id, AnalystDTO analystDTO) {
        Analyst analyst = analystRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Analyst", id));
        analyst.setName(analystDTO.getName());
        analyst.setEmail(analystDTO.getEmail());
        if (analystDTO.getPhone() != null) analyst.setPhone(analystDTO.getPhone());
        if (analystDTO.getPassword() != null) analyst.setPassword(analystDTO.getPassword());
        if (analystDTO.getJoiningDate() != null) analyst.setJoiningDate(analystDTO.getJoiningDate());
        if (analystDTO.getSalary() != null) analyst.setSalary(analystDTO.getSalary());
        if (analystDTO.getEmpstatus() != null) analyst.setEmpstatus(analystDTO.getEmpstatus());
        Analyst saved = analystRepository.save(analyst);
        return new AnalystDTO(saved.getId(), saved.getName(), saved.getEmail(), saved.getPhone(),
                saved.getPassword(), saved.getJoiningDate(), saved.getSalary(), saved.getEmpstatus());
    }
}