import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Scanner;

public class FindEmployeesByFirstName11 {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory =
                Persistence.createEntityManagerFactory("PU_Name");

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter Employee StartNames:");
        String startName = scanner.nextLine();

        entityManager.createQuery(
                "SELECT e FROM Employee e WHERE firstName LIKE :start ORDER BY id", Employee.class)
                .setParameter("start", startName + "%")
                .getResultStream().forEach(e -> System.out.printf("%s %s - %s - ($%.2f)%n",
                e.getFirstName(), e.getLastName(), e.getJobTitle(), e.getSalary()));

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
