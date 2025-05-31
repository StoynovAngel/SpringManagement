package com.angel.uni.management.service.impl;

import com.angel.uni.management.dto.SubjectDTO;
import com.angel.uni.management.entity.UniversitySubject;
import com.angel.uni.management.exceptions.ResourceNotFoundException;
import com.angel.uni.management.mapper.subject.SubjectMapper;
import com.angel.uni.management.repositories.USubjectRepository;
import com.angel.uni.management.service.USubjectService;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class USubjectServiceImpl implements USubjectService {

    private final SubjectMapper subjectMapper;
    private final USubjectRepository subjectRepository;

    @Autowired
    public USubjectServiceImpl(SubjectMapper subjectMapper, USubjectRepository subjectRepository) {
        this.subjectMapper = subjectMapper;
        this.subjectRepository = subjectRepository;
    }

    @Override
    public SubjectDTO createSubject(SubjectDTO subjectDTO) throws BadRequestException {
        if (subjectDTO == null) throw new BadRequestException("Cannot create new subject. SubjectDTO is null.");
        UniversitySubject subject = subjectMapper.toEntity(subjectDTO);
        UniversitySubject savedSubject = subjectRepository.save(subject);
        return subjectMapper.toDTO(savedSubject);
    }

    @Override
    public SubjectDTO getSubjectByName(String name) throws BadRequestException {
        if (name.isEmpty()) throw new BadRequestException("Cannot find the subject. Subject cannot be empty.");
        UniversitySubject subject = subjectRepository.findByName(name);
        if (subject == null) throw new ResourceNotFoundException("Couldn't find a subject with this name: " + name);
        return subjectMapper.toDTO(subject);
    }

    @Override
    public List<SubjectDTO> getAllSubjects() {
        List<UniversitySubject> subjects = subjectRepository.findAll();
        if (subjects.isEmpty()) throw new ResourceNotFoundException("Subjects' list is empty.");
        return subjects.stream().map(subjectMapper::toDTO).toList();
    }

    @Override
    public SubjectDTO updateSubject(Long id, SubjectDTO subjectDTO) throws BadRequestException {
        if (subjectDTO == null) throw new BadRequestException("Cannot update the subject. SubjectDTO is null.");
        UniversitySubject subject = subjectRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Could not find a subject with this id: " + id));
        subjectMapper.updateEntityFromDTO(subjectDTO, subject);
        UniversitySubject updated = subjectRepository.save(subject);
        return subjectMapper.toDTO(updated);
    }
}
