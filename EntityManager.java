import java.text.SimpleDateFormat;
import java.time.*;
import java.util.Calendar;
import java.util.Date;

public class EntityManager {
	// creates a Book object
	public Book createBook (String title, String author, String ISBN, String genre, String copies) {
        // converts copies into an integer, and passes parameters into Book
		int copyInt = Integer.parseInt(copies);
		Book book = new Book(title, author, ISBN, genre, copyInt);
		return book;
	}
	// creates a People object
	public People createPeople (String libraryNumber, String firstName, String lastName, String phoneNumber) {
        // passes parameters into People
		try{
			int libraryNumberInt = Integer.parseInt(libraryNumber);
			People people = new People(libraryNumberInt, firstName, lastName, phoneNumber);
			return people;
			
		} catch(IllegalArgumentException e){
			System.out.println(libraryNumber + " is not an integer");
			e.printStackTrace();
			return null; 
		}
		
	}
	// creates a Transaction object
	public Transaction createTransaction (String ISBN, String libraryNumber) {
        // converts libraryNumber to an integer
		int libraryNumberInt = Integer.parseInt(libraryNumber);
        // gets the current date
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");	//month-day-year format
		Calendar dueDate = Calendar.getInstance();
		dueDate.setTime(new Date());
        //adds 21 days to the current date
		dueDate.add(Calendar.DATE, 21);
        // passes parameters into Transaction		
		Transaction transaction = new Transaction(ISBN, libraryNumberInt, dueDate);
		return transaction;
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
		return fees;
	}
	// converts a Calendar object to a String in the format of MM/dd/yyyy
	public String formatDate (Calendar date) {
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");	//month-day-year format
		return formatter.format(date);
	}
}
