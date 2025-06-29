package org.bank.account.operations.app;

import org.bank.account.accounts.app.AccountService;
import org.bank.account.accounts.error.AccountNotFoundException;
import org.bank.account.accounts.error.ExternalException;

import java.math.BigDecimal;
import java.util.Currency;

public class WithdrawalService {

    private final AccountService accountService = AccountService.getInstance();

    public BigDecimal getBalance(String number, Currency currency) throws ExternalException {
        try{
            return accountService.getAccount(number).getDeposits().get(currency);
        } catch (AccountNotFoundException e) {
            throw new ExternalException("Something went wrong. Please contact support.");
        }
    }
}