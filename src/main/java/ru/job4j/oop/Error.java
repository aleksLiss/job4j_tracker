package ru.job4j.oop;

public class Error {

    private boolean active;

    private int status;

    private String message;

    public Error(boolean active, int status, String message) {
        this.active = active;
        this.status = status;
        this.message = message;
    }

    public Error() {
    }

    public void showInfo() {
        System.out.println("Active: " + active);
        System.out.println("Status: " + status);
        System.out.println("Message: " + message);
    }

    public static void main(String[] args) {
        Error error1 = new Error();
        Error error2 = new Error(true, 400, "Bad request");
        Error error3 = new Error(false, 500, "Internal Server Error");
        Error error4 = new Error(true, 501, "Not Implemented");
        error1.showInfo();
        error2.showInfo();
        error3.showInfo();
        error4.showInfo();
    }
}
