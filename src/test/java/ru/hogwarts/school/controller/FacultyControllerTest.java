package ru.hogwarts.school.controller;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.service.FacultyService;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(FacultyController.class)
class FacultyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FacultyService service;

    @Test
    public void testGetFaculty() throws Exception {
        when(service.get(any(Long.class))).thenReturn(new Faculty());

        mockMvc.perform(
                        get("/faculty/{id}", 1))
                .andExpect(status().isOk());
    }

    @Test
    public void testDeleteFaculty() throws Exception {
        mockMvc.perform(
                        delete("/faculty/{id}", 1))
                .andExpect(status().isOk());
    }

    @Test
    public void testAddFaculty() throws Exception {
        when(service.add(any(Faculty.class))).thenReturn(new Faculty());
        JSONObject userObject = new JSONObject();

        mockMvc.perform(
                        post("/faculty")
                                .content(userObject.toString())
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testUpdateFaculty() throws Exception {
        when(service.update(any(Faculty.class))).thenReturn(new Faculty());

        mockMvc.perform(
                        put("/faculty")
                                .content(new JSONObject().toString())
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetAllFaculties() throws Exception {
        when(service.getAll()).thenReturn(List.of(new Faculty()));

        mockMvc.perform(
                        get("/faculty/all"))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetFacultyByColor() throws Exception {
        when(service.getByColor(any(String.class))).thenReturn(List.of(new Faculty()));

        mockMvc.perform(
                        get("/faculty/color?color=red"))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetFacultyByName() throws Exception {
        when(service.getFacultyByNameIgnoreCase(any(String.class))).thenReturn(new Faculty());

        mockMvc.perform(
                        get("/faculty/name?name=abc"))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetFacultyByStudentId() throws Exception {
        when(service.getFacultyByStudentId(any(Long.class))).thenReturn(new Faculty());

        mockMvc.perform(
                        get("/faculty/student?id=1"))
                .andExpect(status().isOk());
    }
}
