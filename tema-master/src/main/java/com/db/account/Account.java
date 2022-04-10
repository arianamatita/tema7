package com.db.account;

import com.db.user.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;

import javax.persistence.*;
import java.util.Random;

@Getter
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

    public Account() {
    }

    public Account(User user, String currency) {
        this.user = user;
        this.currency = currency;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public String generateIban() {
        Random rand = new Random();
        String iban = this.currency;
        for (int i = 0; i < 14; i++) {
            int n = rand.nextInt(10) + 0;
            iban += Integer.toString(n);
        }
        return iban;
    }
}
