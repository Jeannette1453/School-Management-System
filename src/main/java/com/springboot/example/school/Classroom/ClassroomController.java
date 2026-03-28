package com.springboot.example.school.Classroom;

import com.springboot.example.school.Response.AddResponse;
import com.springboot.example.school.Response.UpdateResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;

@RestController
@RequestMapping("/classrooms")
public class ClassroomController {

    private final ClassroomService service;

    public ClassroomController(ClassroomService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<AddResponse> addClassroom(@Valid @RequestBody ClassroomDto dto) {
        return new ResponseEntity<>(service.addClassroom(dto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Classroom>> getAllClassrooms() {
        return new ResponseEntity<>(service.getAllClassrooms(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Classroom> getClassroom(@PathVariable Long id) {
        return new ResponseEntity<>(service.getClassroom(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UpdateResponse> updateClassroom(@PathVariable Long id, @Valid @RequestBody ClassroomDto dto) {
        return new ResponseEntity<>(service.updateClassroom(id, dto), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClassroom(@PathVariable Long id) {
        service.deleteClassroom(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
