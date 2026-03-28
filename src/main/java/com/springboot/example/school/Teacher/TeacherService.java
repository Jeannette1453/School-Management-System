package com.springboot.example.school.Teacher;

import com.springboot.example.school.Exceptions.ResourceNotFoundException;
import com.springboot.example.school.Response.AddResponse;
import com.springboot.example.school.Response.UpdateResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherService {

    private final TeacherRepository teacherRepository;
    private final TeacherMapper teacherMapper;

    public TeacherService(TeacherRepository teacherRepository, TeacherMapper teacherMapper) {
        this.teacherRepository = teacherRepository;
        this.teacherMapper = teacherMapper;
    }

    public AddResponse addTeacher(TeacherDto dto) {
        teacherRepository.save(teacherMapper.mapToEntity(dto));
        return new AddResponse(dto.getName() + " successfully added", HttpStatus.CREATED);
    }

    public List<Teacher> getAllTeachers() {
        return teacherRepository.findAll();
    }

    public Teacher getTeacher(Long id) {
        return teacherRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Teacher not found with id: " + id));
    }

    public UpdateResponse updateTeacher(Long id, TeacherDto dto) {
        Teacher teacher = getTeacher(id);
        String oldName = teacher.getName();
        teacherRepository.save(teacherMapper.updateEntity(dto, teacher));
        return new UpdateResponse(oldName, dto.getName(), "successfully updated");
    }

    public void deleteTeacher(Long id) {
        teacherRepository.deleteById(id);
    }
}
