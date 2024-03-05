package client;

import java.io.Serializable;

public class ClientData implements Serializable {
    private String name;
    private String lastname;
    private String pesel;
    private String accountNumber;
    private double balance;

    public ClientData(String name, String lastname, String pesel, String accountNumber, double balance) {
        this.name = name;
        this.lastname = lastname;
        this.pesel = pesel;
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    // Геттеры и сеттеры
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPesel() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    // Метод для отображения информации о клиенте
    public void displayInfo() {
        System.out.println("Name: " + name);
        System.out.println("Lastname: " + lastname);
        System.out.println("PESEL: " + pesel);
        System.out.println("Account number: " + accountNumber);
        System.out.println("Balance: " + balance);
    }
}
