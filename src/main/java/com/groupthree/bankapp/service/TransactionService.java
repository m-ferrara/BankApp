package com.groupthree.bankapp.service;

import com.groupthree.bankapp.model.entity.Account;
import com.groupthree.bankapp.exception.AccountNotFoundException;
import com.groupthree.bankapp.exception.TransactionTypeNotSupportedException;
import com.groupthree.bankapp.model.entity.InterestAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class TransactionService {
    @Autowired
    AccountService service;

    @Autowired
    FirstDateOfMonthValidationService firstDateOfMonthValidationService;

    public Account processTransaction(String transactionType, String acctId, double amount)
            throws TransactionTypeNotSupportedException, AccountNotFoundException {
        Account account = service.getAccountByAccNumber(acctId);
        double balance = account.getBalance();

        switch (transactionType) {
            case "deposit" : {
                account.setBalance((balance + amount));
                break;
            }
            case "withdrawal" : {
                account.setBalance((balance - amount));
                break;
            }
            default: {
                throw new TransactionTypeNotSupportedException();
            }
        }

        this.verifyAndApplyInterestCharged(account);
        service.updateAccount(account);
        return account;
    }

    /**
     * Extra Credit: Verifies if the account is of type InterestAccount that the interest rate is applied
     * if it's the first day of the month
     * @param account The account being verified
     */
    private void verifyAndApplyInterestCharged(Account account) {
        if (account instanceof InterestAccount) {
            boolean doApplyInterest = false;
            InterestAccount intAccount = (InterestAccount) account;

            if (this.firstDateOfMonthValidationService.isFirstDayOfTheMonth()) {
                if (intAccount.getLastDateSinceTransactionCharged() != null && !intAccount.getLastDateSinceTransactionCharged().equals(LocalDate.now())) {
                    doApplyInterest = true;
                }
                else if (intAccount.getLastDateSinceTransactionCharged() == null){
                    doApplyInterest = true;
                }
            }

            if (doApplyInterest) {
                intAccount.applyInterestRate();
                intAccount.setLastDateSinceTransactionCharged(LocalDate.now());
            }
        }
    }
}
