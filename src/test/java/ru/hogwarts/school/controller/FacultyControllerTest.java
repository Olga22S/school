package ru.hogwarts.school.controller;

import org.json.JSONArray;
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
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(FacultyController.class)
class FacultyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FacultyService service;

    @Test
    public void testGetFaculty() throws Exception {
        Long id = 1L;
        Faculty faculty = new Faculty();
        faculty.setId(id);
        when(service.get(id)).thenReturn(faculty);

        mockMvc.perform(
                        get("/faculty/{id}", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id));
    }

    @Test
    public void testDeleteFaculty() throws Exception {
        mockMvc.perform(
                        delete("/faculty/{id}", 1))
                .andExpect(status().isOk());
        verify(service).remove(1L);
    }

    @Test
    public void testAddFaculty() throws Exception {
        Long id = 1L;
        String name = "Nice";
        Faculty faculty = new Faculty();
        faculty.setId(id);
        faculty.setName(name);
        JSONObject userObject = new JSONObject();
        userObject.put("id", id);
        userObject.put("name", name);

        when(service.add(any(Faculty.class))).thenReturn(faculty);

        mockMvc.perform(
                        post("/faculty")
                                .content(userObject.toString())
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(userObject.toString()));
        verify(service).add(faculty);
    }

    @Test
    public void testUpdateFaculty() throws Exception {
        Long id = 1L;
        String name = "Nice";
        Faculty faculty = new Faculty();
        faculty.setId(id);
        faculty.setName(name);
        JSONObject userObject = new JSONObject();
        userObject.put("id", id);
        userObject.put("name", name);

        when(service.update(any(Faculty.class))).thenReturn(faculty);

        mockMvc.perform(
                        put("/faculty")
                                .content(userObject.toString())
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(userObject.toString()));
        verify(service).update(faculty);
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
        Faculty faculty = new Faculty();
        faculty.setId(1L);
        faculty.setColor("красный");
        List<Faculty> faculties = List.of(faculty);
        JSONObject userObject = new JSONObject();
        userObject.put("id", 1L);
        userObject.put("color", "красный");
        JSONArray jsonArray = new JSONArray();
        jsonArray.put(userObject);

        when(service.getByColor("красный")).thenReturn(faculties);

        mockMvc.perform(
                        get("/faculty/color?color=красный"))
                .andExpect(status().isOk())
                .andExpect(content().json(jsonArray.toString()));
    }

    @Test
    public void testGetFacultyByName() throws Exception {
        Faculty faculty = new Faculty();
        faculty.setId(1L);
        faculty.setName("Nice");
        JSONObject userObject = new JSONObject();
        userObject.put("id", 1L);
        userObject.put("name", "Nice");

        when(service.getFacultyByNameIgnoreCase("Nice")).thenReturn(faculty);

        mockMvc.perform(
                        get("/faculty/name?name=Nice"))
                .andExpect(status().isOk())
                .andExpect(content().json(userObject.toString()));
    }

    @Test
    public void testGetFacultyByStudentId() throws Exception {
        Faculty faculty = new Faculty();
        faculty.setId(1L);
        faculty.setName("Nice");
        JSONObject userObject = new JSONObject();
        userObject.put("id", 1L);
        userObject.put("name", "Nice");

        when(service.getFacultyByStudentId(any(Long.class))).thenReturn(faculty);

        mockMvc.perform(
                        get("/faculty/student?id=1"))
                .andExpect(status().isOk())
                .andExpect(content().json(userObject.toString()));
    }
}
