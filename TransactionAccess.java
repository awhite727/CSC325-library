import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class TransactionAccess {
    ArrayList<Transaction> transactions = new ArrayList<Transaction>();
    private static TransactionAccess transactionAccess;
    TransactionAccess(){
        Calendar calendarSetter = Calendar.getInstance();
		calendarSetter.setTime(new Date());
        Date dueDate;
        //due today
        transactions.add(new Transaction("123", 2,new Date())); 

        //due two days from now

		calendarSetter.add(Calendar.DATE,2);
		dueDate = calendarSetter.getTime();
        transactions.add(new Transaction("123", 3,dueDate));

        //due a week from now
        calendarSetter.add(Calendar.DATE,5);
        dueDate = calendarSetter.getTime();
        transactions.add(new Transaction("179", 3,dueDate));
        
        //due last week
		calendarSetter.add(Calendar.DATE,-14);
		dueDate = calendarSetter.getTime();
        transactions.add(new Transaction("555",4,dueDate));
        transactions.get(3).setDueDate(dueDate);
    }

    public static TransactionAccess getInstance(){
        if(transactionAccess == null){
            transactionAccess = new TransactionAccess();
        }
        return transactionAccess;
    }

    public static void main(String[] args) {
        TransactionAccess ta = TransactionAccess.getInstance();
        EntityManager et = new EntityManager();
        for (int i=0;i<ta.transactions.size(); i++) {
            System.out.println(i+1 + ": " + et.formatDate(ta.transactions.get(i).getDueDate()));
        }
    }

    public ArrayList<Transaction> searchByLibraryNumber(String libraryNumber){
        Transaction transactionHold; 
        ArrayList<Transaction> allOfLibraryNumber = new ArrayList<>();
        for (int i=0;i<transactions.size();i++){
            transactionHold = transactions.get(i);
            if(libraryNumber.strip().equalsIgnoreCase(Integer.toString(transactionHold.getLibraryNumber()))){
                allOfLibraryNumber.add(transactionHold);
            }
        }
        if (allOfLibraryNumber.isEmpty()){
            return null;
        }
        return allOfLibraryNumber;
    }

    public ArrayList<Transaction> searchByISBN(String isbn){
        Transaction transactionHold;
        ArrayList<Transaction> allOfIsbn = new ArrayList<>();
        for (int i=0;i<transactions.size();i++){
            transactionHold = transactions.get(i);
            if(isbn.equalsIgnoreCase(transactionHold.getISBN().strip())){
                allOfIsbn.add(transactionHold);
            }
        }
        if (allOfIsbn.isEmpty()){
            return null;
        }
        return allOfIsbn;
    }

    public ArrayList<Transaction> searchByBoth(String memberID, String isbn){
        ArrayList<Transaction> matchingFields = new ArrayList<>();
        ArrayList<Transaction> transaction1 = searchByLibraryNumber(memberID);
		ArrayList<Transaction> transaction2 = searchByISBN(isbn);
        if ((transaction1 == null)||(transaction2 == null)){
            return matchingFields;
        }
        //searches all transactions oldest to most recent and adds all transactions with matching isbn and memberID
		for(int i=0; i < transaction1.size(); i++){ 
			for(int j=0; j < transaction2.size(); j++){
				if (transaction1.get(i) == transaction2.get(j)){
					System.out.println("1: " + transaction1.get(i));
					System.out.println("2: " + transaction2.get(j));
					matchingFields.add(transaction1.get(i));
				}
			}
		}
        return matchingFields;
    }

    public Transaction mostRecentTransaction(){
        return transactions.get(transactions.size()-1);
    }

    //searchBy unneeded?
    public void addItem(Transaction transaction) {//adds a Transaction object to the ArrayList of Transactions 
        transactions.add(transaction);
    }

    public void editItem(){}//Currently unnecessary, but will edit the sql database

    public void removeItem(Transaction transaction) {//removes the transaction from the arraylist
        transactions.remove(transaction);
    }
}
