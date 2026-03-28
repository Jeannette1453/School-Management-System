package com.springboot.example.school.Student;

import com.springboot.example.school.Classroom.Classroom;
import com.springboot.example.school.Classroom.ClassroomRepository;
import com.springboot.example.school.Exceptions.ResourceNotFoundException;
import com.springboot.example.school.Response.AddResponse;
import com.springboot.example.school.Response.UpdateResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final ClassroomRepository classroomRepository;

    public StudentService(StudentRepository studentRepository, ClassroomRepository classroomRepository) {
        this.studentRepository = studentRepository;
        this.classroomRepository = classroomRepository;
    }

    public AddResponse addStudent(StudentDto dto) {
        Classroom classroom = classroomRepository.findById(dto.getClassroomId())
                .orElseThrow(() -> new ResourceNotFoundException("Classroom not found with id: " + dto.getClassroomId()));
        Student student = new Student();
        student.setName(dto.getName());
        student.setAge(dto.getAge());
        student.setGender(dto.getGender());
        student.setClassroom(classroom);
        studentRepository.save(student);
        return new AddResponse(dto.getName() + " successfully added", HttpStatus.CREATED);
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student getStudent(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id: " + id));
    }

    public List<Student> getStudentsByClassroom(Long classroomId) {
        return studentRepository.findByClassroomId(classroomId);
    }

    public UpdateResponse updateStudent(Long id, StudentDto dto) {
        Student student = getStudent(id);
        String oldName = student.getName();
        Classroom classroom = classroomRepository.findById(dto.getClassroomId())
                .orElseThrow(() -> new ResourceNotFoundException("Classroom not found with id: " + dto.getClassroomId()));
        student.setName(dto.getName());
        student.setAge(dto.getAge());
        student.setGender(dto.getGender());
        student.setClassroom(classroom);
        studentRepository.save(student);
        return new UpdateResponse(oldName, dto.getName(), "successfully updated");
    }

    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }
}
