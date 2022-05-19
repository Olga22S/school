package ru.hogwarts.school.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.hogwarts.school.model.Student;

import java.util.Arrays;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class StudentControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private StudentController studentController;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void contextLoads() {
        assertThat(studentController).isNotNull();
    }

    @Test
    public void testGetStudent() {
        ResponseEntity<Student> response = restTemplate.getForEntity(
                "http://localhost:" + port + "/hogwarts/student/1", Student.class);

        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertEquals(response.getBody().getId(), 1);
    }

    @Test
    public void testGetAllStudents() {
        ResponseEntity<Student[]> response = restTemplate.getForEntity(
                "http://localhost:" + port + "/hogwarts/student/all", Student[].class);

        assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void testGetStudentByAge() {
        ResponseEntity<Student[]> response = restTemplate.getForEntity(
                "http://localhost:" + port + "/hogwarts/student/age?age=21", Student[].class);

        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertTrue(Arrays.stream(response.getBody())
                .allMatch(student -> student.getAge() == 21));
    }

    @Test
    public void testGetStudentsByAgeBetween() {
        ResponseEntity<Student[]> response = restTemplate.getForEntity(
                "http://localhost:" + port + "/hogwarts/student/age/?min=21&max=22", Student[].class);

        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertTrue(Arrays.stream(response.getBody())
                .allMatch(student -> student.getAge() == 21 || student.getAge() == 22));
    }

    @Test
    public void testGetStudentsByFacultyId() {
        ResponseEntity<Student[]> response = restTemplate.getForEntity(
                "http://localhost:" + port + "/hogwarts/student/faculty?id=1", Student[].class);

        assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void testAddStudent() {
        Student student = new Student();
        student.setName("Kirill");

        ResponseEntity<Student> response = restTemplate.postForEntity(
                "http://localhost:" + port + "/hogwarts/student", student, Student.class);
        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertTrue(response.getBody().getName().equals("Kirill"));
    }

    @Test
    public void testUpdateStudent() {
        Student student = new Student();
        HttpEntity<Student> entity = new HttpEntity(student);

        ResponseEntity<Student> response = restTemplate.exchange(
                "http://localhost:" + port + "/hogwarts/student", HttpMethod.PUT, entity, Student.class);
        assertThat(response.getStatusCode().equals(HttpStatus.OK));
    }

    @Test
    public void testDeleteStudent() {
        Student student = new Student();
        HttpEntity<Student> entity = new HttpEntity(student);

        ResponseEntity<Student> response = restTemplate.exchange(
                "http://localhost:" + port + "/hogwarts/student/1", HttpMethod.DELETE, entity, Student.class);
        assertThat(response.getStatusCode().equals(HttpStatus.OK));
    }
}
