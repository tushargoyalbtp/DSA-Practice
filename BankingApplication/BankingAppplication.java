package BankingApplication;

import java.sql.SQLOutput;
import java.util.Scanner;

public class BankingAppplication {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        BankService bankService = new BankService();

        while(true){
            System.out.println("\nBank Menu:");
            System.out.println("1. Register Customer");
            System.out.println("2. Create Account");
            System.out.println("3. Deposit");
            System.out.println("4. Withdraw");
            System.out.println("5. Check Balance");
            System.out.println("6. Transfer");
            System.out.println("0. Exit");
            System.out.print("Choose option: ");
            int choice = sc.nextInt();

            switch (choice){
                case 1:
                    System.out.println("Customer Id for creating new customer");
                    String customerId = sc.next();
                    System.out.println("Customer name for new customer");
                    String customerName = sc.next();
                    bankService.registerCustomer(customerId, customerName);
                    break;
                case 2:
                    System.out.println("Account id for creating new account");
                    String accountId = sc.next();
                    System.out.println("Customer Id for which this account is getting create");
                    customerId = sc.next();
                    Account account = bankService.createAccount(accountId, customerId);
                    break;
                case 3:
                    System.out.println("Account id");
                    accountId = sc.next();
                    System.out.println("Amount to be deposit");
                    double depositAmount = sc.nextDouble();
                    boolean isDeposit = bankService.deposit(accountId, depositAmount);
                    System.out.println("Successfully deposit amount");
                    break;
                case 4:
                    System.out.println("Account id from which amount needs to be withdrawn");
                    accountId = sc.next();
                    System.out.println("amount needed to be withdraw");
                    double amount = sc.nextDouble();
                    boolean isWithdraw = bankService.withdraw(accountId, amount);
                    System.out.println("Amount withdrawn successfully");
                    break;
                case 5:
                    System.out.println("Account id: ");
                    accountId = sc.next();
                    double balanceAmount = bankService.checkBalance(accountId);
                    System.out.println("Balance amount from the paricular amount : " + balanceAmount);
                    break;
                case 6:
                    System.out.println("from account balance withdraw");
                    String fromAcc = sc.next();
                    System.out.println("To account balance needs to be deposit");
                    String toAcc = sc.next();
                    System.out.println("amount required needs to tranfer");
                    depositAmount = sc.nextDouble();
                    if(bankService.transfer(fromAcc, toAcc, depositAmount)){
                        System.out.println("Tranfer successfully ....");
                    }
                    else{
                        System.out.println("Transfer failed..");
                    }
                    break;
                case 0:
                    System.out.println("Exiting. Goodbye!");
                    sc.close();
                    return;
                default:
                    System.out.println("Invalid option.");

            }
        }
    }
}
