package org.bank.account.accounts.infra;

import org.bank.account.accounts.model.Account;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.HashMap;
import java.util.Map;

public class AccountsProvider {

    private static final HashMap<String, Account> accounts = new HashMap<>();

    static {
        accounts.put("RU 12 1444 166666 18888888",
                        new Account(
                                "RU 12 1444 166666 18888888",
                                new HashMap<>(Map.of(
                                    Currency.getInstance("USD"),new BigDecimal("100"),
                                    Currency.getInstance("EUR"), new BigDecimal("23"),
                                    Currency.getInstance("GBP"), new BigDecimal("0")
                                ))));
        accounts.put("PL 22 2444 266666 28888888",
                        new Account("PL 22 2444 266666 28888888",
                                new HashMap<>(Map.of(
                                    Currency.getInstance("USD"), new BigDecimal("150"),
                                    Currency.getInstance("EUR"), new BigDecimal("2000"),
                                    Currency.getInstance("GBP"), new BigDecimal("5000")
                                ))));
        accounts.put("US 32 3444 366666 38888888",
                        new Account("US 32 3444 366666 38888888",
                                new HashMap<>(Map.of(
                                    Currency.getInstance("USD"), new BigDecimal("100000021"),
                                    Currency.getInstance("EUR"), new BigDecimal("50000012"),
                                    Currency.getInstance("GBP"), new BigDecimal("312999")
                                ))));
    }

    public static Map<String, Account> getAccounts() {
        return accounts;
    }
}
