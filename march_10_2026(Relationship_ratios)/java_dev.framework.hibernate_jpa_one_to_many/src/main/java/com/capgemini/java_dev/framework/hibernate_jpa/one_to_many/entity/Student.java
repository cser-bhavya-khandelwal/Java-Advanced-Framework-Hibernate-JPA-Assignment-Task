package com.capgemini.java_dev.framework.hibernate_jpa.one_to_many.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Student implements Serializable {

    @Id
    private Integer id;
    private String name;
    private String mailid;
    private Long contactNumber;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    private List<Project> projects;

    public Student() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getMailid() {
        return mailid;
    }

    public Long getContactNumber() {
        return contactNumber;
    }

    public List<Project> getProjects() {
        return projects;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMailid(String mailid) {
        this.mailid = mailid;
    }

    public void setContactNumber(Long contactNumber) {
        this.contactNumber = contactNumber;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }

    @Override
    public String toString() {
        return "Student [id=" + id + ", name=" + name + ", mailid=" + mailid +
                ", contactNumber=" + contactNumber + ", projects=" + projects + "]";
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}