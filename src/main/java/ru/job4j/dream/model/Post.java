package ru.job4j.dream.model;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Post {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
    private int id;
    private String name;
    private String description = "";
    private LocalDateTime created;
    private String formattedCreated;

    public Post(int id, String name) {
        this.id = id;
        this.name = name;
        this.created = LocalDateTime.now();
        this.formattedCreated = created.format(FORMATTER);

    }

    public Post(int id, String name, Timestamp timestamp) {
        this.id = id;
        this.name = name;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
        Post post = (Post) o;
        return id == post.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Post{" + "id=" + id
                + ", name='" + name + '\''
                + ", description='" + description + '\''
                + ", formattedCreated='" + formattedCreated + '\''
                + '}';
    }
}
