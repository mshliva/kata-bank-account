package org.bank.account.accounts.model;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Map;

public class Account {

    private String accountNumber;

    private Map<Currency, BigDecimal> deposits;

    public Account(String accountNumber, Map<Currency, BigDecimal> deposits) {
        this.accountNumber = accountNumber;
        this.deposits = deposits;
    }

    public Map<Currency, BigDecimal> getDeposits() {
        return this.deposits;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

}
