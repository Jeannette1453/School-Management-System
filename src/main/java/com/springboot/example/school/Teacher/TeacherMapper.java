package com.springboot.example.school.Teacher;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface TeacherMapper {
    Teacher mapToEntity(TeacherDto dto);
    Teacher updateEntity(TeacherDto dto, @MappingTarget Teacher teacher);
}
