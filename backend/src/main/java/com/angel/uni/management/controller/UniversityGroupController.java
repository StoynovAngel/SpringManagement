package com.angel.uni.management.controller;

import com.angel.uni.management.dto.group.GroupRequestDTO;
import com.angel.uni.management.dto.group.GroupResponseDTO;
import com.angel.uni.management.service.UniversityGroupService;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/group")
public class UniversityGroupController {

    private final UniversityGroupService universityGroupService;

    @Autowired
    public UniversityGroupController(UniversityGroupService universityGroupService) {
        this.universityGroupService = universityGroupService;
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public GroupRequestDTO createGroup(@RequestBody GroupRequestDTO requestDTO) throws BadRequestException {
        return universityGroupService.createUniversityGroup(requestDTO);
    }

    @GetMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public GroupResponseDTO getGroupById(@PathVariable("id") Long id) {
        return universityGroupService.getUniversityGroupById(id);
    }

    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    public List<GroupResponseDTO> getAllGroups() {
        return universityGroupService.getAllUniversityGroups();
    }

    @PutMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public GroupResponseDTO updateGroupById(@PathVariable("id") Long id, @RequestBody GroupResponseDTO groupResponseDTO) throws BadRequestException {
        return universityGroupService.updateUniversityGroup(id, groupResponseDTO);
    }
}
