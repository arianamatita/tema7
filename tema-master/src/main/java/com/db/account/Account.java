package com.db.account;

import com.db.user.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Random;

@Getter
@Setter
@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id")
    private int id;
    @ManyToOne
    @JoinColumn(name = "customer_id")
    @JsonBackReference
    private User user;
    private String currency;
    private String iban;
    private float amount;

    public Account() {
    }

    public Account(User user, String currency) {
        this.user = user;
        this.currency = currency;
        this.amount = 0;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public String generateIban(String internalOrExternal) {
        Random rand = new Random();
        String iban = this.currency;
        iban += this.getUser().getId();
        iban += internalOrExternal;
        for (int i = 0; i < 10; i++) {
            int n = rand.nextInt(10);
            iban += Integer.toString(n);
        }
        return iban;
    }
}
