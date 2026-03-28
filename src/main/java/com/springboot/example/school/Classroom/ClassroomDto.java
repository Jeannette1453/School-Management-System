package com.springboot.example.school.Classroom;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ClassroomDto {

    @NotBlank(message = "Classroom name must not be blank")
    private String name;

    @NotNull(message = "Teacher ID must not be null")
    private Long teacherId;
}
