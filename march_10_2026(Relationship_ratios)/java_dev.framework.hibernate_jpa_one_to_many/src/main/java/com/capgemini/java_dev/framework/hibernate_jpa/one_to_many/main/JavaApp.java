package com.capgemini.java_dev.framework.hibernate_jpa.one_to_many.main;

import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

import com.capgemini.java_dev.framework.hibernate_jpa.one_to_many.entity.Project;
import com.capgemini.java_dev.framework.hibernate_jpa.one_to_many.entity.Student;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JavaApp {

	public static void execution() {

		Scanner sc = new Scanner(System.in);

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Student-Project");
		EntityManager em = emf.createEntityManager();

		int choice = 0;

		while (choice != 5) {

			System.out.println("\n===== STUDENT PROJECT MENU =====");
			System.out.println("1. CREATE");
			System.out.println("2. READ");
			System.out.println("3. UPDATE");
			System.out.println("4. DELETE");
			System.out.println("5. EXIT");

			System.out.println("Enter your choice:");
			choice = sc.nextInt();

			switch (choice) {

			//---------------------------------
			// CREATE
			//---------------------------------

			case 1:

				System.out.println("Enter Student ID");
				int sid = sc.nextInt();
				sc.nextLine();

				System.out.println("Enter Student Name");
				String sname = sc.nextLine();

				System.out.println("Enter Mail");
				String mail = sc.nextLine();

				System.out.println("Enter Contact Number");
				long contact = sc.nextLong();

				System.out.println("How many projects?");
				int n = sc.nextInt();

				List<Project> projects = new ArrayList<>();

				Student student = new Student();
				student.setId(sid);
				student.setName(sname);
				student.setMailid(mail);
				student.setContactNumber(contact);

				for (int i = 0; i < n; i++) {

					System.out.println("\nEnter Project ID");
					int pid = sc.nextInt();
					sc.nextLine();

					System.out.println("Enter Project Name");
					String pname = sc.nextLine();

					System.out.println("Enter Description");
					String desc = sc.nextLine();

					Project p = new Project();
					p.setId(pid);
					p.setName(pname);
					p.setDescription(desc);
					p.setStudent(student);

					projects.add(p);
				}

				student.setProjects(projects);

				em.getTransaction().begin();
				em.persist(student);
				em.getTransaction().commit();

				System.out.println("CREATE SUCCESSFUL");

				break;

			//---------------------------------
			// READ
			//---------------------------------

			case 2:

				System.out.println("Enter Student ID to Read");
				int readId = sc.nextInt();

				Student s = em.find(Student.class, readId);

				if (s != null) {
					System.out.println("\nStudent Details:");
					System.out.println(s);
				} else {
					System.out.println("Student Not Found");
				}

				break;

			//---------------------------------
			// UPDATE
			//---------------------------------

			case 3:

				System.out.println("Enter Student ID to Update");
				int updateId = sc.nextInt();

				em.getTransaction().begin();

				Student update = em.find(Student.class, updateId);

				if (update != null) {

					sc.nextLine();

					System.out.println("Enter New Name");
					String newName = sc.nextLine();

					update.setName(newName);

					System.out.println("Student Updated");
				} else {

					System.out.println("Student Not Found");

				}

				em.getTransaction().commit();

				break;

			//---------------------------------
			// DELETE
			//---------------------------------

			case 4:

				System.out.println("Enter Student ID to Delete");
				int deleteId = sc.nextInt();

				em.getTransaction().begin();

				Student delete = em.find(Student.class, deleteId);

				if (delete != null) {

					em.remove(delete);

					System.out.println("DELETE SUCCESSFUL");

				} else {

					System.out.println("Student Not Found");

				}

				em.getTransaction().commit();

				break;

			//---------------------------------
			// EXIT
			//---------------------------------

			case 5:

				System.out.println("Exiting Program...");
				break;

			default:
				System.out.println("Invalid Choice");

			}

		}

		em.close();
		emf.close();
		sc.close();
	}
}