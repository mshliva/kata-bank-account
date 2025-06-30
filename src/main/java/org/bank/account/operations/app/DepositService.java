package org.bank.account.operations.app;

import org.bank.account.accounts.app.AccountService;
import org.bank.account.accounts.error.AccountNotFoundException;
import org.bank.account.accounts.error.DepositsException;
import org.bank.account.accounts.model.Account;
import org.bank.account.operations.error.NegativeDepositException;
import org.bank.account.operations.model.Transaction;
import org.bank.account.operations.model.TransactionType;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Currency;

public class DepositService {

    AccountService accountService = AccountService.getInstance();

    public void makeDeposit(String accountNumber, BigDecimal amount, Currency currency) throws DepositsException, NegativeDepositException {
        if (amount.signum() <= 0)
            throw new NegativeDepositException("It's not a withdrawal");
        try {
            Account account = accountService.getAccount(accountNumber);
            account.getDeposits().merge(currency, amount, BigDecimal::add);
            account.getTransactionHistory()
                    .addTransaction(
                            new Transaction(
                                LocalDate.now(),
                                TransactionType.DEPOSIT,
                                amount,
                                account.getDeposits().get(currency),
                                currency));
            accountService.updateAccount(account);
        } catch (AccountNotFoundException e) {
            throw new DepositsException("Something went wrong. Please contact support.");
        }
    }
}
