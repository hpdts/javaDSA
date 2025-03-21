import java.util.*;

public class Library {

/**

bookBorrow
1, 1

**/
    private Set<Book> books = new HashSet<>();
    private Set<User> usersRegistered = new HashSet<>();
    private Map<Book, User> borrowedBooks = new HashMap<>();
    
    public void addBook(Book book) {
        if (books.contains(book)) {
            System.out.println("Book already exists: " + book.getTitle());
            return;
        }
        for (Book b : books) {
            if (b.getBookId() == book.getBookId()) {
                System.out.println("Book with ID " + book.getBookId() + " already exists: " + book.getTitle());
                return;
            }
        }
        books.add(book);
    }

    public Set<Book> getBooks() {
        return books;
    }

    public List<Book> fetchBooks(final String bookTitle) {
        List<Book> booksFound = new ArrayList<>();
        for(Book book : books){
            if(book.getTitle().equals(bookTitle)){
                booksFound.add(book);
            }
        }
        return booksFound;
    }

    public void registerUser(User user){
        //set fix the problem of duplicate users
        usersRegistered.add(user);
    }

    public Set<User> getUsers(){
        return usersRegistered;
    }
    
    
    public void borrowBook(Book book, User user) throws Exception{
        if (usersRegistered.contains(user) ){
            if (borrowedBooks.containsKey(book)) {
                throw new Exception("Book already borrowed: " + book.getTitle());
            }
            if (books.contains(book)) {
                borrowedBooks.put(book, user);
                System.out.println("Book borrowed: " + book);
            } else {
                throw new Exception("Book either not found or available: " + book.getTitle());
            }
        } else{
            throw new Exception("User: " + user + ", not registered on the library");
        }
        // if(registeredIdUsers.contains(userId)){
        //     //multiple copies
        //     List<Book> books = fetchBooks(bookTitle);
        //     for(Book book: books){
        //         if(!bookBorrowed.containsKey(book.getBookId())){
        //             if (book != null) {
        //                 System.out.println("Book borrowed: " + bookTitle);
        //             } else {
        //                 System.out.println("Book either not found or available: " + bookTitle);
        //             }
        //             bookBorrowed.put(book.getBookId(), userId);
        //         }
        //     }
        // }else{
        //     throw new Exception("User not regiestred on the library");
        // }

    }

    public void returnBook(Book returnedBook, User user){
        // boolean foundBook = false;
        if (usersRegistered.contains(user) ){
            if (borrowedBooks.containsKey(returnedBook)) {
                borrowedBooks.remove(returnedBook);
                System.out.println("Book returned: " + returnedBook);
                return;
            }else{
                System.out.println("Book has not being borrowed, you can't return it: " + returnedBook);
            }
            // for (Book book : borrowedBooks.keySet()) {
            //     if (book.getBookId() == bookID) {
            //         borrowedBooks.remove(book);
            //         System.out.println("Book returned: " + book);
            //         return;
            //     }
            // }
            System.out.println("Book not borrowed: " + returnedBook);
        } else{
            System.out.println("User: " + user + ", not registered on the library for returning books");
        }
        //     for(String bookIDBorrowed : bookBorrowed.KeySet()){
        //         if(bookIDBorrowed == bookID){
        //             bookBorrowed.removeKey(bookIDBorrowed);
        //             foundBook = true;
        //             break;
        //         }
        //     }
        //     if(!foundBook){
        //         System.out.println("Book has not been borrowed it");
        //     } 
        // }else{
        //     throw new Exception("User not regiestred on the library");
        // }
    }
}