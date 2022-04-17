package com.db.transferMoney;

import com.db.InvalidException;
import com.db.account.Account;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Qualifier("intern")
@Service
public class TransferMoneyServiceIntern implements TransferMoneyServices {
    @Override
    public void executeTransfer(float amount, Account to, Account from) throws InvalidException {
        if (verifyAccounts(amount, to, from)) {
            from.setAmount(from.getAmount() - amount);
            to.setAmount(to.getAmount() + amount);
        } else throw new InvalidException("Invalid account or amount in intern!");
    }

    private boolean verifyAccounts(float amount, Account to, Account from) {
        return Optional.ofNullable(to.getIban()).isPresent() && Optional.ofNullable(from.getIban()).isPresent() &&
                (to.getIban().contains("INT") || to.getIban().contains("EXT")) && from.getAmount() > amount;
    }
}
