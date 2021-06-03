package com.douglas.netflix.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;

@Document
public class Movie {

    @Id
    private String id;

    private String name;

    private String type;

    private String principalActor;

    private Integer createdAt;

    public Movie(String id, String name, String type, String principalActor, Integer createdAt) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.principalActor = principalActor;
        this.createdAt = createdAt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPrincipalActor() {
        return principalActor;
    }

    public void setPrincipalActor(String principalActor) {
        this.principalActor = principalActor;
    }

    public Integer getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Integer createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return Objects.equals(id, movie.id) && Objects.equals(name, movie.name) && Objects.equals(type, movie.type)
                && Objects.equals(principalActor, movie.principalActor) && Objects.equals(createdAt, movie.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, type, principalActor, createdAt);
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", principalActor='" + principalActor + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}
