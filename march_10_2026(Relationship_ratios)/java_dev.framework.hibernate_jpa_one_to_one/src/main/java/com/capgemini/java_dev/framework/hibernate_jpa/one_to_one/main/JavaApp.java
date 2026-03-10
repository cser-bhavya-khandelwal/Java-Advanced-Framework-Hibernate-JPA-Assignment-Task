package com.capgemini.java_dev.framework.hibernate_jpa.one_to_one.main;

import com.capgemini.java_dev.framework.hibernate_jpa.one_to_one.entity.Project;
import com.capgemini.java_dev.framework.hibernate_jpa.one_to_one.entity.Student;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JavaApp {

	public static void execution() {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Student-Project");
		EntityManager em = emf.createEntityManager();

		//---------------------------------
		// CREATE
		//---------------------------------

		Project p1 = new Project(101, "Banking System", "Spring Boot Project");

		Student s1 = new Student();
		s1.setId(1);
		s1.setName("Aditya");
		s1.setMailid("aditya@gmail.com");
		s1.setContactNumber(9876543210L);
		s1.setProject(p1);

//		em.getTransaction().begin();
//		em.persist(p1);
//		em.persist(s1);
//		em.getTransaction().commit();

		System.out.println("CREATE SUCCESSFUL");

		//---------------------------------
		// READ
		//---------------------------------

		Student student = em.find(Student.class, 1);

		System.out.println("\nREAD OPERATION");
		System.out.println(student);

		//---------------------------------
		// UPDATE
		//---------------------------------

		em.getTransaction().begin();

		Student updateStudent = em.find(Student.class, 1);

		if (updateStudent != null) {

			updateStudent.setName("Aditya Darda");

			Project project = updateStudent.getProject();
			project.setName("Online Banking System");

		}

		em.getTransaction().commit();

		System.out.println("\nUPDATE SUCCESSFUL");

		System.out.println(em.find(Student.class, 1));

		//---------------------------------
		// DELETE
		//---------------------------------

		em.getTransaction().begin();

		Student deleteStudent = em.find(Student.class, 1);

		if (deleteStudent != null) {

			Project project = deleteStudent.getProject();

			em.remove(deleteStudent);
			em.remove(project);
		}

		em.getTransaction().commit();

		System.out.println("\nDELETE SUCCESSFUL");

		em.close();
		emf.close();
	}
}