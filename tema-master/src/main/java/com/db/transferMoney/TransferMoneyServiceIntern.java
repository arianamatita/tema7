package com.db.transferMoney;

import com.db.account.Account;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Qualifier("intern")
@Service
public class TransferMoneyServiceIntern implements TransferMoneyServices {
    @Override
    public String executeTransfer(float amount, Account to, Account from) {
        from.setAmount(from.getAmount() - amount);
        to.setAmount(to.getAmount() + amount);
        return "Funds successfully transfered.";
    }
}
