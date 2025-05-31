package com.angel.uni.management.service.impl;

import com.angel.uni.management.dto.subject.SubjectRequestDTO;
import com.angel.uni.management.dto.subject.SubjectResponseDTO;
import com.angel.uni.management.entity.Student;
import com.angel.uni.management.entity.Teacher;
import com.angel.uni.management.entity.UniversitySubject;
import com.angel.uni.management.exceptions.ResourceNotFoundException;
import com.angel.uni.management.mapper.student.StudentResponseEntityMapper;
import com.angel.uni.management.mapper.subject.SubjectMapper;
import com.angel.uni.management.repositories.StudentRepository;
import com.angel.uni.management.repositories.TeacherRepository;
import com.angel.uni.management.repositories.UniversitySubjectRepository;
import com.angel.uni.management.service.UniversitySubjectService;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UniversitySubjectServiceImpl implements UniversitySubjectService {

    private final UniversitySubjectRepository universitySubjectRepository;
    private final SubjectMapper subjectMapper;
    private final TeacherRepository teacherRepository;
    private final StudentRepository studentRepository;
    private final StudentResponseEntityMapper studentResponseEntityMapper;

    @Autowired
    public UniversitySubjectServiceImpl
            (UniversitySubjectRepository universitySubjectRepository,
             SubjectMapper subjectMapper,
             TeacherRepository teacherRepository,
             StudentRepository studentRepository,
             StudentResponseEntityMapper studentResponseEntityMapper) {
        this.universitySubjectRepository = universitySubjectRepository;
        this.subjectMapper = subjectMapper;
        this.teacherRepository = teacherRepository;
        this.studentRepository = studentRepository;
        this.studentResponseEntityMapper = studentResponseEntityMapper;
    }

    @Override
    public SubjectRequestDTO createSubject(SubjectRequestDTO subjectDTO) throws BadRequestException {

        if (subjectDTO == null) {
            throw new BadRequestException("Cannot create subject: SubjectDTO is null.");
        }
        Teacher teacher = teacherRepository.findById(subjectDTO.teacherId()).orElseThrow(() -> new ResourceNotFoundException("Teacher not found with this id: " + subjectDTO.teacherId()));
        List<Student> students = studentRepository.findAllById(subjectDTO.studentIds());
        UniversitySubject universitySubject = subjectMapper.requestToEntity(subjectDTO);
        universitySubject.setTeacher(teacher);
        universitySubject.setStudentsAssignedToSubject(students);
        UniversitySubject saved = universitySubjectRepository.save(universitySubject);

        return subjectMapper.requestToDTO(saved);
    }

    @Override
    public SubjectResponseDTO getSubjectById(Long id) {
        UniversitySubject subject = universitySubjectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Subject not found with this id: " + id));
        return subjectMapper.responseToDTO(subject);
    }

    @Override
    public List<SubjectResponseDTO> getAllSubjects() {
        List<UniversitySubject> subjects = universitySubjectRepository.findAll();
        if (subjects.isEmpty()) {
            throw new ResourceNotFoundException("No subjects found.");
        }
        return subjects.stream().map(subjectMapper::responseToDTO).collect(Collectors.toList());
    }

    @Override
    public SubjectResponseDTO updateSubject(SubjectResponseDTO subjectDTO) throws BadRequestException {
        if (subjectDTO == null) {
            throw new BadRequestException("Subject cannot be updated: SubjectDTO not null.");
        }
        UniversitySubject subjectToBeEdited = universitySubjectRepository.findById(
                subjectDTO.id()).orElseThrow(() -> new ResourceNotFoundException("Subject with this id is not found: " + subjectDTO.id())
        );
        Teacher teacher = teacherRepository.findById(
                subjectDTO.teacherId()).orElseThrow(() -> new ResourceNotFoundException("Teacher not found with this id: " + subjectDTO.teacherId())
        );
        subjectToBeEdited.setName(subjectDTO.name());
        subjectToBeEdited.setHoursPerWeek(subjectDTO.hoursPerWeek());
        subjectToBeEdited.setDescription(subjectDTO.description());
        subjectToBeEdited.setTeacher(teacher);
        subjectToBeEdited.setStudentsAssignedToSubject(
                subjectDTO.students().stream().map(studentResponseEntityMapper).collect(Collectors.toList())
        );
        return subjectMapper.responseToDTO(subjectToBeEdited);
    }
}
