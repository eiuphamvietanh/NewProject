import java.io.Serializable;

public abstract class Account implements Serializable {

    protected String idAccount;
    protected double balance;

    public Account() {
    }

    public Account(String id, double balance) {
        this.idAccount = id;
        this.balance = balance;
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public boolean withdraw(double amount) {
        if (amount > balance) {
            return false;
        } else {
            balance -= amount;
            return true;
        }
    }

    public String getId() {
        return idAccount;
    }

    public double getBalance() {
        return balance;
    }

    public void setId(String id) {
        this.idAccount = id;
    }

    @Override
    public String toString() {
        return "Account [id=" + idAccount + ", balance=" + balance + "]";
    }

    public abstract void transferMoney(Account toAccount, double amount);
}
