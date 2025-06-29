package org.bank.account.operations.app;

import org.bank.account.accounts.app.AccountService;
import org.bank.account.accounts.error.AccountNotFoundException;
import org.bank.account.accounts.error.ExternalException;
import org.bank.account.operations.error.CurrencyAbsentException;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Map;
import java.util.Optional;

public class WithdrawalService {

    private final AccountService accountService = AccountService.getInstance();

    public BigDecimal getBalance(String accountNumber, Currency currency) throws ExternalException {
            return getDeposits(accountNumber).get(currency);
    }

    public void doWithdrawal(String accountNumber, BigDecimal amount, Currency currency) throws ExternalException {
            getDeposits(accountNumber).merge(currency, amount, BigDecimal::subtract);

    }

    private Map<Currency, BigDecimal> getDeposits(String accountNumber) throws ExternalException {
        try {
            return Optional
                    .ofNullable(accountService.getAccount(accountNumber).getDeposits())
                    .orElseThrow(() -> new CurrencyAbsentException("No deposit of given currency available."));
        } catch (AccountNotFoundException | CurrencyAbsentException e) {
            throw new ExternalException("Something went wrong. Please contact support.");
        }
    }
}