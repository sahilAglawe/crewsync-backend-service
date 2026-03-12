package com.crewsync.EMS.service;

import java.util.List;

import com.crewsync.EMS.dto.AdminDTO;
import com.crewsync.EMS.dto.CounsellorDTO;
import com.crewsync.EMS.dto.StudentDTO;
import com.crewsync.EMS.entity.Batch;
import com.crewsync.EMS.entity.Counsellor;
import com.crewsync.EMS.entity.Student;
import com.crewsync.EMS.repository.BatchRepository;
import com.crewsync.EMS.repository.CounsellorRepository;
import com.crewsync.EMS.repository.StudentRepository;

public class StudentServiceImple implements StudentService {
	
	private final StudentRepository studentRepository = null;
    private final BatchRepository batchRepository = null;
	
	@Override
	public StudentDTO createStudent(StudentDTO studentDTO) {
		Student student = new Student();

		student.setName(studentDTO.getName());
		student.setEmail(studentDTO.getEmail());
		student.setPassword(studentDTO.getPassword());

		// fetch batch from DB using batchId
		Batch batch = batchRepository.findById(studentDTO.getBatchId()).orElseThrow();

		student.setBatch(batch);

		student = studentRepository.save(student);

		studentDTO.setId(student.getId());

		return studentDTO;
	}

	@Override
	public List<StudentDTO> getAllStudents() {
		return studentRepository.findAll()
                .stream()
                .map(s -> new StudentDTO(s.getId(), s.getName(), s.getEmail(), s.getPassword(), s.getBatch().getId()))
                .toList();
	}

	@Override
	public StudentDTO getStudentById(Long id) {
		
        Student student = studentRepository.findById(id).orElseThrow();
		
		return new StudentDTO(student.getId(), student.getName(), student.getEmail(), student.getPassword(), student.getBatch().getId());
	}

	@Override
	public void deleteStudent(Long id) {
		
		studentRepository.deleteById(id);
	}

}
