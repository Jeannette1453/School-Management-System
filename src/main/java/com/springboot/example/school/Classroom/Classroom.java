package com.springboot.example.school.Classroom;

import com.springboot.example.school.Student.Student;
import com.springboot.example.school.Teacher.Teacher;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "classroom")
@AllArgsConstructor
@NoArgsConstructor
public class Classroom {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "classroom_id")
    private Long id;

    @Column(name = "classroom_name", nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "teacher_id")
    @JsonBackReference("teacher-classroom")
    private Teacher teacher;

    @OneToMany(mappedBy = "classroom", cascade = CascadeType.ALL)
    @JsonManagedReference("classroom-student")
    private List<Student> students;
}
