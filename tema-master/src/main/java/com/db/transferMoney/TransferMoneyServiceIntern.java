package com.db.transferMoney;

import com.db.account.Account;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Qualifier("intern")
@Service
public class TransferMoneyServiceIntern implements TransferMoneyServices {
    @Override
    public String executeTransfer(float amount, Account to, Account from) {
        if (Optional.ofNullable(to.getIban()).isPresent() && (to.getIban().contains("INT") || to.getIban().contains("EXT"))
                && from.getAmount() > amount) {
            from.setAmount(from.getAmount() - amount);
            to.setAmount(to.getAmount() + amount);
        }
        return "Funds successfully transfered.";
    }
}
