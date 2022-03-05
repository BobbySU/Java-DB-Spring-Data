package _02_SalesDatabase;

import _02_SalesDatabase.entities.Customer;
import _02_SalesDatabase.entities.Product;
import _02_SalesDatabase.entities.Sale;
import _02_SalesDatabase.entities.StoreLocation;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory =
                Persistence.createEntityManagerFactory("CodeFirst");

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

//      -----  Test Input:  -----
//        Customer customer = new Customer("bobby","bob@abv.bg","15 35 12");
//        BigDecimal price = new BigDecimal(2.50);
//        Product product = new Product("Cola", 24.0,price);
//        StoreLocation storeLocation = new StoreLocation("Sofia");
//        Sale sale = new Sale(product, customer,storeLocation);
//
//        entityManager.persist(customer);
//        entityManager.persist(product);
//        entityManager.persist(storeLocation);
//        entityManager.persist(sale);

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
