package BankingApplication;

import java.util.HashMap;

public class BankService {
    HashMap<String, Customer> customerHashMap = new HashMap<>();
    HashMap<String, Account> accountHashMap = new HashMap<>();

    public Customer registerCustomer(String id, String customerName){
        Customer customer = new Customer(id, customerName);
        customerHashMap.put(id, customer);
        return customer;
    }

    public Account createAccount(String accountId, String customerId){
        Customer customer = customerHashMap.get(customerId);
        if(customer != null) {
            Account account = new Account(accountId, customer);
            accountHashMap.put(accountId, account);
            return account;
        }
        return null;
    }

    public boolean deposit(String accountId, double amount){
        Account account = accountHashMap.get(accountId);
        if(account != null){
            account.deposit(amount);
            return true;
        }

        return false;
    }

    public boolean withdraw(String accountId, double amount){
        Account account = accountHashMap.get(accountId);
        if(account != null){
            account.withdraw(amount);
            return true;
        }

        return false;
    }

    public double checkBalance(String accountId){
        Account account = accountHashMap.get(accountId);
        if(account != null){
            return account.getBalance();
        }
        return 0.0;
    }

    public boolean transfer(String fromAcc, String toAcc, double amount){
        Account primaryAccount = accountHashMap.get(fromAcc);
        Account secondaryAccount = accountHashMap.get(toAcc);
        if(primaryAccount != null && secondaryAccount != null && primaryAccount.withdraw(amount)){
            secondaryAccount.deposit(amount);
            return true;
        }

        return false;
    }
}
