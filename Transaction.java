import java.util.Date;

public class Transaction {

    private String ISBN;
    private int libraryNumber;
    private Date dueDate;
    //constructor
    public Transaction(String ISBN, int libraryNumber, Date dueDate) {
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
    public Date getDueDate() {
        return dueDate;
    }
    //setters for the attributes
    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }
    public void setLibraryNumber(int libraryNumber) {
        this.libraryNumber = libraryNumber;
    }
    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

}