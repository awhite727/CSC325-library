import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class EntityManager {
	protected static final String Transaction = null;

	public String copiesIsInt(String copies){
		try{
			int copyInt = Integer.parseInt(copies);
			if (copyInt < 1){
				return "The number of copies must be more than 0";
			}else {
				return null;
			}
		} catch(IllegalArgumentException e){
			return "Quantity must be a number";
		}
	}

	public String memberIdIsInt(String memberID){
		try{
			if(memberID.equals("")){
				return "Please include something in the text field.";
			}
			int libraryNumberInt = Integer.parseInt(memberID);
			return null;
		} catch(IllegalArgumentException e){
			return "MemberID must be a number";
		}
	}

	// creates a Book object
	public String createBook (String title, String author, String ISBN, String genre, String copies) {
        // converts copies into an integer, and passes parameters into Book
		if(title.equals("")||author.equals("")||ISBN.equals("")||genre.equals("")||copies.equals("")){
			return "Please include something in all fields.";
		} else {
			String isCopiesInt = copiesIsInt(copies);
			if (isCopiesInt == null){
				if (BookAccess.getInstance().searchByISBN(ISBN) == null) {
					Book book = new Book(title, author, ISBN, genre, Integer.parseInt(copies));
					BookAccess.getInstance().addItem(book);
					return null;
				}
				else {
					return ISBN + " already belongs to an existing book";
				}
			} else{
				return isCopiesInt;
			}
		}
	}
	// creates a People object
	public String createPeople (String libraryNumber, String firstName, String lastName, String phoneNumber) {
        // passes parameters into People
		try{
			if(libraryNumber.equals("")||firstName.equals("")||lastName.equals("")||phoneNumber.equals("")){
				return "Please include something in all fields.";
			} else {
			if (memberIdIsInt(libraryNumber)==null){
				if (PersonAccess.getInstance().searchByLibraryNumber(libraryNumber) == null) {
					People people = new People(Integer.parseInt(libraryNumber), firstName, lastName, phoneNumber);
					PersonAccess.getInstance().addItem(people);
					return null;
				}
				else {
					return "\"" + libraryNumber + "\" already belongs to an existing user.";
				}
			} else{
				return memberIdIsInt(libraryNumber);
			}
		}
		} catch(IllegalArgumentException e){
			return "MemberID must be a number";
		}
		
	}
	// creates a Transaction object
	public String createTransaction (String ISBN, String libraryNumber) {
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
			TransactionAccess.getInstance().addItem(transaction);
			return null;
		} catch(IllegalArgumentException e){
			return "MemberID must be a number";
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
	public String checkOut(String ISBN, String libraryNumber) {
		Book book = BookAccess.getInstance().searchByISBN(ISBN);
		People people = PersonAccess.getInstance().searchByLibraryNumber(libraryNumber);
		if (book == null) {
			return "That book does not exist";
		}
		else if (people == null) {
			return "That user does not exist";
		}
		else if (book.getAvailableCopies()==0) {
			return "This book has no available copies at this time.";
		}
		else {
			book.setAvailableCopies(book.getAvailableCopies()-1);
			String checkAndCreate = createTransaction(ISBN, libraryNumber);
			return checkAndCreate;
		}
	}
	public String returnBook(String memberID, String isbn) {
		ArrayList<Transaction> allMatchingTransactions = TransactionAccess.getInstance().searchByBoth(memberID,isbn);
		if (allMatchingTransactions.isEmpty()) {
			return "This book has not been checked out by the listed user";
		}
		else {
			//assume they are returning the oldest book
			double fees = calculateFees(allMatchingTransactions.get(0));
			People user = PersonAccess.getInstance().searchByLibraryNumber(memberID);
			Book book = BookAccess.getInstance().searchByISBN(isbn);
			user.updateFeesDue(fees);
			book.setAvailableCopies(book.getAvailableCopies()+1);
			TransactionAccess.getInstance().removeItem(allMatchingTransactions.get(0));

			return formatFees(fees);
		}
	}
}
