package org.bank.account.accounts.model;

import org.apache.commons.collections4.ListUtils;
import org.bank.account.accounts.error.TransactionHistoryEmptyException;
import org.bank.account.operations.model.Transaction;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TransactionHistory {

    private final List<Transaction> transactionHistory;
    public TransactionHistory() {
        this.transactionHistory = new ArrayList<>();
    }
    public static final String NO_TRANSACTIONS_MESSAGE = "No trasactions on account.";

    public TransactionHistory(List<Transaction> transactionHistory) {
        this.transactionHistory = transactionHistory;
    }

    public void addTransaction(Transaction transaction) {
        transactionHistory.add(transaction);
    }

    public List<Transaction> getAllTransactions() throws TransactionHistoryEmptyException {
        return Optional
                .ofNullable(transactionHistory.isEmpty() ? null : transactionHistory)
                .orElseThrow(() -> new TransactionHistoryEmptyException(NO_TRANSACTIONS_MESSAGE));
    }

    public Transaction getLastTransaction() throws TransactionHistoryEmptyException {
            try {
                return ListUtils.getLast(transactionHistory);
            } catch (IndexOutOfBoundsException e) {
                throw new TransactionHistoryEmptyException(NO_TRANSACTIONS_MESSAGE);
            }
    }
}
