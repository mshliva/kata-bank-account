package org.bank.account.operations.app;

import org.bank.account.accounts.app.AccountService;
import org.bank.account.accounts.error.AccountNotFoundException;
import org.bank.account.accounts.error.ExternalException;
import org.bank.account.accounts.model.Account;

import java.math.BigDecimal;
import java.util.Currency;

public class DepositService {

    AccountService accountService = AccountService.getInstance();

    public void makeDeposit(String accountNumber, BigDecimal amount, Currency currency) throws ExternalException {
        try {
            Account account = accountService.getAccount(accountNumber);
            account.getDeposits().merge(currency, amount, BigDecimal::add);
            accountService.updateAccount(account);
        } catch (AccountNotFoundException e) {
            throw new ExternalException("Something went wrong. Please contact support.");
        }
    }
}
