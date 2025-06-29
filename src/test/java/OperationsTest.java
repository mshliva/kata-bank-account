import org.bank.account.accounts.error.ExternalException;
import org.bank.account.operations.app.DepositService;
import org.bank.account.operations.app.WithdrawalService;
import org.bank.account.operations.error.NegativeDepositException;
import org.bank.account.operations.error.NonDebitDeductionException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Currency;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class OperationsTest {

    DepositService depositService = new DepositService();
    WithdrawalService withdrawalService = new WithdrawalService();

    @Test
    @DisplayName("Positive: Successful deposit")
    public void givenUsdCurrencyWhenPositiveDepositThenIncreaseBalance() throws ExternalException, NegativeDepositException {
        depositService.makeDeposit("PL 22 2444 266666 28888888", new BigDecimal("1000"), Currency.getInstance("USD"));
        assertEquals(BigDecimal.valueOf(1150L), withdrawalService.getBalance("PL 22 2444 266666 28888888", Currency.getInstance("USD")));
    }

    @Test
    @DisplayName("Negative: Negative number deposit attempt")
    public void whenNegativeNumberDepositExpectException() {
        assertThrows(NegativeDepositException.class, () -> {
            depositService.makeDeposit("PL 22 2444 266666 28888888", new BigDecimal("-100.22"), Currency.getInstance("USD"));
        });
    }

    @Test
    @DisplayName("Positive: Successful retrieving balance")
    public void givenWhenGetBalanceCalledReturnBalance() throws ExternalException {
        WithdrawalService withdrawalService = new WithdrawalService();
        assertEquals(BigDecimal.valueOf(0L), withdrawalService.getBalance("RU 12 1444 166666 18888888", Currency.getInstance("GBP")));
    }

    @Test
    @DisplayName("Negative: Call for not existing account")
    public void givenAccountNotExistsWhenGetBalanceThenExpectException() {
        assertThrows(ExternalException.class, () -> {
            withdrawalService.getBalance("AL 42 4444 466666 48888888", Currency.getInstance("PLN"));
        });
    }

    @Test
    @DisplayName("Positive: Successful withdrawal")
    public void givenUsdCurrencyWhenWithdrawalThenDecreaseBalance() throws ExternalException {
        withdrawalService.doWithdrawal("US 32 3444 366666 38888888", new BigDecimal("500000.32"), Currency.getInstance("USD"));
        assertEquals(new BigDecimal("99500020.68"), withdrawalService.getBalance("US 32 3444 366666 38888888", Currency.getInstance("USD")));
    }

    @Test
    @DisplayName("Positive: Withdrawal to negative balance on debit account")
    public void givenGbpCurrencyAndNonDebitAccountWhenDebitWithdrawalThenDecreaseBalance() throws ExternalException {
        withdrawalService.doWithdrawal("US 32 3444 366666 38888888", new BigDecimal("312999.01"), Currency.getInstance("GBP"));
        assertEquals(new BigDecimal("-0.01"), withdrawalService.getBalance("US 32 3444 366666 38888888", Currency.getInstance("GBP")));
    }
}
