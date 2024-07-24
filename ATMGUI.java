import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class BankAccount {
    private String user;
    private double balance;

    public BankAccount(String u, double b){
        u = user;
        b = balance;
    }
    public boolean deposit(double amount){
        if (amount>0){
            balance+=amount;
            return true;
        }
        else {
            System.out.println("amount must be greater than 0.");
        }
        return false;
    }
    public boolean withdraw(double amount){
        if (amount>0 && amount<=balance){
            balance-=amount;
            return true;
        }
        else {
            if (amount<=0){
                System.out.println("amount must be greater than 0.");
            }
            else{
            System.out.println("insufficient balance.");
            }
        }
        return false;
    }
    public double checkBalance(){
        return balance;
    }
}

class ATM {
    private BankAccount bankAccount;

    public ATM(BankAccount bankAccount){
        this.bankAccount = bankAccount;
    }

    public boolean deposit(double amount){
        return bankAccount.deposit(amount);
    }

    public boolean withdraw(double amount){
        return bankAccount.withdraw(amount);
    }

    public double checkBalance(){
        return bankAccount.checkBalance();
    }
}


public class ATMGUI {
    private ATM atm;
    private JFrame frame;
    private JTextField amountField;
    private JTextArea displayArea;

    public ATMGUI(ATM atm) {
        this.atm = atm;
        frame = new JFrame("ATM");

        
        JLabel amountLabel = new JLabel("Amount:");
        amountField = new JTextField(10);
        JButton depositButton = new JButton("Deposit");
        JButton withdrawButton = new JButton("Withdraw");
        JButton checkBalanceButton = new JButton("Check Balance");
        displayArea = new JTextArea(10, 30);
        displayArea.setEditable(false);

        frame.setLayout(new FlowLayout());
        frame.add(amountLabel);
        frame.add(amountField);
        frame.add(depositButton);
        frame.add(withdrawButton);
        frame.add(checkBalanceButton);
        frame.add(new JScrollPane(displayArea));

        depositButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deposit();
            }
        });

        withdrawButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                withdraw();
            }
        });

        checkBalanceButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                checkBalance();
            }
        });

        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private void deposit() {
        try {
            double amount = Double.parseDouble(amountField.getText().trim());
            if (atm.deposit(amount)) {
                displayArea.setText("Deposited: $" + amount);
            } else {
                displayArea.setText("Invalid amount. Deposit unsuccessful.");
            }
        } catch (NumberFormatException e) {
            displayArea.setText("Invalid input. Please enter a numeric value.");
        }
        amountField.setText("");
    }

    private void withdraw() {
        try {
            double amount = Double.parseDouble(amountField.getText().trim());
            if (atm.withdraw(amount)) {
                displayArea.setText("Withdrawn: $" + amount);
            } else {
                displayArea.setText("Invalid amount or insufficient balance. Withdrawal unsuccessful.");
            }
        } catch (NumberFormatException e) {
            displayArea.setText("Invalid input. Please enter a numeric value.");
        }
        amountField.setText("");
    }

    private void checkBalance() {
        double balance = atm.checkBalance();
        displayArea.setText("Balance: $" + balance);
    }

    public static void main(String[] args) {
        BankAccount account = new BankAccount("John Doe", 1000.0);
        ATM atm = new ATM(account);
        new ATMGUI(atm);
    }
}
