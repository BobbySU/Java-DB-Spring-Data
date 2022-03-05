import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Scanner;

public class ContainsEmployee03 {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory =
                Persistence.createEntityManagerFactory("PU_Name");

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter Employee Names:");
        String[] name = scanner.nextLine().split(" ");

        Long employeeCount = entityManager.createQuery(
                "SELECT COUNT(*) FROM Employee WHERE firstName = :first_name AND lastName = :last_name", Long.class)
                .setParameter("first_name", name[0])
                .setParameter("last_name", name[1])
                .getSingleResult();
        if (employeeCount > 0) {
            System.out.println("Yes");
        } else {
            System.out.println("No");
        }

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
