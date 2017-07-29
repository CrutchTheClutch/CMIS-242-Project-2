package atm;

import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;

/**
 * File: App.java
 * Author: William Crutchfield
 * Date: February 4, 2017
 * Description: An ATM Machine which allows a user to apply various functions on the two Account Objects.
 */
public class App extends JFrame {

    // Variables
    private static DecimalFormat df = new DecimalFormat("#0.00");

    private double wdAmount;
    private double depAmount;
    private double trfAmount;
    private double balAmount;

    private Account checkingAcc = new Account(0);
    private Account savingsAcc = new Account(0);

    /**
     * Constructs the GUI for the program
     */
    private App() {
        // Set Title
        super("ATM Machine");

        // Create Main Panel
        JPanel main = new JPanel();

        // Set Layout
        main.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        // Create Components
        JButton withdrawBtn = new JButton("Withdraw");
        JButton depositBtn = new JButton("Deposit");
        JButton transferBtn = new JButton("Transfer To");
        JButton balanceBtn = new JButton("Balance");
        JRadioButton checkingRbtn = new JRadioButton("Checking");
        JRadioButton savingsRbtn = new JRadioButton("Savings");
        JTextField amountTxt = new JTextField("");


        // Add Components
        c.fill = GridBagConstraints.HORIZONTAL;

        c.insets = new Insets(0,0,5,5);
        c.gridx = 0;
        c.gridy = 0;
        main.add(withdrawBtn, c);

        c.insets = new Insets(0,5,5,0);
        c.gridx = 1;
        c.gridy = 0;
        main.add(depositBtn, c);

        c.insets = new Insets(5,0,5,5);
        c.gridx = 0;
        c.gridy = 2;
        main.add(transferBtn, c);

        c.insets = new Insets(5,5,5,0);
        c.gridx = 1;
        c.gridy = 2;
        main.add(balanceBtn, c);

        c.insets = new Insets(5,0,5,5);
        c.gridx = 0;
        c.gridy = 3;
        main.add(checkingRbtn, c);

        c.insets = new Insets(5,5,5,0);
        c.gridx = 1;
        c.gridy = 3;
        main.add(savingsRbtn, c);

        c.insets = new Insets(5,0,0,0);
        c.gridx = 0;
        c.gridy = 4;
        c.gridwidth = 2;
        main.add(amountTxt, c);

        add(main);

        // Radio Button Group
        ButtonGroup group = new ButtonGroup();
        group.add(checkingRbtn);
        group.add(savingsRbtn);

        // Action Event Handlers
        withdrawBtn.addActionListener(e -> {
            try {
                wdAmount = Double.parseDouble(amountTxt.getText());
                if (wdAmount % 20 == 0) {
                    try {
                        if (checkingRbtn.isSelected()) {
                            checkingAcc.withdraw(wdAmount);
                        } else {
                            savingsAcc.withdraw(wdAmount);
                        }
                        JOptionPane.showMessageDialog(null, "You have withdrawn $" + df.format(wdAmount));
                    } catch (InsufficientFunds c1) {
                        JOptionPane.showMessageDialog(null, "Error! Insufficient Funds!\n You are short $" + df.format(c1.Needs()));
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Error! The number entered is not an increment of $20.00!");
                }
            } catch (NumberFormatException d) {
                JOptionPane.showMessageDialog(null, "Error! You failed to enter a number!");
            }
        });

        depositBtn.addActionListener(e -> {
            try {
                depAmount = Double.parseDouble(amountTxt.getText());
                if (checkingRbtn.isSelected()) {
                    checkingAcc.deposit(depAmount);
                } else {
                    savingsAcc.deposit(depAmount);
                }
                JOptionPane.showMessageDialog(null, "You have deposited $" + df.format(depAmount));
            } catch (NumberFormatException d) {
                JOptionPane.showMessageDialog(null, "Error! You failed to enter a number!");
            }
        });

        transferBtn.addActionListener(e -> {
            try {
                trfAmount = Double.parseDouble(amountTxt.getText());
                if (checkingRbtn.isSelected()) {
                    checkingAcc.transferTo(savingsAcc, checkingAcc, trfAmount);
                } else {
                    savingsAcc.transferTo(checkingAcc, savingsAcc, trfAmount);
                }
                JOptionPane.showMessageDialog(null, "You have transferred $" + df.format(trfAmount));
            } catch (NumberFormatException d) {
            JOptionPane.showMessageDialog(null, "Error! You failed to enter a number!");
            }
            catch (InsufficientFunds c12) {
                JOptionPane.showMessageDialog(null, "Error! Insufficient Funds!\n You are short $" + df.format(c12.Needs()));
            }
        });

        balanceBtn.addActionListener(e -> {
            if (checkingRbtn.isSelected()) {
                balAmount = checkingAcc.balance();
            } else {
                balAmount = savingsAcc.balance();
            }

            JOptionPane.showMessageDialog(null, "Your Balance is : $" + df.format(balAmount));
        });

        // Set Default Radio Button
        checkingRbtn.doClick();

        // JFrame Settings
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(350, 225);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }

    /**
     * Creates the App
     * @param args  :   arguments (does not effect program)
     */
    public static void main(String[] args) {
        new App();
    }
}
