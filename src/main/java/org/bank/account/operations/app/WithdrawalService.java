package org.bank.account.operations.app;

import org.bank.account.accounts.app.AccountService;
import org.bank.account.accounts.error.AccountNotFoundException;
import org.bank.account.accounts.error.DepositsException;
import org.bank.account.accounts.model.Account;
import org.bank.account.operations.error.CurrencyAbsentException;
import org.bank.account.operations.error.WithdrawalException;
import org.bank.account.operations.model.Transaction;
import org.bank.account.operations.model.TransactionType;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Currency;
import java.util.Map;
import java.util.Optional;

public class WithdrawalService {

    private final AccountService accountService = AccountService.getInstance();

    public BigDecimal getBalance(String accountNumber, Currency currency) throws DepositsException {
            return getDeposits(accountNumber).get(currency);
    }

    public void doWithdrawal(String accountNumber, BigDecimal amount, Currency currency) throws DepositsException, AccountNotFoundException, WithdrawalException {
            try {
                getDeposits(accountNumber).merge(currency, amount, BigDecimal::subtract);
                Account account = accountService.getAccount(accountNumber);
                account.getTransactionHistory().addTransaction(
                        new Transaction(
                                LocalDate.now(),
                                TransactionType.WITHDRAWAL,
                                amount,
                                account.getDeposits().get(currency),
                                currency));
            } catch (DepositsException | AccountNotFoundException e) {
                throw new WithdrawalException("Something went wrong. Please Contact support.");
            }
    }

    private Map<Currency, BigDecimal> getDeposits(String accountNumber) throws DepositsException {
        try {
            return Optional
                    .ofNullable(accountService.getAccount(accountNumber).getDeposits())
                    .orElseThrow(() -> new CurrencyAbsentException("No deposit of given currency available."));
        } catch (AccountNotFoundException | CurrencyAbsentException e) {
            throw new DepositsException("Something went wrong. Please contact support.");
        }
    }
}