package ui;
// Manages main actions of program

import model.Account;
import model.LogEntry;

import java.util.ArrayList;
import java.util.List;

// TODO: Restructure Heavily
public class Manage {

    private List<Account> accounts = new ArrayList<>();

    public Manage() {
        runManage(); // from Teller
    }

    private void runManage(){
        //stub
    }

    private void makeAccount(String name, int val) {
        accounts.add(new Account(name,val));
    }

    private void moveMoney(Account acc1, Account acc2, int val, String date){
        //stub
    }

    private void addMoney(Account acc, int val, String date){
        //stub
    }

    private void removeMoney(Account acc, int val, String date){
        //stub
    }

    private void logAction(Account acc, int val, String date){
        //stub
    }

    private void returnHistory(){
        //stub
    }

    private void returnAccValues(){
        //stub
    }
}
