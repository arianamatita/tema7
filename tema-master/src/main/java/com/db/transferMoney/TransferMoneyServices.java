package com.db.transferMoney;

import com.db.account.Account;

public interface TransferMoneyServices {
    public String executeTransfer(float amount, Account sendTo, Account from);
}
