package com.angel.uni.management.controller;

import com.angel.uni.management.dto.GroupDTO;
import com.angel.uni.management.service.UGroupService;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/group")
public class UGroupController {

    private final UGroupService universityGroupService;

    @Autowired
    public UGroupController(UGroupService universityGroupService) {
        this.universityGroupService = universityGroupService;
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public GroupDTO createGroup(@RequestBody GroupDTO groupDTO) throws BadRequestException {
        return universityGroupService.createUniversityGroup(groupDTO);
    }

    @GetMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public GroupDTO getGroupById(@PathVariable("id") Long id) {
        return universityGroupService.getUniversityGroupById(id);
    }

    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    public List<GroupDTO> getAllGroups() {
        return universityGroupService.getAllUniversityGroups();
    }

    @PutMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public GroupDTO updateGroupById(@PathVariable("id") Long id, @RequestBody GroupDTO groupDTO) throws BadRequestException {
        return universityGroupService.updateUniversityGroup(id, groupDTO);
    }
}
