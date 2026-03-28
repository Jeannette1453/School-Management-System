package com.springboot.example.school.Student;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class StudentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getStudents_withoutAuth_returns401or302() throws Exception {
        mockMvc.perform(get("/students"))
                .andExpect(status().is(org.hamcrest.Matchers.anyOf(
                        org.hamcrest.Matchers.is(401),
                        org.hamcrest.Matchers.is(302)
                )));
    }

    @Test
    void getStudents_withUser_returns200() throws Exception {
        mockMvc.perform(get("/students")
                        .with(user("janet").roles("USER")))
                .andExpect(status().isOk());
    }

    @Test
    void deleteStudent_withUser_returns403() throws Exception {
        mockMvc.perform(delete("/students/1")
                        .with(user("janet").roles("USER")))
                .andExpect(status().isForbidden());
    }

    @Test
    void deleteStudent_withAdmin_returns404OrNoContent() throws Exception {
        mockMvc.perform(delete("/students/999999")
                        .with(user("admin").roles("ADMIN")))
                .andExpect(status().is(org.hamcrest.Matchers.anyOf(
                        org.hamcrest.Matchers.is(204),
                        org.hamcrest.Matchers.is(404)
                )));
    }
}
