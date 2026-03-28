package com.springboot.example.school.Classroom;

import com.springboot.example.school.Exceptions.ResourceNotFoundException;
import com.springboot.example.school.Response.AddResponse;
import com.springboot.example.school.Response.UpdateResponse;
import com.springboot.example.school.Teacher.Teacher;
import com.springboot.example.school.Teacher.TeacherRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassroomService {

    private final ClassroomRepository classroomRepository;
    private final TeacherRepository teacherRepository;

    public ClassroomService(ClassroomRepository classroomRepository, TeacherRepository teacherRepository) {
        this.classroomRepository = classroomRepository;
        this.teacherRepository = teacherRepository;
    }

    public AddResponse addClassroom(ClassroomDto dto) {
        Teacher teacher = teacherRepository.findById(dto.getTeacherId())
                .orElseThrow(() -> new ResourceNotFoundException("Teacher not found with id: " + dto.getTeacherId()));
        Classroom classroom = new Classroom();
        classroom.setName(dto.getName());
        classroom.setTeacher(teacher);
        classroomRepository.save(classroom);
        return new AddResponse(dto.getName() + " successfully added", HttpStatus.CREATED);
    }

    public List<Classroom> getAllClassrooms() {
        return classroomRepository.findAll();
    }

    public Classroom getClassroom(Long id) {
        return classroomRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Classroom not found with id: " + id));
    }

    public UpdateResponse updateClassroom(Long id, ClassroomDto dto) {
        Classroom classroom = getClassroom(id);
        String oldName = classroom.getName();
        Teacher teacher = teacherRepository.findById(dto.getTeacherId())
                .orElseThrow(() -> new ResourceNotFoundException("Teacher not found with id: " + dto.getTeacherId()));
        classroom.setName(dto.getName());
        classroom.setTeacher(teacher);
        classroomRepository.save(classroom);
        return new UpdateResponse(oldName, dto.getName(), "successfully updated");
    }

    public void deleteClassroom(Long id) {
        classroomRepository.deleteById(id);
    }
}
