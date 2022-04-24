package ru.hogwarts.school.model;

import java.util.Objects;

public class Faculty {

    private Long id;
    private String name;
    private String Color;

    public Faculty(Long id, String name, String color) {
        this.id = id;
        this.name = name;
        Color = color;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return Color;
    }

    public void setColor(String color) {
        Color = color;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o){
            return true;
        }
        if (!(o instanceof Faculty)){
            return false;
        }
        Faculty faculty = (Faculty) o;
        return id.equals(faculty.id) && name.equals(faculty.name) && Color.equals(faculty.Color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, Color);
    }

    @Override
    public String toString() {
        return "Faculty{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", Color='" + Color + '\'' +
                '}';
    }
}
