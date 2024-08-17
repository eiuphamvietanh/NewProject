import java.io.Serializable;
import java.util.ArrayList;

public class User implements Serializable {

    private String userName;
    private String password;
    private ArrayList<Account> accounts;

    public User() {
        accounts = new ArrayList<>();
    }

    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
        accounts = new ArrayList<>();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void removeAccount(Account account) {
        accounts.remove(account);
    }

    public void addAccount(Account account) {
        accounts.add(account);
    }

    public ArrayList<Account> getAccountList() {
        return accounts;
    }

    public boolean containsAccount(String id) {
        for (Account account : accounts) {
            if (account.getId().equals(id)) {
                return true;
            }
        }
        return false;
    }

    public void printAccounts() {
        for (Account account : accounts) {
            System.out.println(account);
        }
    }

    public Account getAccountById(String id) {
        for (Account account : accounts) {
            if (account.getId().equals(id)) {
                return account;
            }
        }
        return null;
    }

    public  Account findAccountByType(Class<? extends Account> accountType,User currentUser) {
        for (Account account : currentUser.getAccountList()) {
            if (accountType.isInstance(account)) {
                return account;
            }
        }
        return null;
    }

    
}
