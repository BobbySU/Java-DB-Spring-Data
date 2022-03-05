import entities.Project;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class FindLatest10Projects09 {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory =
                Persistence.createEntityManagerFactory("PU_Name");

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

        entityManager.createQuery(
                "SELECT p FROM Project p ORDER BY startDate DESC", Project.class)
                .setMaxResults(10)
                .getResultStream()
                .sorted((a1,a2)-> a1.getName().compareTo(a2.getName()))
                .forEach(e -> System.out.printf("Project name: %s%n        Project Description: %s%n" +
                        "        Project Start Date:%s%n        Project End Date: %s%n",
                        e.getName(), e.getDescription(), e.getStartDate(), e.getEndDate()));

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
