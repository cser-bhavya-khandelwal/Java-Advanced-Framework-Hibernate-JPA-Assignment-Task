package com.capgemini.java_dev.framework.hibernate_jpa.many_to_many.main;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.capgemini.java_dev.framework.hibernate_jpa.many_to_many.entity.Project;
import com.capgemini.java_dev.framework.hibernate_jpa.many_to_many.entity.Student;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JavaApp {

	public static void execution() {

		Scanner sc = new Scanner(System.in);

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Student-Project");
		EntityManager em = emf.createEntityManager();

		int choice = 0;

		while (choice != 9) {

			System.out.println("\n========= MENU =========");

			System.out.println("1 Create Student");
			System.out.println("2 Read Student");
			System.out.println("3 Update Student");
			System.out.println("4 Delete Student");

			System.out.println("5 Create Project");
			System.out.println("6 Read Project");
			System.out.println("7 Update Project");
			System.out.println("8 Delete Project");

			System.out.println("9 Exit");

			System.out.println("Enter Choice:");

			choice = sc.nextInt();

			switch (choice) {

			//---------------------------------------
			// CREATE STUDENT
			//---------------------------------------

			case 1:

				System.out.println("Enter Student ID");
				int sid = sc.nextInt();
				sc.nextLine();

				System.out.println("Enter Student Name");
				String name = sc.nextLine();

				System.out.println("Enter Mail");
				String mail = sc.nextLine();

				System.out.println("Enter Contact");
				long contact = sc.nextLong();

				Student s = new Student();
				s.setId(sid);
				s.setName(name);
				s.setMailid(mail);
				s.setContactNumber(contact);

				System.out.println("How many projects?");
				int pn = sc.nextInt();

				List<Project> projects = new ArrayList<>();

				for (int i = 0; i < pn; i++) {

					System.out.println("Enter Project ID");
					int pid = sc.nextInt();

					Project p = em.find(Project.class, pid);

					if (p == null) {

						sc.nextLine();

						System.out.println("New Project Name");
						String pname = sc.nextLine();

						System.out.println("Description");
						String desc = sc.nextLine();

						p = new Project();

						p.setId(pid);
						p.setName(pname);
						p.setDescription(desc);

					}

					if (p.getStudents() == null)
						p.setStudents(new ArrayList<>());

					p.getStudents().add(s);

					projects.add(p);
				}

				s.setProjects(projects);

				em.getTransaction().begin();
				em.persist(s);
				em.getTransaction().commit();

				System.out.println("Student Created");

				break;

			//---------------------------------------
			// READ STUDENT
			//---------------------------------------

			case 2:

				System.out.println("Enter Student ID");

				Student readStudent = em.find(Student.class, sc.nextInt());

				System.out.println(readStudent);

				break;

			//---------------------------------------
			// UPDATE STUDENT
			//---------------------------------------

			case 3:

				System.out.println("Enter Student ID");

				int usid = sc.nextInt();

				em.getTransaction().begin();

				Student updateStudent = em.find(Student.class, usid);

				if (updateStudent != null) {

					sc.nextLine();

					System.out.println("Enter New Name");
					updateStudent.setName(sc.nextLine());

				} else {

					System.out.println("Student Not Found");

				}

				em.getTransaction().commit();

				break;

			//---------------------------------------
			// DELETE STUDENT
			//---------------------------------------

			case 4:

				System.out.println("Enter Student ID");

				int dsid = sc.nextInt();

				em.getTransaction().begin();

				Student deleteStudent = em.find(Student.class, dsid);

				if (deleteStudent != null)
					em.remove(deleteStudent);

				em.getTransaction().commit();

				System.out.println("Student Deleted");

				break;

			//---------------------------------------
			// CREATE PROJECT
			//---------------------------------------

			case 5:

				System.out.println("Enter Project ID");
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

				System.out.println("How many students?");
				int sn = sc.nextInt();

				List<Student> students = new ArrayList<>();

				for (int i = 0; i < sn; i++) {

					System.out.println("Enter Student ID");
					int stid = sc.nextInt();

					Student st = em.find(Student.class, stid);

					if (st == null) {

						sc.nextLine();

						System.out.println("New Student Name");
						String sname = sc.nextLine();

						System.out.println("Mail");
						String smail = sc.nextLine();

						System.out.println("Contact");
						long scontact = sc.nextLong();

						st = new Student();

						st.setId(stid);
						st.setName(sname);
						st.setMailid(smail);
						st.setContactNumber(scontact);

					}

					if (st.getProjects() == null)
						st.setProjects(new ArrayList<>());

					st.getProjects().add(p);

					students.add(st);

				}

				p.setStudents(students);

				em.getTransaction().begin();
				em.persist(p);
				em.getTransaction().commit();

				System.out.println("Project Created");

				break;

			//---------------------------------------
			// READ PROJECT
			//---------------------------------------

			case 6:

				System.out.println("Enter Project ID");

				Project readProject = em.find(Project.class, sc.nextInt());

				System.out.println(readProject);

				break;

			//---------------------------------------
			// UPDATE PROJECT
			//---------------------------------------

			case 7:

				System.out.println("Enter Project ID");

				int upid = sc.nextInt();

				em.getTransaction().begin();

				Project updateProject = em.find(Project.class, upid);

				if (updateProject != null) {

					sc.nextLine();

					System.out.println("Enter New Project Name");

					updateProject.setName(sc.nextLine());

				}

				em.getTransaction().commit();

				break;

			//---------------------------------------
			// DELETE PROJECT
			//---------------------------------------

			case 8:

				System.out.println("Enter Project ID");

				int dpid = sc.nextInt();

				em.getTransaction().begin();

				Project deleteProject = em.find(Project.class, dpid);

				if (deleteProject != null)
					em.remove(deleteProject);

				em.getTransaction().commit();

				System.out.println("Project Deleted");

				break;

			//---------------------------------------
			// EXIT
			//---------------------------------------

			case 9:

				System.out.println("Program Ended");

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