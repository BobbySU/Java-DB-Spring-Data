import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Set;

public class IncreaseSalaries10 {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory =
                Persistence.createEntityManagerFactory("PU_Name");

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

        int idsUpdate = entityManager.createQuery(
                "UPDATE Employee e SET salary = salary * 1.12 WHERE department.id IN :ids")
                .setParameter("ids", Set.of(1, 2, 4, 11))
                .executeUpdate();
        System.out.println("Updated Salary: " + idsUpdate);

        entityManager.createQuery(
                "SELECT e FROM Employee e WHERE department.id IN :ids", Employee.class)
                .setParameter("ids", Set.of(1, 2, 4, 11))
                .getResultStream().forEach(e -> System.out.printf("%s %s ($%.2f)%n",
                e.getFirstName(), e.getLastName(), e.getSalary()));

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
