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

    // Helper: entity -> DTO (null-safe for optional relations)
    private StudentDTO toDTO(Student s) {
        return new StudentDTO(
                s.getId(),
                s.getName(),
                s.getEmail(),
                s.getPhone(),
                null,   // never return password
                s.getCourse(),
                s.getCounsellor() != null ? s.getCounsellor().getId() : null,
                s.getBatch() != null ? s.getBatch().getId() : null,
                s.getBatch() != null ? s.getBatch().getBatchName() : null
        );
    }

    @Override
    public StudentDTO createStudent(StudentDTO studentDTO) {
        Student student = new Student();
        student.setName(studentDTO.getName());
        student.setEmail(studentDTO.getEmail());
        student.setPassword(studentDTO.getPassword() != null ? studentDTO.getPassword() : "default123");
        student.setPhone(studentDTO.getPhone());
        student.setCourse(studentDTO.getCourse());

        // Optionally link to batch
        if (studentDTO.getBatchId() != null) {
            Batch batch = batchRepository.findById(studentDTO.getBatchId())
                    .orElseThrow(() -> new ResourceNotFoundException("Batch", studentDTO.getBatchId()));
            student.setBatch(batch);
        }

        // Optionally link to counsellor
        if (studentDTO.getCounsellorId() != null) {
            Counsellor counsellor = counsellorRepository.findById(studentDTO.getCounsellorId())
                    .orElseThrow(() -> new ResourceNotFoundException("Counsellor", studentDTO.getCounsellorId()));
            student.setCounsellor(counsellor);
        }

        return toDTO(studentRepository.save(student));
    }

    @Override
    public List<StudentDTO> getAllStudents() {
        return studentRepository.findAll().stream().map(this::toDTO).toList();
    }

    @Override
    public StudentDTO getStudentById(Long id) {
        return toDTO(studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student", id)));
    }

    @Override
    public void deleteStudent(Long id) {
        if (!studentRepository.existsById(id)) {
            throw new ResourceNotFoundException("Student", id);
        }
        studentRepository.deleteById(id);
    }

    @Override
    public StudentDTO updateStudent(Long id, StudentDTO studentDTO) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student", id));
        if (studentDTO.getName() != null) student.setName(studentDTO.getName());
        if (studentDTO.getEmail() != null) student.setEmail(studentDTO.getEmail());
        if (studentDTO.getPhone() != null) student.setPhone(studentDTO.getPhone());
        if (studentDTO.getCourse() != null) student.setCourse(studentDTO.getCourse());
        if (studentDTO.getPassword() != null && !studentDTO.getPassword().isBlank()) {
            student.setPassword(studentDTO.getPassword());
        }
        if (studentDTO.getBatchId() != null) {
            Batch batch = batchRepository.findById(studentDTO.getBatchId())
                    .orElseThrow(() -> new ResourceNotFoundException("Batch", studentDTO.getBatchId()));
            student.setBatch(batch);
        }
        if (studentDTO.getCounsellorId() != null) {
            Counsellor counsellor = counsellorRepository.findById(studentDTO.getCounsellorId())
                    .orElseThrow(() -> new ResourceNotFoundException("Counsellor", studentDTO.getCounsellorId()));
            student.setCounsellor(counsellor);
        }
        return toDTO(studentRepository.save(student));
    }
}