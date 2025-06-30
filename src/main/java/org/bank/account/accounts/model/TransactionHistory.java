package org.bank.account.accounts.model;

import org.apache.commons.collections4.ListUtils;
import org.bank.account.accounts.error.TransactionHistoryEmptyException;
import org.bank.account.operations.model.Transaction;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TransactionHistory {

    private final List<Transaction> transactionHistory;

    public TransactionHistory() {
        this.transactionHistory = new ArrayList<>();
    }

    public TransactionHistory(List<Transaction> transactionHistory) {
        this.transactionHistory = transactionHistory;
    }

    public void addTransaction(Transaction transaction) {
        transactionHistory.add(transaction);
    }

    public List<Transaction> getHistory() {
        return Collections.unmodifiableList(transactionHistory);
    }

    public Transaction getLastTransaction() throws TransactionHistoryEmptyException {
            try {
                return ListUtils.getLast(transactionHistory);
            } catch (IndexOutOfBoundsException e) {
                throw new TransactionHistoryEmptyException("No trasactions");
            }
    }
}
