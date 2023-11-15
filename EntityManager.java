import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.*;
import java.util.Calendar;
import java.util.Date;

public class EntityManager {
	// creates a Book object
	public Book createBook (String title, String author, String ISBN, String genre, String copies) {
        // converts copies into an integer, and passes parameters into Book
		try{
			int copyInt = Integer.parseInt(copies);
			if (BookAccess.getInstance().searchByISBN(ISBN) == null) {
				Book book = new Book(title, author, ISBN, genre, copyInt);
				return book;
			}
			else {
				System.out.println(ISBN + " already belongs to an existing book");
				return null;
			}
		} catch(IllegalArgumentException e){
			System.out.println(copies + " is not an integer");
			e.printStackTrace();
			return null; 
		}
	}
	// creates a People object
	public People createPeople (String libraryNumber, String firstName, String lastName, String phoneNumber) {
        // passes parameters into People
		try{
			int libraryNumberInt = Integer.parseInt(libraryNumber);
			if (PersonAccess.getInstance().searchByLibraryNumber(libraryNumber) == null) {
				People people = new People(libraryNumberInt, firstName, lastName, phoneNumber);
				return people;
			}
			else {
				System.out.println(libraryNumber + " already belongs to an existing user.");
				return null;
			}
			
		} catch(IllegalArgumentException e){
			System.out.println(libraryNumber + " is not an integer");
			e.printStackTrace();
			return null; 
		}
		
	}
	// creates a Transaction object
	public Transaction createTransaction (String ISBN, String libraryNumber) {
        // converts libraryNumber to an integer
		try{
			int libraryNumberInt = Integer.parseInt(libraryNumber);
			// gets the current date
			Date currDate = new Date();
			Calendar dueDate = Calendar.getInstance();
			dueDate.setTime(currDate);
        	//adds 21 days to the current date
			dueDate.add(Calendar.DATE, 21);
        	// passes parameters into Transaction		
			Transaction transaction = new Transaction(ISBN, libraryNumberInt, dueDate);
			return transaction;
		} catch(IllegalArgumentException e){
			System.out.println(libraryNumber + " is not an integer");
			e.printStackTrace();
			return null; 
		}
	}
	// calculates fees due for a transaction
	public double calculateFees (Transaction transaction) {
		// gets dates
		Calendar dueDate = transaction.getDueDate();
		Calendar currDate = Calendar.getInstance();
		double fees = 0.0;
		if (dueDate.before(currDate)) {		// checks that the book is late
			// gets days between due date and current date
			int days = (int) Duration.between(dueDate.toInstant(), currDate.toInstant()).toDays();
			fees = days*0.1;		// ten cent fee per day
		}
		PersonAccess.getInstance().searchByLibraryNumber(Integer.toString(transaction.getLibraryNumber())).updateFeesDue(fees);
		return fees;
	}
	// converts a Calendar object to a String in the format of MM/dd/yyyy
	public String formatDate (Calendar date) {
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");	//month-day-year format
		return formatter.format(date);
	}
	// formats a double in 0.00 form.
	public String formatFees (double fees) {
		DecimalFormat formatter = new DecimalFormat("0.00");
		return formatter.format(fees);
	}
	// logic for checking out a book
	public Transaction checkOut(String ISBN, String libraryNumber) {
		Book book = BookAccess.getInstance().searchByISBN(ISBN);
		People people = PersonAccess.getInstance().searchByLibraryNumber(libraryNumber);
		if (book == null) {
			System.out.println("Invalid ISBN.");
			return null;
		}
		else if (people == null) {
			System.out.println("Invalid MemberID.");
			return null;
		}
		else if (book.getAvailableCopies()==0) {
			System.out.println("This book has no available copies at this time.");
			return null;
		}
		else {
			book.setAvailableCopies(book.getAvailableCopies()-1);
			Transaction transaction = createTransaction(ISBN, libraryNumber);
			return transaction;
		}
	}
	public void returnBook(Transaction transaction) {
		// AW 11/14 how to handle the same book checked out by different people
		// 		or the same person with multiple books checked out?
		if (TransactionAccess.getInstance().searchByLibraryNumber(Integer.toString(transaction.getLibraryNumber()))!=TransactionAccess.getInstance().searchByISBN(transaction.getISBN())) {
			System.out.println("This book has not been checked out!");
		}
		else {
			double fees = calculateFees(transaction);
			People user = PersonAccess.getInstance().searchByLibraryNumber(Integer.toString(transaction.getLibraryNumber()));
			Book book = BookAccess.getInstance().searchByISBN(transaction.getISBN());
			user.updateFeesDue(fees);
			book.setAvailableCopies(book.getAvailableCopies()+1);
			TransactionAccess.getInstance().removeItem(transaction);
		}
	}
}
