import org.bank.account.accounts.error.DepositsException;
import org.bank.account.operations.error.NegativeDepositException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Currency;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DepositsTest extends ConfigurationClass{

    @Test
    @DisplayName("Positive: Successful deposit")
    public void givenUsdCurrencyWhenPositiveDepositThenIncreaseBalance() throws DepositsException, NegativeDepositException {
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
    @DisplayName("Negative: Call for not existing account")
    public void givenAccountNotExistsWhenGetBalanceThenExpectException() {
        assertThrows(DepositsException.class, () -> {
            withdrawalService.getBalance("AL 42 4444 466666 48888888", Currency.getInstance("PLN"));
        });
    }
}