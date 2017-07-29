package atm;

/**
 * File: Account.java
 * Author: William Crutchfield
 * Date: February 4, 2017
 * Description: Account objects that are created, and can be used to Withdraw, Deposit, Transfer, and View the funds of.
 */
class Account {

    // Variables
    private double accBalance;
    private static int count;

    /**
     * Constructs an Account Object
     * @param accBalance    :   The starting balance of the account
     */
    Account(double accBalance) {
        this.accBalance = accBalance;
    }

    /**
     * Withdraws funds from an Account Object
     * @param amount    :   The amount to withdraw from the account
     */
    void withdraw(double amount) throws InsufficientFunds {
        if (count >= 4) {
            double serviceCharge = 1.50;
            amount += serviceCharge;
        }
        if (this.accBalance >= amount) {
            this.accBalance -= amount;
            count++;
        } else {
            double needs = amount - this.accBalance;
            throw new InsufficientFunds(needs);
        }
    }

    /**
     * Deposits funds into an Account Object
     * @param amount    :   The amount to deposit into the account
     */
    void deposit(double amount) {
        this.accBalance += amount;
    }

    /**
     * Transfers funds from one Account Object into another
     * @param acc1  :   The Account in which funds are being taken from
     * @param acc2  :   The Account in which funds are being transferred to
     * @param amount    :   The amount of funds that are being transferred
     */
    void transferTo(Account acc1, Account acc2, double amount) throws InsufficientFunds {
        if (acc1.accBalance >= amount) {
            acc1.accBalance -= amount;
            acc2.accBalance += amount;
        } else {
            double needs = amount - acc1.accBalance;
            throw new InsufficientFunds(needs);
        }
    }

    /**
     * Shows the Balance of the current Account Object
     * @return  :   Returns the account balance
     */
    double balance() {
        return accBalance;
    }
}