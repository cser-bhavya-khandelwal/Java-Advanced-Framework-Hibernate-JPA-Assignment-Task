package com.capgemini.java_dev.framework.hibernate_employee.entity;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Employee implements Serializable {

	@Id
	private Integer id;
	private String name;
	private Long contactNumber;

	public Employee() {
	}

	public Employee(Integer id, String name, Long contactNumber) {
		this.id = id;
		this.name = name;
		this.contactNumber = contactNumber;
	}

	public Integer getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Long getContactNumber() {
		return contactNumber;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setContactNumber(Long contactNumber) {
		this.contactNumber = contactNumber;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", contactNumber=" + contactNumber + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(contactNumber, id, name);
	}

	@Override
	public boolean equals(Object obj) {

		if (this == obj)
			return true;

		if (obj == null || getClass() != obj.getClass())
			return false;

		Employee other = (Employee) obj;

		return Objects.equals(id, other.id)
				&& Objects.equals(name, other.name)
				&& Objects.equals(contactNumber, other.contactNumber);
	}

}