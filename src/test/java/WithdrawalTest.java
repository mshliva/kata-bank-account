import org.bank.account.accounts.error.AccountNotFoundException;
import org.bank.account.accounts.error.DepositsException;
import org.bank.account.operations.app.WithdrawalService;
import org.bank.account.operations.error.WithdrawalException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Currency;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WithdrawalTest {

    WithdrawalService withdrawalService = new WithdrawalService();

    @Test
    @DisplayName("Positive: Successful get balance")
    public void givenWhenGetBalanceCalledReturnBalance() throws DepositsException {
        assertEquals(BigDecimal.valueOf(0L), withdrawalService.getBalance("RU 12 1444 166666 18888888", Currency.getInstance("GBP")));
    }

    @Test
    @DisplayName("Positive: Successful withdrawal")
    public void givenUsdCurrencyWhenWithdrawalThenDecreaseBalance() throws DepositsException, WithdrawalException, AccountNotFoundException {
        withdrawalService.doWithdrawal("US 32 3444 366666 38888888", new BigDecimal("500000.32"), Currency.getInstance("USD"));
        assertEquals(new BigDecimal("99500020.68"), withdrawalService.getBalance("US 32 3444 366666 38888888", Currency.getInstance("USD")));
    }

    @Test
    @DisplayName("Positive: Withdrawal to negative balance on debit account")
    public void givenGbpCurrencyAndNonDebitAccountWhenDebitWithdrawalThenDecreaseBalance() throws DepositsException, WithdrawalException, AccountNotFoundException {
        withdrawalService.doWithdrawal("US 32 3444 366666 38888888", new BigDecimal("312999.01"), Currency.getInstance("GBP"));
        assertEquals(new BigDecimal("-0.01"), withdrawalService.getBalance("US 32 3444 366666 38888888", Currency.getInstance("GBP")));
    }
}
