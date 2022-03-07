package _04_HospitalDatabase;

import _04_HospitalDatabase.entities.Diagnose;
import _04_HospitalDatabase.entities.Medicaments;
import _04_HospitalDatabase.entities.Patient;
import _04_HospitalDatabase.entities.Visitation;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {

    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory =
                Persistence.createEntityManagerFactory("CodeFirst");

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

//      -----  Test Input:  -----
//        Medicaments medicament1 = new Medicaments("Penicelin");
//        Medicaments medicament2 = new Medicaments("Analgin");
//        Patient patient = new Patient("Gosho", "Goshev", "Sogia Vihren 10",
//                "gosh@abv.bg", "15.10.1986", true);
//        Patient patient2 = new Patient("Pesho", "Goshev", "Sogia Vihren 10",
//                "pgosh@abv.bg", "15.12.1990", true);
//        Visitation visitation = new Visitation("Patient - ill", patient2);
//        Diagnose diagnose = new Diagnose("Bronho","High Temp.");
//
//        entityManager.persist(medicament1);
//        entityManager.persist(medicament2);
//        entityManager.persist(patient);
//        entityManager.persist(patient2);
//        entityManager.persist(visitation);
//        entityManager.persist(diagnose);

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
