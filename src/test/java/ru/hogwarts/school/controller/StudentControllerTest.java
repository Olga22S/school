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

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

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
        assertThat(this.restTemplate.getForObject(
                "http://localhost:" + port + "/hogwarts/student/1", String.class))
                .isNotNull();
    }

    @Test
    public void testGetAllStudents() {
        assertThat(this.restTemplate.getForObject(
                "http://localhost:" + port + "/hogwarts/student/all", String.class))
                .isNotNull();
    }

    @Test
    public void testGetStudentByAge() {
        assertThat(this.restTemplate.getForObject(
                "http://localhost:" + port + "/hogwarts/student/age?age=21", String.class))
                .isNotNull();
    }

    @Test
    public void testGetStudentsByAgeBetween() {
        assertThat(this.restTemplate.getForObject(
                "http://localhost:" + port + "/hogwarts/student/age/?min=21&max=22", String.class))
                .isNotNull();
    }

    @Test
    public void testGetStudentsByFacultyId() {
        assertThat(this.restTemplate.getForObject(
                "http://localhost:" + port + "/hogwarts/student/faculty?id=1", String.class))
                .isNotNull();
    }

    @Test
    public void testAddStudent() {
        Student student = new Student();

        assertThat(this.restTemplate.postForObject(
                "http://localhost:" + port + "/hogwarts/student", student, String.class))
                .isNotNull();
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