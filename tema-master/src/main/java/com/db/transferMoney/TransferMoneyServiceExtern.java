package com.db.transferMoney;


import com.db.account.Account;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Qualifier("extern")
@Service
public class TransferMoneyServiceExtern implements TransferMoneyServices {
    @Override
    public String executeTransfer(float amount, Account sendTo, Account from) {

        return "Update send Account!";
    }
}
