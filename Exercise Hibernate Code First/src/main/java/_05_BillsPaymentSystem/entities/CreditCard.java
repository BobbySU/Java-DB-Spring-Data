package _05_BillsPaymentSystem.entities;

import javax.persistence.*;

@Entity
@Table(name = "_05_credit_card")
@DiscriminatorValue(value = "CreditCard")
public class CreditCard extends BillingDetails{
    @Enumerated(value = EnumType.STRING)
    private CardType cardType;

    @Column(name = "expiration_month")
    private Integer expirationMonth;

    @Column(name = "expiration_year")
    private Integer expirationYear;

    public CreditCard() {
    }

    public CreditCard(String number, BankUser bankUser, CardType cardType, Integer expirationMonth, Integer expirationYear) {
        super(number, bankUser);
        this.cardType = cardType;
        this.expirationMonth = expirationMonth;
        this.expirationYear = expirationYear;
    }

    public CardType getCardType() {
        return cardType;
    }

    public void setCardType(CardType cardType) {
        this.cardType = cardType;
    }

    public Integer getExpirationMonth() {
        return expirationMonth;
    }

    public void setExpirationMonth(Integer expirationMonth) {
        this.expirationMonth = expirationMonth;
    }

    public Integer getExpirationYear() {
        return expirationYear;
    }

    public void setExpirationYear(Integer expirationYear) {
        this.expirationYear = expirationYear;
    }
}
