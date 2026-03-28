package com.springboot.example.school.Teacher;

import com.springboot.example.school.Classroom.Classroom;
import com.springboot.example.school.Enum.Subject;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "teacher")
@AllArgsConstructor
@NoArgsConstructor
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "teacher_id")
    private Long id;

    @Column(name = "teacher_name", nullable = false)
    private String name;

    @Column(name = "subject", nullable = false)
    @Enumerated(EnumType.STRING)
    private Subject subject;

    @OneToMany(mappedBy = "teacher", cascade = CascadeType.ALL)
    @JsonManagedReference("teacher-classroom")
    private List<Classroom> classrooms;
}
