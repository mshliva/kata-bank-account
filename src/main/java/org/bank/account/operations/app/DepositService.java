package org.bank.account.operations.app;

import org.bank.account.accounts.app.AccountService;
import org.bank.account.accounts.error.AccountNotFoundException;
import org.bank.account.operations.error.NegativeDepositException;
import org.bank.account.operations.model.Transaction;
import org.bank.account.operations.model.TransactionType;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Currency;

public class DepositService {

    public static final String DEPOSIT_NAGATIVE_MESSAGE = "Deposit amount cannot be negative.";
    private final AccountService accountService = AccountService.getInstance();

    public void makeDeposit(String accountNumber, BigDecimal amount, Currency currency) throws NegativeDepositException, AccountNotFoundException {
        if (amount.compareTo(BigDecimal.ZERO) < 0) {
            throw new NegativeDepositException(DEPOSIT_NAGATIVE_MESSAGE);
        }

        var account = accountService.getAccount(accountNumber);
        var currentBalance = account.deposits().getOrDefault(currency, BigDecimal.ZERO);
        var newBalance = currentBalance.add(amount);

        account.deposits().put(currency, newBalance);

        account.transactionHistory().addTransaction(
                new Transaction(
                        LocalDate.now(),
                        TransactionType.DEPOSIT,
                        amount,
                        newBalance,
                        currency
                )
        );
        accountService.updateAccount(account);
    }
}
