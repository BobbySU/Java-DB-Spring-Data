import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EmployeesFromDepartment05 {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory =
                Persistence.createEntityManagerFactory("PU_Name");

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

        String departmentName = "Research and Development";

        entityManager.createQuery(
                "SELECT e FROM Employee e WHERE e.department.name = :departmentName ORDER BY salary, e.id", Employee.class)
                .setParameter("departmentName", departmentName)
                .getResultStream().forEach(e ->
                System.out.printf("%s %s from %s - $%.2f%n",
                        e.getFirstName(), e.getLastName(), departmentName, e.getSalary()));

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
