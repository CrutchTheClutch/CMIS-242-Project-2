package atm;

/**
 * File: InsufficientFunds.java
 * Author: William Crutchfield
 * Date: February 4, 2017
 * Description: User defined Exception that determines if the Account has enough funds to perform a selected action.
 */
class InsufficientFunds extends Exception {

    // Variables
    private double amount;

    /**
     * Takes the amount needed to preform an action on the Account
     * @param amount    :   Amount needed
     */
    InsufficientFunds(double amount) {
        this.amount = amount;
    }

    /**
     * Shows the Amount needed for the account
     * @return  :   Returns the amount needed
     */
    double Needs() {
        return amount;
    }
}
