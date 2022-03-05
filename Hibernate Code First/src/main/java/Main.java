import entities.shampoo.BasicIngredient;
import entities.shampoo.BasicLabel;
import entities.shampoo.BasicShampoo;
import entities.shampoo.ProductionBatch;
import entities.vehicle.Bike;
import entities.vehicle.Car;
import entities.vehicle.Truck;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory =
                Persistence.createEntityManagerFactory("PU_Name");

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

//        -------- Java Persistence API Inheritance --------
        Bike bike = new Bike(21);
        Car car = new Car(5);
        Truck track = new Truck(2000, 24);

        entityManager.persist(bike);
        entityManager.persist(car);
        entityManager.persist(track);


//        -------- Table Relations --------
        BasicIngredient ingredient = new BasicIngredient(100, "B12");
        BasicIngredient ingredient1 = new BasicIngredient(12, "E120");

        ProductionBatch batch = new ProductionBatch(LocalDate.now());
        BasicLabel label = new BasicLabel("blue");
        BasicShampoo shampoo = new BasicShampoo("Shower", label, batch);

        shampoo.addIngredient(ingredient);
        shampoo.addIngredient(ingredient1);

        entityManager.persist(ingredient);
        entityManager.persist(ingredient1);

        entityManager.persist(batch);
        entityManager.persist(label);
        entityManager.persist(shampoo);

        entityManager.find(ProductionBatch.class, 1);

        System.out.println();

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
