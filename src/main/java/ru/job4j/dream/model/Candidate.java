package ru.job4j.dream.model;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Candidate {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
    private int id;
    private String name;
    private int city_id;
    private LocalDateTime created;
    private String formattedCreated;

    public Candidate(int id, String name) {
        this.id = id;
        this.name = name;
        this.created = LocalDateTime.now();
        this.formattedCreated = created.format(FORMATTER);
    }

    public Candidate(int id, String name, int city_id) {
        this.id = id;
        this.name = name;
        this.city_id = city_id;
        this.created = LocalDateTime.now();
        this.formattedCreated = created.format(FORMATTER);
    }

    public Candidate(int id, String name, int city_id, Timestamp timestamp) {
        this.id = id;
        this.name = name;
        this.city_id = city_id;
        this.created = timestamp.toLocalDateTime();
        this.formattedCreated = created.format(FORMATTER);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCity_id() {
        return city_id;
    }

    public void setCity_id(int city_id) {
        this.city_id = city_id;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
        this.formattedCreated = created.format(FORMATTER);
    }

    public String getFormattedCreated() {
        return formattedCreated;
    }

    public void setFormattedCreated(String formattedCreated) {
        this.formattedCreated = formattedCreated;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Candidate candidate = (Candidate) o;
        return id == candidate.id &&
                Objects.equals(name, candidate.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "Candidate{" + "id=" + id
                + ", name='" + name + '\''
                + ", city_id=" + city_id
                + ", created=" + created
                + ", formattedCreated='" + formattedCreated
                + '\'' + '}';
    }
}