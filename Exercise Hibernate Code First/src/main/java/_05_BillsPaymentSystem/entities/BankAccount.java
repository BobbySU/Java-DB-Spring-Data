package _05_BillsPaymentSystem.entities;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "_05_bank_account")
@DiscriminatorValue(value = "BankAccount")
public class BankAccount extends BillingDetails{
    @Column(name = "bank_name")
    private String bankName;

    @Column(name = "swift_code")
    private String SwiftCode;

    public BankAccount() {
    }

    public BankAccount(String number, BankUser bankUser, String bankName, String swiftCode) {
        super(number, bankUser);
        this.bankName = bankName;
        SwiftCode = swiftCode;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getSwiftCode() {
        return SwiftCode;
    }

    public void setSwiftCode(String swiftCode) {
        SwiftCode = swiftCode;
    }
}
