import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ATM {
    private String cardNum;
    private int pin;
    private String firstName;
    private String lastName;
    private double balance;

    public ATM(String cardNum, int pin, String firstName, String lastName, double balance) {
        this.cardNum = cardNum;
        this.pin = pin;
        this.firstName = firstName;
        this.lastName = lastName;
        this.balance = balance;
    }

    public String getNum() {
        return cardNum;
    }

    public int getPin() {
        return pin;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public double getBalance() {
        return balance;
    }

    public void setNum(String cardNum) {
        this.cardNum = cardNum;
    }

    public void setPin(int pin) {
        this.pin = pin;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Helper methods
        void printOptions() {
            System.out.println("Enter a number for the operation you want to perform:");
            System.out.println("1. Deposit");
            System.out.println("2. Withdraw");
            System.out.println("3. Show Balance");
            System.out.println("4. Exit");
        }

        void deposit(ATM currentUser) {
            System.out.println("How much money would you like to deposit?");
            double deposit = scanner.nextDouble();
            currentUser.setBalance(currentUser.getBalance() + deposit);
            System.out.println("Thank you for your money, your new balance is " + currentUser.getBalance());
        }

        void withdraw(ATM currentUser) {
            System.out.println("How much money you want to withdraw?");
            double withdrawal = scanner.nextDouble();
            if (currentUser.getBalance() > withdrawal) {
                double newBalance = currentUser.getBalance() - withdrawal;
                currentUser.setBalance(newBalance);
                System.out.println("You are good to go, thank you!");
            } else {
                System.out.println("Insufficient money");
            }
        }

        void balance(ATM currentUser) {
            System.out.println("Current balance is " + currentUser.getBalance());
        }

        // List of ATM users
        List<ATM> list = new ArrayList<>();
        list.add(new ATM("123456", 1234, "Umesh", "Bhingare", 15000000.22));
        list.add(new ATM("1234567", 12345, "Demo", "Demons", 1000000.22));
        list.add(new ATM("12345678", 1235, "Pranjali", "Shinde", 1500000.22));
        list.add(new ATM("123456789", 1233, "Rutika", "Jadhav", 15000.22));
        list.add(new ATM("1234567891", 1222, "Anushka", "Shinde", 150000.22));

        // User interaction
        System.out.println("Welcome to ATM interface");
        System.out.println("Please insert your debit card:");
        String debitCardNum = "";
        ATM currentUser = null;

        while (true) {
            try {
                debitCardNum = scanner.nextLine();

                for (ATM user : list) {
                    if (user.getNum().equals(debitCardNum)) {
                        currentUser = user;
                        break;
                    }
                }

                if (currentUser != null) {
                    break;
                } else {
                    System.out.println("Card not recognized. Please try again.");
                }
            } catch (Exception e) {
                System.out.println("Card not recognized. Please try again.");
            }
        }

        System.out.println("Please enter your PIN:");
        int userPin = 0;
        while (true) {
            try {
                userPin = scanner.nextInt();

                if (currentUser.getPin() == userPin) {
                    break;
                } else {
                    System.out.println("Incorrect PIN. Please try again.");
                }
            } catch (Exception e) {
                System.out.println("Incorrect PIN. Please try again.");
                scanner.nextLine();  // Clear buffer
            }
        }

        System.out.println("Welcome " + currentUser.getFirstName() + " " + currentUser.getLastName());
        int option = 0;
        do {
            printOptions();
            try {
                option = scanner.nextInt();
            } catch (Exception e) {
                option = 0;
                scanner.nextLine();  // Clear buffer
            }
            switch (option) {
                case 1:
                    deposit(currentUser);
                    break;
                case 2:
                    withdraw(currentUser);
                    break;
                case 3:
                    balance(currentUser);
                    break;
                case 4:
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
                    option = 0;
                    break;
            }
        } while (option != 4);
        System.out.println("Thank you. Have a nice day!");

        scanner.close();
    }
}
