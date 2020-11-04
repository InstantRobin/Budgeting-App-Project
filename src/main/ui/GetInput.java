package ui;

import model.Account;
import model.Currency;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GetInput {
    //    currencies still TODO
    //    private List<Currency> currencies;
    private Scanner sc = new Scanner(System.in);


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
    static Currency getCurrencyInput(List<Account> accounts) {
        // TODO: Implement Currency Array, default USD
        // TODO: Save Currency Array?
        Scanner sc = new Scanner(System.in);
        System.out.println("Select Currency");
        List<Currency> currencies = new ArrayList<>();
        int item = 0;

        for (Account acc : accounts) {
            if (!currencies.contains(acc.getCurrency())) {
                currencies.add(acc.getCurrency());
                System.out.println((item + 1) + ") " + currencies.get(item).getName());
                item += 1;
            }
        }

        System.out.println(item + 1 + ") New Currency");

        int choice = sc.nextInt();
        if (choice == item + 1) {
            return newCurrency();
        } else {
            return currencies.get(choice);
        }

    }

    private static Currency newCurrency() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Currency Name:");
        String name = sc.next();
        System.out.println("Existing Currency not found, please enter information for this Currency");
        System.out.println("Enter Symbol:");
        String symbol = sc.next();
        System.out.println("Enter Exchange Rate to USD");
        int exRate = sc.nextInt();
        return (new Currency(name,symbol,exRate));
    }
}
