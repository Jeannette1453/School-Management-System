package com.springboot.example.school.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateResponse {
    private String previousName;
    private String newName;
    private String message;
}
