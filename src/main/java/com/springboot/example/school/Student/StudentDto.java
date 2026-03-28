package com.springboot.example.school.Student;

import com.springboot.example.school.Enum.Gender;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class StudentDto {

    @NotBlank(message = "Student name must not be blank")
    private String name;

    @Min(value = 1, message = "Age must be at least 1")
    private int age;

    @NotNull(message = "Gender must not be null")
    private Gender gender;

    @NotNull(message = "Classroom ID must not be null")
    private Long classroomId;
}
