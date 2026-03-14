package com.crewsync.EMS.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.crewsync.EMS.dto.StudentDTO;
import com.crewsync.EMS.entity.Batch;
import com.crewsync.EMS.entity.Counsellor;
import com.crewsync.EMS.entity.Student;
import com.crewsync.EMS.exception.ResourceNotFoundException;
import com.crewsync.EMS.repository.BatchRepository;
import com.crewsync.EMS.repository.CounsellorRepository;
import com.crewsync.EMS.repository.StudentRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StudentServiceImple implements StudentService {

    private final StudentRepository studentRepository;
    private final BatchRepository batchRepository;
    private final CounsellorRepository counsellorRepository;

    @Override
    public StudentDTO createStudent(StudentDTO studentDTO) {

        Batch batch = batchRepository.findById(studentDTO.getBatchId())
                .orElseThrow(() -> new ResourceNotFoundException("Batch", studentDTO.getBatchId()));

        Counsellor counsellor = counsellorRepository.findById(studentDTO.getCounsellorId())
                .orElseThrow(() -> new ResourceNotFoundException("Counsellor", studentDTO.getCounsellorId()));

        Student student = new Student();
        student.setName(studentDTO.getName());
        student.setEmail(studentDTO.getEmail());
        student.setPassword(studentDTO.getPassword());
        student.setPhone(studentDTO.getPhone());
        student.setBatch(batch);
        student.setCounsellor(counsellor);

        Student savedStudent = studentRepository.save(student);

        studentDTO.setId(savedStudent.getId());
        studentDTO.setName(savedStudent.getName());
        studentDTO.setEmail(savedStudent.getEmail());
        studentDTO.setPhone(savedStudent.getPhone());
        studentDTO.setBatchId(savedStudent.getBatch().getId());
        studentDTO.setCounsellorId(savedStudent.getCounsellor().getId());
        studentDTO.setPassword(null);

        return studentDTO;
    }

    @Override
    public List<StudentDTO> getAllStudents() {

        return studentRepository.findAll()
                .stream()
                .map(s -> {
                    StudentDTO dto = new StudentDTO();
                    dto.setId(s.getId());
                    dto.setName(s.getName());
                    dto.setEmail(s.getEmail());
                    dto.setPhone(s.getPhone());
                    dto.setBatchId(s.getBatch().getId());
                    dto.setCounsellorId(s.getCounsellor().getId());
                    dto.setPassword(null);
                    return dto;
                })
                .toList();
    }

    @Override
    public StudentDTO getStudentById(Long id) {

        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student", id));

        return new StudentDTO(
                student.getId(),
                student.getName(),
                student.getEmail(),
                student.getPhone(),
                null,
                student.getBatch().getId(),
                student.getCounsellor().getId()
        );
    }

    @Override
    public void deleteStudent(Long id) {

        if (!studentRepository.existsById(id)) {
            throw new ResourceNotFoundException("Student", id);
        }

        studentRepository.deleteById(id);
    }
}