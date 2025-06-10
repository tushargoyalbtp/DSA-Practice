package BankingApplication;

import java.util.function.Consumer;

public class Account {

    private String accountId;
    private Customer customer;
    private double balance;

    public Account(String accountId, Customer customer) {
        this.accountId = accountId;
        this.customer = customer;
        this.balance = 0.0;
    }

    public String getAccountId() {
        return accountId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount){
        if(amount > 0){
            balance += amount;
        }
    }

    public boolean withdraw(double amount){
        if(amount > 0 && balance >= amount){
            balance -= amount;
            return true;
        }
        return false;
    }

}
