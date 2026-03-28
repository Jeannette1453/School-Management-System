package com.springboot.example.school.Teacher;

import com.springboot.example.school.Response.AddResponse;
import com.springboot.example.school.Response.UpdateResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;

@RestController
@RequestMapping("/teachers")
public class TeacherController {

    private final TeacherService service;

    public TeacherController(TeacherService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<AddResponse> addTeacher(@Valid @RequestBody TeacherDto dto) {
        return new ResponseEntity<>(service.addTeacher(dto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Teacher>> getAllTeachers() {
        return new ResponseEntity<>(service.getAllTeachers(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Teacher> getTeacher(@PathVariable Long id) {
        return new ResponseEntity<>(service.getTeacher(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UpdateResponse> updateTeacher(@PathVariable Long id, @Valid @RequestBody TeacherDto dto) {
        return new ResponseEntity<>(service.updateTeacher(id, dto), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTeacher(@PathVariable Long id) {
        service.deleteTeacher(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
