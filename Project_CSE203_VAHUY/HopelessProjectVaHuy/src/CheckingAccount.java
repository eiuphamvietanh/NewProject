public class CheckingAccount extends Account {

    private double overDraftLimit = 1000;

    public CheckingAccount() {
    }

    public CheckingAccount(String id, double balance) {
        super(id, balance);
    }

    @Override
    public String toString() {
        return "CheckingAccount: " + getId() + "";
    }

    @Override
    public void transferMoney(Account toAccount, double amount) {
        if (this.withdraw(amount)) {
            toAccount.deposit(amount);
        } else {
            System.out.println("Insufficient balance for transfer.");
        }
    }
}
