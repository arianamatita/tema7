package com.db.transferMoney;


import com.db.InvalidException;
import com.db.account.Account;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Qualifier("extern")
@Service
public class TransferMoneyServiceExtern implements TransferMoneyServices {
    @Override
    public void executeTransfer(float amount, Account sendTo, Account from) throws InvalidException {
        if(sendTo.getAmount() > amount) {
            sendTo.setAmount(sendTo.getAmount() - amount);
        } throw new InvalidException("Invalid amount in extern!");
    }
}
