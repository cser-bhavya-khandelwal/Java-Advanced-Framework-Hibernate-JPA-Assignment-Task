package com.capgemini.java_dev.framework.hibernate_jpa.many_to_one.entity;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Student implements Serializable {

    @Id
    private Integer id;
    private String name;
    private String mailid;
    private Long contactNumber;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

    public Student() {
    }

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

    public Project getProject() {
        return project;
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

    public void setProject(Project project) {
        this.project = project;
    }

    @Override
    public String toString() {
        return "Student [id=" + id +
                ", name=" + name +
                ", mailid=" + mailid +
                ", contactNumber=" + contactNumber +
                ", project=" + project + "]";
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}