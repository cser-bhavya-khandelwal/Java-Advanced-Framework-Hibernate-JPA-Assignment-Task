package com.capgemini.java_dev.framework.hibernate_jpa.many_to_one.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Project implements Serializable {

    @Id
    private Integer id;
    private String name;
    private String description;

    @OneToMany(mappedBy = "project")
    private List<Student> students;

    public Project() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Project [id=" + id +
                ", name=" + name +
                ", description=" + description + "]";
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}