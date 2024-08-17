import java.util.Arrays;

public class SavingsAccount extends Account {

    private double[] interestRates = {0.7, 0.5, 0.3, 0.1};
    private double[] moneyLevels = {10000, 7500, 5000, 3000};

    public SavingsAccount() {
    }

    public SavingsAccount(String id, double balance) {
        super(id, balance);
    }

    public void deposit(double amount) {
        super.deposit(amount);
        calculateInterest();
    }
    
    @Override
    public String toString() {
        return "SavingsAccount "+ getId();
    }

    public void calculateInterest() {
        for (int i = 0; i < moneyLevels.length; i++) {
            if (getBalance() > moneyLevels[i]) {
                balance += balance * interestRates[i];
                break;
            }
        }
       
    }

    @Override
    public void transferMoney(Account toAccount, double amount) {
        System.out.println("Transfers not allowed from savings account.");
    }
}
