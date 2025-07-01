import config.ConfigurationClass;
import org.bank.account.accounts.error.AccountNotFoundException;
import org.bank.account.accounts.error.DepositsException;
import org.bank.account.accounts.error.TransactionHistoryEmptyException;
import org.bank.account.accounts.model.TransactionHistory;
import org.bank.account.operations.error.NegativeDepositException;
import org.bank.account.operations.error.WithdrawalException;
import org.bank.account.operations.model.Transaction;
import org.bank.account.operations.model.TransactionType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TransactionTest extends ConfigurationClass {

    @Test
    @DisplayName("Positive: Successful transaction record created on deposit")
    public void givenDepositTransactionWhenCreatedThenSuccess() throws NegativeDepositException, TransactionHistoryEmptyException, AccountNotFoundException {
        depositService.makeDeposit("PL 22 2444 266666 28888888", new BigDecimal("1000.42"), Currency.getInstance("USD"));
        Transaction transaction = new Transaction(
                LocalDate.now(),
                TransactionType.DEPOSIT,
                new BigDecimal("1000.42"),
                new BigDecimal("1150.42"),
                Currency.getInstance("USD")
        );
        assertEquals(transaction, accountService.getAccount("PL 22 2444 266666 28888888").transactionHistory().getLastTransaction());
    }

    @Test
    @DisplayName("Positive: Successful transaction record created on withdrawal")
    public void whenWithdrawalThenTransactionCreatedWithSuccess() throws DepositsException, TransactionHistoryEmptyException, AccountNotFoundException, WithdrawalException {
        withdrawalService.doWithdrawal("US 32 3444 366666 38888888", new BigDecimal("12"), Currency.getInstance("EUR"));
        LocalDate transactionDate = LocalDate.now();
        Transaction transaction = new Transaction(
                transactionDate,
                TransactionType.WITHDRAWAL,
                new BigDecimal("12"),
                new BigDecimal("50000000"),
                Currency.getInstance("EUR")
        );
        assertEquals(transaction, accountService.getAccount("US 32 3444 366666 38888888").transactionHistory().getLastTransaction());
    }

    @Test
    @DisplayName("Positive: Successful transaction record created on withdrawal")
    public void givenAmountGraterThanBalanceWhenWithdrawalThenTransactionCreatedWithSuccess() throws DepositsException, TransactionHistoryEmptyException, AccountNotFoundException, WithdrawalException {
        withdrawalService.doWithdrawal("RU 12 1444 166666 18888888", new BigDecimal("1000.00"), Currency.getInstance("USD"));
        LocalDate transactionDate = LocalDate.now();
        Transaction transaction = new Transaction(
                transactionDate,
                TransactionType.WITHDRAWAL,
                new BigDecimal("1000.00"),
                new BigDecimal("-900.00"),
                Currency.getInstance("USD")
        );
        assertEquals(transaction, accountService.getAccount("RU 12 1444 166666 18888888").transactionHistory().getLastTransaction());
    }

    @Test
    @DisplayName("Positive: Get full transactions ")
    public void whenGetAllTransactionThenReturnAllTransactions() throws TransactionHistoryEmptyException, AccountNotFoundException {
        List<Transaction> transactionHistory = new ArrayList<>(List.of(
                new Transaction(LocalDate.of(2023, 2, 21), TransactionType.DEPOSIT, new BigDecimal("100000021"), new BigDecimal("100000021"), Currency.getInstance("USD")),
                new Transaction(LocalDate.of(2024, 3, 22), TransactionType.DEPOSIT, new BigDecimal("50000012"), new BigDecimal("50000012"), Currency.getInstance("EUR")),
                new Transaction(LocalDate.of(2025, 4, 23), TransactionType.DEPOSIT, new BigDecimal("312999"), new BigDecimal("312999"), Currency.getInstance("GBP"))
        ));
        assertEquals(transactionHistory,accountService.getAccount("US 32 3444 366666 38888888").transactionHistory().getAllTransactions());
    }

    @Test
    @DisplayName("Negative: No transactions")
    public void whenNoTransactionsExpectException() {
        assertThrows(
                TransactionHistoryEmptyException.class,
                () -> accountService.getAccount("PL 02 04444 066666 08888888").transactionHistory().getAllTransactions(),
                TransactionHistory.NO_TRANSACTIONS_MESSAGE
        );
    }
}
