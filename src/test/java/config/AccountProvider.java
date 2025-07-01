package config;

import org.bank.account.accounts.model.Account;
import org.bank.account.accounts.model.TransactionHistory;
import org.bank.account.operations.model.Transaction;
import org.bank.account.operations.model.TransactionType;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

public class AccountProvider {

    public static HashMap<String, Account> prepareAccounts() {
        HashMap<String, Account> accounts = new HashMap<>();

        accounts.put(
                "PL 02 04444 066666 08888888",
                new Account(
                        "PL 02 04444 066666 08888888",
                        new HashMap<>(),
                        new TransactionHistory()
                )
        );

        accounts.put(
                "RU 12 1444 166666 18888888",
                new Account(
                        "RU 12 1444 166666 18888888",
                        new HashMap<>(Map.of(
                                Currency.getInstance("USD"), new BigDecimal("100"),
                                Currency.getInstance("EUR"), new BigDecimal("23"),
                                Currency.getInstance("GBP"), new BigDecimal("0")
                        )),
                        new TransactionHistory(new ArrayList<>(List.of(
                                new Transaction(LocalDate.of(2023, 1, 13), TransactionType.DEPOSIT, new BigDecimal("100"), new BigDecimal("100"), Currency.getInstance("USD")),
                                new Transaction(LocalDate.of(2024, 2, 14), TransactionType.DEPOSIT, new BigDecimal("23"), new BigDecimal("23"), Currency.getInstance("EUR")),
                                new Transaction(LocalDate.of(2024, 3, 15), TransactionType.DEPOSIT, null, new BigDecimal("10"), Currency.getInstance("GBP")),
                                new Transaction(LocalDate.of(2025, 3, 15), TransactionType.WITHDRAWAL, new BigDecimal("10"), new BigDecimal("0"), Currency.getInstance("GBP"))
                        )))
                )
        );

        accounts.put(
                "PL 22 2444 266666 28888888",
                new Account(
                        "PL 22 2444 266666 28888888",
                        new HashMap<>(Map.of(
                                Currency.getInstance("USD"), new BigDecimal("150"),
                                Currency.getInstance("EUR"), new BigDecimal("2000"),
                                Currency.getInstance("GBP"), new BigDecimal("5000")
                        )),
                        new TransactionHistory(new ArrayList<>(List.of(
                                new Transaction(LocalDate.of(2023, 2, 21), TransactionType.DEPOSIT, new BigDecimal("150"), new BigDecimal("150"), Currency.getInstance("USD")),
                                new Transaction(LocalDate.of(2024, 3, 22), TransactionType.DEPOSIT, new BigDecimal("2000"), new BigDecimal("2000"), Currency.getInstance("EUR")),
                                new Transaction(LocalDate.of(2025, 4, 23), TransactionType.DEPOSIT, new BigDecimal("4000"), new BigDecimal("4000"), Currency.getInstance("GBP")),
                                new Transaction(LocalDate.of(2025, 4, 24), TransactionType.DEPOSIT, new BigDecimal("1000"), new BigDecimal("5000"), Currency.getInstance("GBP"))
                        )))
                )
        );

        accounts.put(
                "US 32 3444 366666 38888888",
                new Account(
                        "US 32 3444 366666 38888888",
                        new HashMap<>(Map.of(
                                Currency.getInstance("USD"), new BigDecimal("100000021"),
                                Currency.getInstance("EUR"), new BigDecimal("50000012"),
                                Currency.getInstance("GBP"), new BigDecimal("312999")
                        )),
                        new TransactionHistory(new ArrayList<>(List.of(
                                new Transaction(LocalDate.of(2023, 2, 21), TransactionType.DEPOSIT, new BigDecimal("100000021"), new BigDecimal("100000021"), Currency.getInstance("USD")),
                                new Transaction(LocalDate.of(2024, 3, 22), TransactionType.DEPOSIT, new BigDecimal("50000012"), new BigDecimal("50000012"), Currency.getInstance("EUR")),
                                new Transaction(LocalDate.of(2025, 4, 23), TransactionType.DEPOSIT, new BigDecimal("312999"), new BigDecimal("312999"), Currency.getInstance("GBP"))
                        )))
                )
        );

        return accounts;
    }
}
