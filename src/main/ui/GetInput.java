package ui;

import model.Account;
import model.Currency;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class GetInput {

    // EFFECT: Presents user with list of accounts, takes user input, returns selected account
    static Account getAccInput(List<Account> accounts) {
        //TODO: Don't allow duplicate names
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the corresponding number of desired account");
        for (int a = 0; a < accounts.size(); a++) {
            System.out.println((a + 1) + ") " + accounts.get(a).getName());
        }

        int acc = sc.nextInt();
        return accounts.get(acc - 1);
    }

    // EFFECT: Gets int value from User
    static int getValInput() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter desired amount:");
        return (int)(sc.nextFloat() * 100);
    }

    // EFFECT: Gets LocalDate date from user, separate input for year, month, date
    static LocalDate getDateInput() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter date of action in number form:");
        System.out.println("Year:");
        int year = sc.nextInt();
        System.out.println("Month:");
        int month = sc.nextInt();
        System.out.println("Day:");
        int day = sc.nextInt();

        return LocalDate.of(year,month,day);
    }

    // EFFECT: Gets string name from user
    static String getNameInput() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter name:");
        return sc.next();
    }

    // EFFECT: Gets Currency from user
    static Currency getCurrencyInput(List<Currency> currencies) {
        // TODO: Implement Currency Array, default USD
        // TODO: Save Currency Array?
        Scanner sc = new Scanner(System.in);
        System.out.println("Select Currency");
        int item = 1;

        for (Currency curr : currencies) {
            System.out.println((item) + ") " + currencies.get(item - 1).getName());
            item += 1;
        }

        System.out.println(item + ") New Currency");

        int choice = sc.nextInt();
        if (choice == item) {
            return newCurrency();
        } else {
            return currencies.get(choice - 1);
        }

    }

    // EFFECTS: Takes user input, sees if it is in use, if not, prompts user for creation of new currency
    private static Currency newCurrency() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Currency Name:");
        String name = sc.next();
        System.out.println("Existing Currency not found, please enter information for this Currency");
        System.out.println("Enter Symbol:");
        String symbol = sc.next();
        System.out.println("Enter Exchange Rate to USD");
        double exRate = sc.nextDouble();
        return (new Currency(name,symbol,exRate));
    }
}
