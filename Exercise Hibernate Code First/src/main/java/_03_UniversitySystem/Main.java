package _03_UniversitySystem;

import _03_UniversitySystem.entities.Course;
import _03_UniversitySystem.entities.Student;
import _03_UniversitySystem.entities.Teacher;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory =
                Persistence.createEntityManagerFactory("CodeFirst");

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

//      -----  Test Input:  -----
//        Student student = new Student("Ani", "Gi", "20525", 6, 1);
//        Student student1 = new Student("Ema", "El", "23030", 3, 5);
//        Teacher teacher = new Teacher("Boss", "Intel", "23030", "shef@abv.bg", 25);
//        Course course = new Course("DataBAse","Java", LocalDate.now(),LocalDate.now(),20);
//
//        entityManager.persist(student);
//        entityManager.persist(student1);
//        entityManager.persist(teacher);
//        entityManager.persist(course);

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
