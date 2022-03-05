import entities.Address;
import entities.Town;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Scanner;

public class RemoveTowns13 {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory =
                Persistence.createEntityManagerFactory("PU_Name");

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter Town Name:");
        String townName = scanner.nextLine();

        Town town = entityManager.createQuery("SELECT t FROM Town t WHERE name = :start", Town.class)
                .setParameter("start", townName)
                .getSingleResult();

        List<Address> addressFind = entityManager.createQuery(
                "SELECT a FROM Address a WHERE a.town.id = :tId", Address.class)
                .setParameter("tId", town.getId())
                .getResultList();

        entityManager.getTransaction().begin();
        addressFind.forEach(entityManager::remove);
        entityManager.remove(town);
        entityManager.getTransaction().commit();

        System.out.printf("%d address in %s deleted", addressFind.size(), townName);

        entityManager.close();
    }
}
