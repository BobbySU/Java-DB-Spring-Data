import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class EmployeesWithSalaryOver50000EUR04 {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory =
                Persistence.createEntityManagerFactory("PU_Name");

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

        List<String> employeesResult = entityManager.createQuery(
                "SELECT firstName FROM Employee WHERE salary > 50000", String.class)
                .getResultList();

        System.out.println(String.join("\n", employeesResult));

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
