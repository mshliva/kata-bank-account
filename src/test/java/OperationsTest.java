import org.bank.account.accounts.error.ExternalException;
import org.bank.account.operations.app.DepositService;
import org.bank.account.operations.app.WithdrawalService;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Currency;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OperationsTest {

    DepositService depositService = new DepositService();
    WithdrawalService withdrawalService = new WithdrawalService();

    @Test
    public void givenCertainCurrencyWhenPositiveDepositThenIncreaseBalance() throws ExternalException {
        depositService.makeDeposit("PL 22 2444 266666 28888888", new BigDecimal("1000"), Currency.getInstance("USD"));
        assertEquals(BigDecimal.valueOf(1150L), withdrawalService.getBalance("PL 22 2444 266666 28888888", Currency.getInstance("USD")));
    }


    @Test
    public void givenwhenGetBalanceCalledReturnBalance() throws ExternalException {
        WithdrawalService withdrawalService = new WithdrawalService();
        assertEquals(withdrawalService.getBalance("RU 12 1444 166666 18888888", Currency.getInstance("GBP")), BigDecimal.valueOf(0L));
    }
}
