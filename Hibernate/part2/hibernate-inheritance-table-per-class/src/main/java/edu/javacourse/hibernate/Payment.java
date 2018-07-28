package edu.javacourse.hibernate;

import javax.persistence.*;

@Entity
@Table(name = "jc_payment_full")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator ="jc_payment_full_payment_id_seq")
    @SequenceGenerator(name="jc_payment_full_payment_id_seq", sequenceName="jc_payment_full_payment_id_seq", allocationSize=1)
    @Column(name = "PAYMENT_ID")
    protected Integer paymentId;

    @Column(name = "AMOUNT")
    private Double amount;

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Integer getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(Integer paymentId) {
        this.paymentId = paymentId;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "paymentId=" + paymentId +
                ", amount=" + amount +
                '}';
    }
}
