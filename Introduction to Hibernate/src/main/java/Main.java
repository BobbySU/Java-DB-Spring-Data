import entities.Student;
import entities.Teacher;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {
    public static void main(String[] args) {

        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("school");

        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        Student student = new Student("Bobby");
        Teacher teacher = new Teacher("Doni");

        em.persist(student);
        em.persist(teacher);

        em.getTransaction().commit();
        em.close();
    }
}
