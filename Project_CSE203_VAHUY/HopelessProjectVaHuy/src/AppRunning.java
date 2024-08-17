import javax.swing.*;
import java.awt.*;


import java.text.DecimalFormat;

import java.util.Random;

public class AppRunning {

    private static Bank bank = new Bank();
    private static Random random = new Random();
    private static User currentUser;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(AppRunning::createAndShowGUI);
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("CSE203 Banking System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.setBackground(new Color(204, 255, 204)); // Light green background

        JPanel loginPanel = createLoginPanel();
        JPanel createUserPanel = createUserPanel();

        tabbedPane.addTab("Login", loginPanel);
        tabbedPane.addTab("Create User", createUserPanel);

        frame.add(tabbedPane);
        frame.setVisible(true);
    }

    private static JPanel createLoginPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panel.setBackground(new Color(255, 250, 205)); // Lemon chiffon background

        JLabel userNameLabel = new JLabel("Username:");
        JTextField userNameField = new JTextField(20);
        JLabel passwordLabel = new JLabel("Password:");
        JPasswordField passwordField = new JPasswordField(20);
        JButton loginButton = new JButton("Login");
        //1JButton createAccountButton = new JButton("Click here to"); //

      
        loginButton.setBackground(new Color(0, 128, 0)); 
        loginButton.setForeground(Color.WHITE);
        //1createAccountButton.setBackground(new Color(255, 69, 0)); 
        //1createAccountButton.setForeground(Color.WHITE);

        panel.add(userNameLabel);
        panel.add(userNameField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(loginButton);
       //1 panel.add(createAccountButton);

        loginButton.addActionListener(e -> {
            String userName = userNameField.getText();
            String password = new String(passwordField.getPassword());

            if (bank.searchUserExist(userName, password)) {
                JOptionPane.showMessageDialog(panel, "Login Successful");
                currentUser = bank.getUser(userName, password);
                openDashboard(panel);
            } else {
                JOptionPane.showMessageDialog(panel, "Login Failed");
            }
        });

       /*
        *  createAccountButton.addActionListener(e -> {
            ((JTabbedPane) panel.getParent()).setSelectedIndex(1); 
        });
        */

        return panel;
    }

    private static JPanel createUserPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panel.setBackground(new Color(255, 240, 245)); 

        JLabel userNameLabel = new JLabel("Username:");
        JTextField userNameField = new JTextField(20);
        JLabel passwordLabel = new JLabel("Password:");
        JPasswordField passwordField = new JPasswordField(20);
        JButton createButton = new JButton("Create User");

        
        createButton.setBackground(new Color(0, 191, 255)); 
        createButton.setForeground(Color.WHITE);

        panel.add(userNameLabel);
        panel.add(userNameField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(createButton);

        createButton.addActionListener(e -> {
            String userName = userNameField.getText();
            String password = new String(passwordField.getPassword());

            if (!bank.searchUserName(userName)) {
                User user = new User(userName, password);
                bank.addUser(user);
                JOptionPane.showMessageDialog(panel, "User Created Successfully");
                createBankAccount(user);
                currentUser = user; 
                openDashboard(panel);
            } else {
                JOptionPane.showMessageDialog(panel, "Username already exists");
            }
        });

        return panel;
    }

    private static void openDashboard(JPanel oldPanel) {
        JFrame dashboard = new JFrame("Dashboard");
        dashboard.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        dashboard.setSize(600, 400);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panel.setBackground(new Color(240, 255, 240)); 

       /*
        *  JLabel title = new JLabel("Your Accounts:");
        title.setFont(new Font("Arial", Font.BOLD, 16));
        title.setForeground(new Color(0, 128, 128)); // Teal color
        panel.add(title);
        */

       /*
        *  ArrayList<Account> accounts = currentUser.getAccountList();
        for (Account account : accounts) {
            JLabel accountLabel = new JLabel(account.toString());
            accountLabel.setFont(new Font("Arial", Font.PLAIN, 14));
            panel.add(accountLabel);
        }
        */

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 4, 10, 10));
        buttonPanel.setBackground(new Color(240, 255, 240)); 
        JButton transactionButton = new JButton("Transfer");
        JButton checkBalancesButton = new JButton("Balance");
        JButton depositButton = new JButton("Deposit");
        JButton withdrawButton = new JButton("Withdraw");

       
        transactionButton.setBackground(new Color(173, 216, 230));
        checkBalancesButton.setBackground(new Color(255, 228, 225)); 
        depositButton.setBackground(new Color(144, 238, 144));
        withdrawButton.setBackground(new Color(255, 182, 193)); 

        buttonPanel.add(transactionButton);
        buttonPanel.add(checkBalancesButton);
        buttonPanel.add(depositButton);
        buttonPanel.add(withdrawButton);

        panel.add(buttonPanel);

        dashboard.add(panel);
        dashboard.setVisible(true);

       
        if (oldPanel != null && oldPanel.getTopLevelAncestor() instanceof JFrame) {
            ((JFrame) oldPanel.getTopLevelAncestor()).dispose();
        }

        transactionButton.addActionListener(e -> showTransactionDialog(dashboard));
        checkBalancesButton.addActionListener(e -> showAccountBalances(dashboard));
        depositButton.addActionListener(e -> showDepositDialog(dashboard));
        withdrawButton.addActionListener(e -> showWithdrawDialog(dashboard));
    }

    public static void createBankAccount(User user) {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panel.setBackground(new Color(255, 250, 240)); 

        JLabel balanceLabel = new JLabel("Enter initial balance for savings account:");
        JTextField balanceField = new JTextField();
        JButton createButton = new JButton("Create Accounts");

      
        createButton.setBackground(new Color(50, 205, 50)); 
        createButton.setForeground(Color.WHITE);

        panel.add(balanceLabel);
        panel.add(balanceField);
        panel.add(new JLabel());
        panel.add(createButton);

        JDialog dialog = new JDialog();
        dialog.setTitle("Create Bank Accounts");
        dialog.setSize(400, 200);
        dialog.setLocationRelativeTo(null);
        dialog.setModal(true);
        dialog.setContentPane(panel);

        createButton.addActionListener(e -> {
            try {
                double savingsBalance = Double.parseDouble(balanceField.getText());
                long randomNumbers = 100000 + random.nextLong(900000);
                String savingsID = String.valueOf(randomNumbers);
                Account savingsAccount = new SavingsAccount(savingsID, savingsBalance);
                user.addAccount(savingsAccount);

                randomNumbers =  100000 + random.nextInt(900000);
                String checkingID = String.valueOf(randomNumbers);
                double checkingBalance = Double.parseDouble(JOptionPane.showInputDialog("Enter initial balance for checking account:"));
                Account checkingAccount = new CheckingAccount(checkingID, checkingBalance);
                user.addAccount(checkingAccount);

                JOptionPane.showMessageDialog(dialog, "Accounts Created Successfully");
                dialog.dispose();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(dialog, "Invalid balance input. Please enter a valid number.");
            }
        });

        dialog.setVisible(true);
    }

    private static void showTransactionDialog(JFrame parent) {
        JDialog dialog = new JDialog(parent, "Transaction", true);
        dialog.setSize(400, 300);
        dialog.setLocationRelativeTo(parent);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 2));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panel.setBackground(new Color(255, 250, 205)); // Lemon chiffon background

        JLabel chooseAccountLabel = new JLabel("Choose account type:");
        JRadioButton savingsButton = new JRadioButton("Savings Account");
        JRadioButton checkingButton = new JRadioButton("Checking Account");
        ButtonGroup accountGroup = new ButtonGroup();
        accountGroup.add(savingsButton);
        accountGroup.add(checkingButton);

        JLabel targetAccountLabel = new JLabel("Target Account ID:");
        JTextField targetAccountField = new JTextField();
        JLabel amountLabel = new JLabel("Amount:");
        JTextField amountField = new JTextField();
        JButton performButton = new JButton("Press here to transaction");
        JButton cancelButton = new JButton("Cancel");

       
        performButton.setBackground(new Color(0, 128, 0)); 
        performButton.setForeground(Color.WHITE);
        cancelButton.setBackground(new Color(255, 69, 0)); 
        cancelButton.setForeground(Color.WHITE);

        panel.add(chooseAccountLabel);
        panel.add(new JLabel());
        panel.add(savingsButton);
        panel.add(checkingButton);
        panel.add(targetAccountLabel);
        panel.add(targetAccountField);
        panel.add(amountLabel);
        panel.add(amountField);
        panel.add(performButton);
        panel.add(cancelButton);

        dialog.setContentPane(panel);

        performButton.addActionListener(e -> {
            try {
                String targetId = targetAccountField.getText();
                double amount = Double.parseDouble(amountField.getText());

                Account targetAccount = currentUser.getAccountById(targetId);
                if (targetAccount != null) {
                    if (savingsButton.isSelected()) {
                        //Account savingsAccount = findAccountByType(SavingsAccount.class);
                        Account savingsAccount = currentUser.findAccountByType(SavingsAccount.class, currentUser);
                        if (savingsAccount != null) {
                            savingsAccount.transferMoney(targetAccount, amount);
                            JOptionPane.showMessageDialog(dialog, "Transferred " + amount + " to account " + targetId);
                        } else {
                            JOptionPane.showMessageDialog(dialog, "No savings account found.");
                        }
                    } else if (checkingButton.isSelected()) {
                        //Account checkingAccount = findAccountByType(CheckingAccount.class);
                        Account checkingAccount = currentUser.findAccountByType(CheckingAccount.class, currentUser);
                        if (checkingAccount != null) {
                            checkingAccount.transferMoney(targetAccount, amount);
                            JOptionPane.showMessageDialog(dialog, "Transferred " + amount + " to account " + targetId);
                        } else {
                            JOptionPane.showMessageDialog(dialog, "No checking account found.");
                        }
                    } else {
                        JOptionPane.showMessageDialog(dialog, "Please select an account type.");
                    }
                } else {
                    JOptionPane.showMessageDialog(dialog, "Target account not found.");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(dialog, "Invalid amount. Please enter a valid number.");
            }
        });

        cancelButton.addActionListener(e -> dialog.dispose());

        dialog.setVisible(true);
    }

    private static void showDepositDialog(JFrame parent) {
        JDialog dialog = new JDialog(parent, "Deposit", true);
        dialog.setSize(400, 300);
        dialog.setLocationRelativeTo(parent);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panel.setBackground(new Color(255, 240, 245)); 
        JLabel chooseAccountLabel = new JLabel("Choose account type:");
        JRadioButton savingsButton = new JRadioButton("Savings Account");
        JRadioButton checkingButton = new JRadioButton("Checking Account");
        ButtonGroup accountGroup = new ButtonGroup();
        accountGroup.add(savingsButton);
        accountGroup.add(checkingButton);

        JLabel amountLabel = new JLabel("Amount:");
        JTextField amountField = new JTextField();
        JButton depositButton = new JButton("Deposit");
        JButton cancelButton = new JButton("Cancel");

       
        depositButton.setBackground(new Color(144, 238, 144)); 
        depositButton.setForeground(Color.WHITE);
        cancelButton.setBackground(new Color(255, 99, 71));
        cancelButton.setForeground(Color.WHITE);

        panel.add(chooseAccountLabel);
        panel.add(new JLabel());
        panel.add(savingsButton);
        panel.add(checkingButton);
        panel.add(amountLabel);
        panel.add(amountField);
        panel.add(depositButton);
        panel.add(cancelButton);

        dialog.setContentPane(panel);

        depositButton.addActionListener(e -> {
            try {
                double amount = Double.parseDouble(amountField.getText());

                if (savingsButton.isSelected()) {
                    //currentUser.getAccountById(null)
                    //Account savingsAccount = findAccountByType(SavingsAccount.class);
                    Account savingsAccount = currentUser.findAccountByType(SavingsAccount.class, currentUser);
                    if (savingsAccount != null) {
                        savingsAccount.deposit(amount);
                        JOptionPane.showMessageDialog(dialog, "Deposited " + amount + " to Savings Account");
                    } else {
                        JOptionPane.showMessageDialog(dialog, "No Savings Account found.");
                    }
                } else if (checkingButton.isSelected()) {
                    //Account checkingAccount = findAccountByType(CheckingAccount.class);
                    Account checkingAccount = currentUser.findAccountByType(CheckingAccount.class, currentUser);
                    if (checkingAccount != null) {
                        checkingAccount.deposit(amount);
                        JOptionPane.showMessageDialog(dialog, "Deposited " + amount + " to Checking Account");
                    } else {
                        JOptionPane.showMessageDialog(dialog, "No Checking Account found.");
                    }
                } else {
                    JOptionPane.showMessageDialog(dialog, "Please select an account type.");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(dialog, "Invalid amount. Please enter a valid number.");
            }
        });

        cancelButton.addActionListener(e -> dialog.dispose());

        dialog.setVisible(true);
    }

    private static void showWithdrawDialog(JFrame parent) {
        JDialog dialog = new JDialog(parent, "Withdraw", true);
        dialog.setSize(400, 300);
        dialog.setLocationRelativeTo(parent);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panel.setBackground(new Color(240, 255, 240)); // Honeydew background

        JLabel chooseAccountLabel = new JLabel("Choose account type:");
        JRadioButton savingsButton = new JRadioButton("Savings Account");
        JRadioButton checkingButton = new JRadioButton("Checking Account");
        ButtonGroup accountGroup = new ButtonGroup();
        accountGroup.add(savingsButton);
        accountGroup.add(checkingButton);

        JLabel amountLabel = new JLabel("Amount:");
        JTextField amountField = new JTextField();
        JButton withdrawButton = new JButton("Withdraw");
        JButton cancelButton = new JButton("Cancel");

      
        withdrawButton.setBackground(new Color(255, 99, 71)); 
        withdrawButton.setForeground(Color.WHITE);
        cancelButton.setBackground(new Color(173, 216, 230)); 
        cancelButton.setForeground(Color.WHITE);

        panel.add(chooseAccountLabel);
        panel.add(new JLabel());
        panel.add(savingsButton);
        panel.add(checkingButton);
        panel.add(amountLabel);
        panel.add(amountField);
        panel.add(withdrawButton);
        panel.add(cancelButton);

        dialog.setContentPane(panel);

        withdrawButton.addActionListener(e -> {
            try {
                double amount = Double.parseDouble(amountField.getText());

                if (savingsButton.isSelected()) {
                  //  Account savingsAccount =findAccountByType(SavingsAccount.class);
                    
                    Account savingsAccount = currentUser.findAccountByType(SavingsAccount.class, currentUser);
                    if (savingsAccount != null) {
                        boolean success = savingsAccount.withdraw(amount);
                        if (success) {
                            JOptionPane.showMessageDialog(dialog, "Withdrew " + amount + " from Savings Account");
                        } else {
                            JOptionPane.showMessageDialog(dialog, "Insufficient balance in Savings Account.");
                        }
                    } else {
                        JOptionPane.showMessageDialog(dialog, "No Savings Account found.");
                    }
                } else if (checkingButton.isSelected()) {
                    //Account checkingAccount = findAccountByType(CheckingAccount.class);
                    Account checkingAccount = currentUser.findAccountByType(CheckingAccount.class, currentUser);
                    if (checkingAccount != null) {
                        boolean success = checkingAccount.withdraw(amount);
                        if (success) {
                            JOptionPane.showMessageDialog(dialog, "Withdrew " + amount + " from Checking Account");
                        } else {
                            JOptionPane.showMessageDialog(dialog, "Insufficient balance in Checking Account.");
                        }
                    } else {
                        JOptionPane.showMessageDialog(dialog, "No Checking Account found.");
                    }
                } else {
                    JOptionPane.showMessageDialog(dialog, "Please select an account type.");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(dialog, "Invalid amount. Please enter a valid number.");
            }
        });

        cancelButton.addActionListener(e -> dialog.dispose());

        dialog.setVisible(true);
    }

    private static void showAccountBalances(JFrame parent) {
        JDialog dialog = new JDialog(parent, "Viet Anh & Quang Huy Bank Account", true);
        dialog.setSize(400, 300);
        dialog.setLocationRelativeTo(parent);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panel.setBackground(new Color(255, 250, 240));
        /*
         * JLabel title = new JLabel("Account Balances:");
        title.setFont(new Font("Arial", Font.BOLD, 16));
        title.setForeground(new Color(0, 128, 128)); // Teal color
        panel.add(title);
         */

        DecimalFormat df = new DecimalFormat("0.00");
        for (Account account : currentUser.getAccountList()) {
            JPanel accountPanel = new JPanel();
            accountPanel.setLayout(new GridLayout(1, 2));
            accountPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
            accountPanel.setBackground(new Color(255, 255, 255)); 

            JLabel accountInfo = new JLabel(account.getId() + " (" + account.getClass().getSimpleName() + ")");
            accountInfo.setForeground(new Color(0, 0, 139)); 
            JLabel balanceInfo = new JLabel("Balance: $" + df.format(account.getBalance()));
            balanceInfo.setForeground(new Color(0, 100, 0)); 

            accountPanel.add(accountInfo);
            accountPanel.add(balanceInfo);

            panel.add(accountPanel);

        }

        JButton closeButton = new JButton("Close");
        closeButton.setBackground(new Color(30, 144, 255)); 
        closeButton.setForeground(Color.WHITE);
        panel.add(closeButton);

        dialog.setContentPane(panel);

        closeButton.addActionListener(e -> dialog.dispose());

        dialog.setVisible(true);
    }

   /*
    *  public static Account findAccountByType(Class<? extends Account> accountType) {
        for (Account account : currentUser.getAccountList()) {
            if (accountType.isInstance(account)) {
                return account;
            }
        }
        return null;
    }
    */
}
