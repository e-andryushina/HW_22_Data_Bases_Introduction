package ru.hogwarts.school.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FacultyDTO {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private final long id;
    private final String name;
    private final String color;

    public FacultyDTO(long id, String name, String color) {
        this.id = id;
        this.name = name;
        this.color = color;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }
}
