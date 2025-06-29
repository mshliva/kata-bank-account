package org.bank.account.accounts.app;

import org.bank.account.accounts.error.AccountNotFoundException;
import org.bank.account.accounts.infra.AccountsProvider;
import org.bank.account.accounts.model.Account;

import java.util.Map;
import java.util.Optional;

public class AccountService {

    private AccountService() {}
    private static AccountService instance;

    public static AccountService getInstance(){
        if(instance == null)
            instance = new AccountService();
        return instance;
    }

    private final Map<String, Account> accounts = AccountsProvider.getAccounts();

    public Account getAccount(String accountNumber) throws AccountNotFoundException {
            return Optional
                    .ofNullable(accounts.get(accountNumber))
                    .orElseThrow(() -> new AccountNotFoundException("Account not found: " + accountNumber));
    }


    public void updateAccount(Account account) {
        accounts.put(account.getAccountNumber(), account);
    }
}
