//MD: 11.9.23
import java.util.ArrayList;
import java.util.Calendar;

public class TransactionAccess {
    ArrayList<Transaction> transactions = new ArrayList<Transaction>();
    private static TransactionAccess transactionAccess;
    TransactionAccess(){
        String ISBN = "12345";
        String libraryNumber = "123";
        EntityManager entityManager = new EntityManager();
        entityManager.createTransaction(ISBN,libraryNumber);
        transactions.add(entityManager.createTransaction(ISBN,libraryNumber));
    }
    public static TransactionAccess getInstance(){
        if(transactionAccess == null){
            transactionAccess = new TransactionAccess();
        }
        return transactionAccess;
    }

    public Transaction searchByISBN(String isbn){
        Transaction transactionHold; 
        for (int i=0;i<transactions.size();i++){
            transactionHold = transactions.get(i);
            if(isbn.equalsIgnoreCase(transactionHold.getISBN().strip())){
                return transactionHold;
            }
        }
        return null;
    }

    //searchBy unneeded?
    public void addItem(Transaction transaction) {//adds a Transaction object to the ArrayList of Transactions 
        transactions.add(transaction);
    }

    public void editItem() {} //MD 11.9.23 NOTE: Do we need this for transactions?

    public void removeItem(Transaction transaction) {//calculates fees due and updates People fees accrued, then removes the item
            
            /* Call Ashen's calculation method
            
            long currDay = (System.currentTimeMillis()/1000/60/60/24); //converts to days and stores
            long daysPastDue = currDay - transaction.getDueDate(); //ex.) turned in 10.31 due 10.30
            if (daysPastDue > 0) {
                PersonAccess findPerson = new PersonAccess();
                People person = findPerson.searchByID(transaction.getLibraryNumber());
                //NOTE: Change to proper fees per day 
                person.setFeesDue(person.getFeesDue()+(0.15*daysPastDue));
            } */
        transactions.remove(transaction);
    }
}
