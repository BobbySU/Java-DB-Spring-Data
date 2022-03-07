package _05_BillsPaymentSystem;

import _05_BillsPaymentSystem.entities.BankAccount;
import _05_BillsPaymentSystem.entities.BankUser;
import _05_BillsPaymentSystem.entities.CardType;
import _05_BillsPaymentSystem.entities.CreditCard;

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
//        BankUser bankUser = new BankUser("Gosho","Goshev","gosh@abv.bg","1234");
//        BankUser bankUser1 = new BankUser("Pesho","Goshev","pesh@abv.bg","4567");
//        CreditCard creditCard = new CreditCard("065254",bankUser, CardType.PLATINUM,02,2028);
//        CreditCard creditCard1 = new CreditCard("069846",bankUser1, CardType.GOLD,04,2022);
//        BankAccount bankAccount = new BankAccount("022525",bankUser,"DSK","BG02DSK");
//
//        entityManager.persist(bankUser);
//        entityManager.persist(bankUser1);
//        entityManager.persist(creditCard);
//        entityManager.persist(creditCard1);
//        entityManager.persist(bankAccount);

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
