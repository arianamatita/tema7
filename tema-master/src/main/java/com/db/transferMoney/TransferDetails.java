package com.db.transferMoney;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Getter
@Setter
public class TransferDetails {
    private String to;
    private String from;
    private float amount;

    public TransferDetails() {}
}
