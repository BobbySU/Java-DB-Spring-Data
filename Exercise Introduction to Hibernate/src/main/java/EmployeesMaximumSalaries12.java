import entities.Department;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class EmployeesMaximumSalaries12 {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory =
                Persistence.createEntityManagerFactory("PU_Name");

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

        @SuppressWarnings("unchecked")
        List<Object[]> resultList = entityManager.createNativeQuery(
                "SELECT d.name, MAX(e.salary) AS ms FROM departments AS d \n" +
                        "JOIN employees AS e ON e.department_id = d.department_id\n" +
                        "GROUP BY d.name\n" +
                        "HAVING ms NOT BETWEEN 30000 AND 70000;")
                .getResultList();

        resultList.forEach(e -> System.out.printf("%s %.2f%n", e[0], e[1]));

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
