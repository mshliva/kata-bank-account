package org.bank.account.accounts.model;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.HashMap;
import java.util.Map;

public record Account(String accountNumber, Map<Currency, BigDecimal> deposits, TransactionHistory transactionHistory) {

    public Account(String accountNumber, Map<Currency, BigDecimal> deposits, TransactionHistory transactionHistory) {
        this.accountNumber = accountNumber;
        this.deposits = deposits != null ? deposits : new HashMap<>();
        this.transactionHistory = transactionHistory != null ? transactionHistory : new TransactionHistory();
    }
}
