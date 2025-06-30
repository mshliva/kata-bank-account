import org.bank.account.accounts.error.AccountNotFoundException;
import org.bank.account.accounts.error.DepositsException;
import org.bank.account.accounts.error.TransactionHistoryEmptyException;
import org.bank.account.operations.error.NegativeDepositException;
import org.bank.account.operations.error.WithdrawalException;
import org.bank.account.operations.model.Transaction;
import org.bank.account.operations.model.TransactionType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Currency;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TransactionHistoryTest  extends ConfigurationClass {

    @Test
    @DisplayName("Positive: Successful transaction record created on deposit")
    public void whenDepositThenTransactionCreatedWithSuccess() throws NegativeDepositException, DepositsException, TransactionHistoryEmptyException, AccountNotFoundException {
        depositService.makeDeposit("PL 22 2444 266666 28888888", new BigDecimal("1000.42"), Currency.getInstance("USD"));
        LocalDate transactionDate = LocalDate.now();
        Transaction transaction = new Transaction(
                transactionDate,
                TransactionType.DEPOSIT,
                new BigDecimal("1000.42"),
                new BigDecimal("1150.42"),
                Currency.getInstance("USD")
        );
        assertEquals(transaction, accountService.getAccount("PL 22 2444 266666 28888888").getTransactionHistory().getLastTransaction());
    }

    @Test
    @DisplayName("Positive: Successful transaction record created on withdrawal")
    public void whenWithdrawalThenTransactionCreatedWithSuccess() throws NegativeDepositException, DepositsException, TransactionHistoryEmptyException, AccountNotFoundException, WithdrawalException {
        withdrawalService.doWithdrawal("US 32 3444 366666 38888888", new BigDecimal("12"), Currency.getInstance("EUR"));
        LocalDate transactionDate = LocalDate.now();
        Transaction transaction = new Transaction(
                transactionDate,
                TransactionType.WITHDRAWAL,
                new BigDecimal("12"),
                new BigDecimal("50000000"),
                Currency.getInstance("EUR")
        );
        assertEquals(transaction, accountService.getAccount("US 32 3444 366666 38888888").getTransactionHistory().getLastTransaction());
    }

    @Test
    @DisplayName("Positive: Successful transaction record created on withdrawal")
    public void givenAmountGraterThanBalanceWhenWithdrawalThenTransactionCreatedWithSuccess() throws NegativeDepositException, DepositsException, TransactionHistoryEmptyException, AccountNotFoundException, WithdrawalException {
        withdrawalService.doWithdrawal("RU 12 1444 166666 18888888", new BigDecimal("1000.00"), Currency.getInstance("USD"));
        LocalDate transactionDate = LocalDate.now();
        Transaction transaction = new Transaction(
                transactionDate,
                TransactionType.WITHDRAWAL,
                new BigDecimal("1000.00"),
                new BigDecimal("-900.00"),
                Currency.getInstance("USD")
        );
        assertEquals(transaction, accountService.getAccount("RU 12 1444 166666 18888888").getTransactionHistory().getLastTransaction());
    }
}
