package org.bank.account.operations.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Currency;
import java.util.Objects;
import java.util.UUID;

public class Transaction {
    private final UUID identifier;
    private final LocalDate localDate;
    private final TransactionType transactionType;
    private final BigDecimal amount;
    private final BigDecimal balance;
    private final Currency currency;

    public Transaction(LocalDate localDate, TransactionType transactionType, BigDecimal amount, BigDecimal balance, Currency currency) {
        this.identifier = UUID.randomUUID();
        this.localDate = localDate;
        this.transactionType = transactionType;
        this.amount = amount;
        this.balance = balance;
        this.currency = currency;
    }

    public UUID getIdentifier() {
        return identifier;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return Objects.equals(localDate, that.localDate) && transactionType == that.transactionType && Objects.equals(amount, that.amount) && Objects.equals(balance, that.balance) && Objects.equals(currency, that.currency);
    }

    @Override
    public int hashCode() {
        return Objects.hash(localDate, transactionType, amount, balance, currency);
    }
}
