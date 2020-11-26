# Budgeteer

## A budgeting program

This is a program designed to facilitate the management of one's money. 

It is able to:
 - Add or remove money from accounts
 - Move money between accounts
 - Log money management history
 - Create graphs and give data on said history
 - Operate with multiple currencies
 
This project is of interest to me because I've always had an interest in data related to my life,
and I've always wanted a system that was more powerful than a spreadsheet but didn't decrease the amount
of control I had over its operation. It should be useful for anyone who has an interest in managing
their money, especially those who live in multiple countries, like myself.


User Stories:

As a user, I want to be able to set up an account <br>
As a user, I want to be able to set up multiple accounts <br>
As a user, I want to be able to add money to a given account, and give a date of the action <br>
As a user, I want to be able to remove money from an account, and give a date of the action <br>
As a user, I want to be able to move money between accounts, and give a date of the action <br>
As a user, I want to be able to view the values on an account <br>
As a user, I want to be able to assign a currency to an account <br>

As a user, I want to be able to log my actions and their date <br>
As a user, I want to be able to add logged action to a history of logged actions <br>
As a user, I want to be able to sort and update this history <br>
As a user, I want to be able to print a history of these actions <br>

As a user, I want to be able to store my account data to a file<br>
As a user, I want to be able to load my account data from a file<br>

As a user, I want to be able to create charts/graphs of this data <br>


Phase 4: Task 2<br>
This program uses a type hierarchy in the form of different types of Windows <br>
These are all descended from the Window type, which has an abstract updateUI method that is overriden by each <br>
Classes that override this method are: Home, ManageAccounts, ManageSavedData, MoveMoney, MoveMoneyWindow, MakeAccount, ViewAccount, ViewAccountHistory, and ViewGraph <br>


Phase 4: Task 3<br>
 - Remove initial value from Account creation, it adds confusion to the history and would simplify the methods that rely on it, specifically ViewAccountHistory and ViewGraph <br>
 - There seems to be some functional overlap between Home and SubWindowWithInputs, I would like to try to refactor it so that this redundancy can be reduced. <br>