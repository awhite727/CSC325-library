import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class EntityManager {
	protected static final String Transaction = null;
	// creates a Book object
	public String createBook (String title, String author, String ISBN, String genre, String copies) {
        // converts copies into an integer, and passes parameters into Book
		
		try{
			if((title==null)||(author==null)||(ISBN==null)||(genre==null)||(copies==null)){
				throw new EmptyFieldException();
			}

			int copyInt = Integer.parseInt(copies);
			if (BookAccess.getInstance().searchByISBN(ISBN) == null) {
				Book book = new Book(title, author, ISBN, genre, copyInt);
				BookAccess.getInstance().addItem(book);
				return null;
			}
			else {
				return ISBN + " already belongs to an existing book";
			}
		} catch(IllegalArgumentException e){
			return "\"" + copies + "\"" + " is not an integer";
		} catch (EmptyFieldException except){
			return "All text fields must be filled to create a book";
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
			// gets the current date and adds 21
			Calendar dueDateTemp = Calendar.getInstance();
			dueDateTemp.setTime(new Date());
			dueDateTemp.add(Calendar.DATE,21);
			Date dueDate = dueDateTemp.getTime();
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
		Date dueDate = transaction.getDueDate();
		Date currDate = new Date();
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
	public String formatDate (Date date) {
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");	//month-day-year format
		return formatter.format(date);
	}
	// formats a double in 0.00 form.
	public String formatFees (double fees) {
		DecimalFormat formatter = new DecimalFormat("0.00");
		return "$" + formatter.format(fees);
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
	public Transaction returnBook(String memberID, String isbn) {
		// AW 11/14 how to handle the same book checked out by different people
		// 		or the same person with multiple books checked out?
		ArrayList<Transaction> transaction1 = TransactionAccess.getInstance().searchByLibraryNumber(memberID);
		ArrayList<Transaction> transaction2 = TransactionAccess.getInstance().searchByISBN(isbn);
		Transaction transaction = null;
		for(int i=0; i < transaction1.size(); i++){
			for(int j=0; j < transaction2.size(); j++){
				if (transaction1.get(i) == transaction2.get(j)){
					System.out.println("1: " + transaction1.get(i));
					System.out.println("2: " + transaction2.get(j));
					transaction = transaction1.get(i);
				}
			}
		}
		
		if (transaction == null) {
			System.out.println("This book has not been checked out!");
			return null;
		}
		else {
			double fees = calculateFees(transaction);
			People user = PersonAccess.getInstance().searchByLibraryNumber(memberID);
			Book book = BookAccess.getInstance().searchByISBN(isbn);
			user.updateFeesDue(fees);
			book.setAvailableCopies(book.getAvailableCopies()+1);
			//TransactionAccess.getInstance().removeItem(transaction);
			System.out.println("Avaliable Copies:"+ book.getAvailableCopies());
			return transaction;
		}
	}
}
