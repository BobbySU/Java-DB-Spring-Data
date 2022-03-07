package _05_BillsPaymentSystem.entities;

import javax.persistence.*;

@Entity
@Table(name = "_05_billing_details")
//@MappedSuperclass
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "billing_type")
public abstract class BillingDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, unique = true)
    private String number;

    @ManyToOne
    private BankUser bankUser;

    public BillingDetails() {
    }

    public BillingDetails(String number, BankUser bankUser) {
        this.number = number;
        this.bankUser = bankUser;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public BankUser getBankUser() {
        return bankUser;
    }

    public void setBankUser(BankUser bankUser) {
        this.bankUser = bankUser;
    }
}
