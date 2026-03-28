package com.springboot.example.school.Response;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddResponse {
     private String message;
     @Enumerated(EnumType.ORDINAL)
     private HttpStatus status;
}
