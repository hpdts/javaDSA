package com.example;
import java.util.*;

class User {
    private int id;
    private String name;
    private String lastName;
    
    public User(final int id, final String name, final String lastName) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
    }

    public String getName() {
        return this.name + "-" + this.lastName;
    }

    public String toString() {
        return this.id + ":" + this.name + "-" + this.lastName;
    }

}

public class LibraryApp {
    public static void main(String[] args) {
        Library library = new Library();
        Book effectiveJava = new Book(1, "Effective Java", "Joshua Bloch");
        Book effectiveJava1 = new Book(3, "Effective Java", "Joshua Bloch");
        Book atomicHabits = new Book(2, "Atomic Habits", "James Clear");
        Book atomicHabits1 = new Book(4, "Atomic Habits", "James Clear");
        Book sevenHabits = new Book(5, "The 7 Habits of Highly Effective People", "Stephen Covey");
        Book sevenHabitsSameID = new Book(5, "The 7 Habits of Highly Effective People copy", "Stephen Covey");
        library.addBook(effectiveJava);
        library.addBook(effectiveJava1);
        library.addBook(atomicHabits);
        library.addBook(atomicHabits1);
        library.addBook(atomicHabits1);
        library.addBook(sevenHabits);
        library.addBook(sevenHabitsSameID);
        System.out.println("Library books: " + library.getBooks());

        User francisco = new User(1, "Francisco", "Hernandez");
        User martin = new User(2, "Martin", "Hernandez");
        library.registerUser(martin);
        library.registerUser(francisco);
        library.registerUser(francisco);
        System.out.println("Library users: "  + library.getUsers());
        //fetch books
        List<Book> books = library.fetchBooks("Atomic Habits");
        System.out.println("Books found: " + books);
        User james = new User(3, "James", "Hetfield");
        //Borrow a book
        try{
            library.borrowBook(books.get(0), james);
            library.borrowBook(books.get(0), francisco);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        try{
            library.borrowBook(books.get(0), francisco);
            library.borrowBook(books.get(0), francisco);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        //return book
        try{
            library.returnBook(books.get(0), james);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }

        try{
            library.returnBook(books.get(1), francisco);
            library.returnBook(books.get(0), francisco);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }

    }
}

/*

users
borrow 
return 
book title
*/
class Book {
    private int id;
    private String title;
    private String author;

    public Book(final int id, final String title, final String author) {
        this.id = id;
        this.title = title;
        this.author = author;
    }

    public String getTitle() {
        return title;
    }
    
    public int getBookId() {
        return id;
    }
    
    public String getAuthor() {
        return author;
    }

    public String toString() {
        return this.id + ":" + this.title + "-" + this.author;
    }
}
