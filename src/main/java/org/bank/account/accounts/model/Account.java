package org.bank.account.accounts.model;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.HashMap;
import java.util.Map;

public class Account {

    private final String accountNumber;
    private final Map<Currency, BigDecimal> deposits;
    private final TransactionHistory transactionHistory;


    public Account(String accountNumber, Map<Currency, BigDecimal> deposits) {
        this(accountNumber, deposits, null);
    }

    public Account(String accountNumber, Map<Currency, BigDecimal> deposits, TransactionHistory transactionHistory) {
        this.accountNumber = accountNumber;
        this.deposits = deposits != null
                ? deposits
                : new HashMap<>();
        this.transactionHistory = transactionHistory != null
                ? transactionHistory
                : new TransactionHistory();
    }

    public String getAccountNumber() { return accountNumber; }

    public Map<Currency, BigDecimal> getDeposits() { return this.deposits; }

    public TransactionHistory getTransactionHistory() { return transactionHistory; }
}
