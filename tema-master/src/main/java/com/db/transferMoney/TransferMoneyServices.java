package com.db.transferMoney;

import com.db.InvalidException;
import com.db.account.Account;

public interface TransferMoneyServices {
    public void executeTransfer(float amount, Account sendTo, Account from) throws InvalidException;
}
