package config;

import org.bank.account.accounts.app.AccountService;
import org.bank.account.operations.app.DepositService;
import org.bank.account.operations.app.WithdrawalService;
import org.junit.jupiter.api.BeforeEach;

public abstract class ConfigurationClass {

    protected AccountService accountService;
    protected DepositService depositService;
    protected WithdrawalService withdrawalService;

    @BeforeEach
    public void setup() {
        accountService = AccountService.getInstance();
        accountService.setAccounts(AccountProvider.prepareAccounts());
        depositService = new DepositService();
        withdrawalService = new WithdrawalService();
    }
}
