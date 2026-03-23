package com.crewsync.EMS.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.crewsync.EMS.dto.CounsellorDTO;
import com.crewsync.EMS.entity.Counsellor;
import com.crewsync.EMS.entity.Student;
import com.crewsync.EMS.exception.ResourceNotFoundException;
import com.crewsync.EMS.repository.CounsellorRepository;
import com.crewsync.EMS.repository.StudentRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CounsellorServiceImple implements CounsellorService {
	
    private final CounsellorRepository counsellorRepository;
    private final StudentRepository studentRepository;

    @Override
    public CounsellorDTO createCounsellor(CounsellorDTO counsellorDTO) {
		
        Counsellor counsellor = new Counsellor();
        counsellor.setName(counsellorDTO.getName());
        counsellor.setEmail(counsellorDTO.getEmail());
        counsellor.setPassword(counsellorDTO.getPassword());
        if (counsellorDTO.getPhone() != null) counsellor.setPhone(counsellorDTO.getPhone());
        if (counsellorDTO.getJoiningDate() != null) counsellor.setJoiningDate(counsellorDTO.getJoiningDate());
        if (counsellorDTO.getSalary() != null) counsellor.setSalary(counsellorDTO.getSalary());
        counsellor.setEmpstatus(counsellorDTO.getEmpstatus() != null ? counsellorDTO.getEmpstatus() : com.crewsync.EMS.enums.EmpStatus.ACTIVE);
		
        Counsellor savedCounsellor = counsellorRepository.save(counsellor);
		
        return new CounsellorDTO(savedCounsellor.getId(), savedCounsellor.getName(), savedCounsellor.getEmail(),
                savedCounsellor.getPhone(), savedCounsellor.getPassword(), savedCounsellor.getJoiningDate(),
                savedCounsellor.getSalary(), savedCounsellor.getEmpstatus());
    }

    @Override
    public List<CounsellorDTO> getAllCounsellors() {
        return counsellorRepository.findAll().stream()
                .map(c -> new CounsellorDTO(c.getId(), c.getName(), c.getEmail(), c.getPhone(), c.getPassword(), c.getJoiningDate(), c.getSalary(), c.getEmpstatus()))
                .toList();
    }

    @Override
    public CounsellorDTO getCounsellorById(Long id) {
        Counsellor counsellor = counsellorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Counsellor", id));
		
        return new CounsellorDTO(counsellor.getId(), counsellor.getName(), counsellor.getEmail(), counsellor.getPhone(), counsellor.getPassword(), counsellor.getJoiningDate(), counsellor.getSalary(), counsellor.getEmpstatus());
    }

    @Override
    @Transactional
    public void deleteCounsellor(Long id) {
        if (!counsellorRepository.existsById(id)) {
            throw new ResourceNotFoundException("Counsellor", id);
        }
        Counsellor counsellor = counsellorRepository.findById(id).get();

        // De-associate students from this counsellor
        List<Student> students = studentRepository.findByCounsellor(counsellor);
        for (Student s : students) {
            s.setCounsellor(null);
            studentRepository.save(s);
        }

        counsellorRepository.deleteById(id);
    }

    @Override
    public CounsellorDTO updateCounsellor(Long id, CounsellorDTO counsellorDTO) {
        Counsellor counsellor = counsellorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Counsellor", id));
        counsellor.setName(counsellorDTO.getName());
        counsellor.setEmail(counsellorDTO.getEmail());
        if (counsellorDTO.getPhone() != null) counsellor.setPhone(counsellorDTO.getPhone());
        if (counsellorDTO.getPassword() != null) counsellor.setPassword(counsellorDTO.getPassword());
        if (counsellorDTO.getJoiningDate() != null) counsellor.setJoiningDate(counsellorDTO.getJoiningDate());
        if (counsellorDTO.getSalary() != null) counsellor.setSalary(counsellorDTO.getSalary());
        if (counsellorDTO.getEmpstatus() != null) counsellor.setEmpstatus(counsellorDTO.getEmpstatus());
        Counsellor saved = counsellorRepository.save(counsellor);
        return new CounsellorDTO(saved.getId(), saved.getName(), saved.getEmail(), saved.getPhone(),
                saved.getPassword(), saved.getJoiningDate(), saved.getSalary(), saved.getEmpstatus());
    }
}