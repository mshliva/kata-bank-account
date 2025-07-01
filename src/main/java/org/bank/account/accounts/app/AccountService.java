package org.bank.account.accounts.app;

import org.bank.account.accounts.error.AccountNotFoundException;
import org.bank.account.accounts.model.Account;

import java.util.Map;
import java.util.Optional;

public class AccountService {

    private Map<String, Account> accounts;
    private static AccountService instance;

    public static AccountService getInstance(){
        if(instance == null)
            instance = new AccountService();
        return instance;
    }

    public Account getAccount(String accountNumber) throws AccountNotFoundException {
            return Optional
                    .ofNullable(accounts.get(accountNumber))
                    .orElseThrow(() -> new AccountNotFoundException("Account not found: " + accountNumber));
    }

    public void updateAccount(Account account) {
        accounts.put(account.accountNumber(), account);
    }

    public void setAccounts(Map<String,Account> accounts) {
        this.accounts = accounts;
    }
}
