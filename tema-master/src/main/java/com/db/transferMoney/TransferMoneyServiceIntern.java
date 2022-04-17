package com.db.transferMoney;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Profile("intern")
@Service
public class TransferMoneyServiceIntern implements TransferMoneyServices {
    @Override
    public String executeTransfer(String sendTo, String from) {
        return "";
    }
}
