import java.util.Calendar;

public class Transaction {

    private String ISBN;
    private int libraryNumber;
    private Calendar dueDate;
    //constructor
    public Transaction(String ISBN, int libraryNumber, Calendar dueDate) {
        this.ISBN = ISBN;
        this.libraryNumber = libraryNumber;
        this.dueDate = dueDate;         // Calculate this in the constructor, or some other class?
    }
    //getters for the attributes
    public String getISBN() {
        return ISBN;
    }
    public int getLibraryNumber() {
        return libraryNumber;
    }
    public String getDueDate() {
        return dueDate;
    }
    //setters for the attributes
    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }
    public void setLibraryNumber(int libraryNumber) {
        this.libraryNumber = libraryNumber;
    }
    public void setDueDate(Calendar dueDate) {
        this.dueDate = dueDate;
    }

}