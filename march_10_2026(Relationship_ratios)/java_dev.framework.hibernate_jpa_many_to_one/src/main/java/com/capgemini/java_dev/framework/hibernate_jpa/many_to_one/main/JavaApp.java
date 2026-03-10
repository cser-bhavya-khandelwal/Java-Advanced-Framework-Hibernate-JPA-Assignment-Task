package com.capgemini.java_dev.framework.hibernate_jpa.many_to_one.main;

import java.util.Scanner;

import com.capgemini.java_dev.framework.hibernate_jpa.many_to_one.entity.Project;
import com.capgemini.java_dev.framework.hibernate_jpa.many_to_one.entity.Student;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JavaApp {

    public static void execution() {

        Scanner sc = new Scanner(System.in);

        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("Student-Project");

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

            choice = sc.nextInt();

            switch (choice) {

            //--------------------------------
            // CREATE STUDENT
            //--------------------------------

            case 1:

                System.out.println("Enter Student ID");
                int sid = sc.nextInt();
                sc.nextLine();

                System.out.println("Enter Name");
                String name = sc.nextLine();

                System.out.println("Enter Mail");
                String mail = sc.nextLine();

                System.out.println("Enter Contact");
                long contact = sc.nextLong();

                System.out.println("Enter Project ID");
                int pid = sc.nextInt();

                Project p = em.find(Project.class, pid);

                if (p == null) {

                    sc.nextLine();

                    System.out.println("Enter Project Name");
                    String pname = sc.nextLine();

                    System.out.println("Enter Description");
                    String desc = sc.nextLine();

                    p = new Project();

                    p.setId(pid);
                    p.setName(pname);
                    p.setDescription(desc);
                }

                Student s = new Student();

                s.setId(sid);
                s.setName(name);
                s.setMailid(mail);
                s.setContactNumber(contact);
                s.setProject(p);

                em.getTransaction().begin();

                em.persist(p);
                em.persist(s);

                em.getTransaction().commit();

                System.out.println("Student Created");

                break;

            //--------------------------------
            // READ STUDENT
            //--------------------------------

            case 2:

                System.out.println("Enter Student ID");

                Student readStudent =
                        em.find(Student.class, sc.nextInt());

                System.out.println(readStudent);

                break;

            //--------------------------------
            // UPDATE STUDENT
            //--------------------------------

            case 3:

                System.out.println("Enter Student ID");

                int usid = sc.nextInt();

                em.getTransaction().begin();

                Student updateStudent =
                        em.find(Student.class, usid);

                if (updateStudent != null) {

                    sc.nextLine();

                    System.out.println("Enter New Name");

                    updateStudent.setName(sc.nextLine());

                }

                em.getTransaction().commit();

                break;

            //--------------------------------
            // DELETE STUDENT
            //--------------------------------

            case 4:

                System.out.println("Enter Student ID");

                int dsid = sc.nextInt();

                em.getTransaction().begin();

                Student deleteStudent =
                        em.find(Student.class, dsid);

                if (deleteStudent != null)
                    em.remove(deleteStudent);

                em.getTransaction().commit();

                break;

            //--------------------------------
            // CREATE PROJECT
            //--------------------------------

            case 5:

                System.out.println("Enter Project ID");
                int newpid = sc.nextInt();
                sc.nextLine();

                System.out.println("Enter Project Name");
                String pname = sc.nextLine();

                System.out.println("Enter Description");
                String desc = sc.nextLine();

                Project project = new Project();

                project.setId(newpid);
                project.setName(pname);
                project.setDescription(desc);

                em.getTransaction().begin();
                em.persist(project);
                em.getTransaction().commit();

                System.out.println("Project Created");

                break;

            //--------------------------------
            // READ PROJECT
            //--------------------------------

            case 6:

                System.out.println("Enter Project ID");

                Project readProject =
                        em.find(Project.class, sc.nextInt());

                System.out.println(readProject);

                break;

            //--------------------------------
            // UPDATE PROJECT
            //--------------------------------

            case 7:

                System.out.println("Enter Project ID");

                int upid = sc.nextInt();

                em.getTransaction().begin();

                Project updateProject =
                        em.find(Project.class, upid);

                if (updateProject != null) {

                    sc.nextLine();

                    System.out.println("Enter New Project Name");

                    updateProject.setName(sc.nextLine());

                }

                em.getTransaction().commit();

                break;

            //--------------------------------
            // DELETE PROJECT
            //--------------------------------

            case 8:

                System.out.println("Enter Project ID");

                int dpid = sc.nextInt();

                em.getTransaction().begin();

                Project deleteProject =
                        em.find(Project.class, dpid);

                if (deleteProject != null)
                    em.remove(deleteProject);

                em.getTransaction().commit();

                break;

            case 9:

                System.out.println("Program Ended");

                break;
            }
        }

        em.close();
        emf.close();
        sc.close();
    }
}