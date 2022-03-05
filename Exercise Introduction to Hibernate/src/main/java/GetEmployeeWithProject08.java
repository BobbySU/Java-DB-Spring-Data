import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Scanner;

public class GetEmployeeWithProject08 {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory =
                Persistence.createEntityManagerFactory("PU_Name");

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter Employee ID:");
        int employeeId = Integer.parseInt(scanner.nextLine());

        entityManager.createQuery(
                "SELECT e FROM Employee e WHERE e.id = :employeeId ", Employee.class)
                .setParameter("employeeId", employeeId)
                .getResultStream()
                .forEach(e -> {
                    System.out.printf("%s %s - %s%n",
                            e.getFirstName(), e.getLastName(), e.getJobTitle());
                    e.getProjects().stream().sorted((a1,a2)-> a1.getName().compareTo(a2.getName()))
                            .forEach(pro -> System.out.println("      " + pro.getName()));
                });

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
