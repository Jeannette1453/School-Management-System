package com.springboot.example.school.Teacher;

import com.springboot.example.school.Enum.Subject;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class TeacherDto {

    @NotBlank(message = "Teacher name must not be blank")
    private String name;

    @NotNull(message = "Subject must not be null")
    private Subject subject;
}
