package ru.job4j.pojo;

public class Library {
    public static void main(String[] args) {
        Book book1 = new Book("Philosophy of Java", 873);
        Book book2 = new Book("Effective Java", 212);
        Book book3 = new Book("Netty in action", 284);
        Book book4 = new Book("Clean code", 341);
        Book[] books = new Book[4];
        books[0] = book1;
        books[1] = book2;
        books[2] = book3;
        books[3] = book4;
        for (int index = 0; index < books.length; index++) {
            System.out.println(books[index].getName());
        }
        Book temp = books[0];
        books[0] = books[3];
        books[3] = temp;
        for (int index = 0; index < books.length; index++) {
            if ("Clean code".equals(books[index].getName())) {
                System.out.println(books[index].getName());
                System.out.println(books[index].getPages());
            }
        }

    }
}
