//MD: 11.5.23
import java.util.ArrayList;

public class TransactionAccess {//implements AccessInterface {
    ArrayList<Transaction> transactions = new ArrayList<Transaction>();

    //searchBy unneeded?

    public void addItem(Transaction transaction) {
        transactions.add(transaction);

    }

    public void editItem() {}//NOTE: Is this unnecessary? 

    public void removeItem(Transaction transaction) {//calculates fees due and updates People fees accrued, then removes the item
        try {
            int currDay = (int) (System.currentTimeMillis()/1000/60/60/24); //converts to days and stores
            int daysPastDue = currDay - Integer.parseInt(transaction.getDueDate()); //ex.) turned in 10.31 due 10.30
            if (daysPastDue > 0) {//TO DO: Complete //NOTE: Should be another class for SOLID principles? 
                PersonAccess findPerson = new PersonAccess();
                People person = findPerson.searchByID(transaction.getLibraryNumber());
                //NOTE: Change to proper fees per day 
                person.setFeesDue(person.getFeesDue()+(0.15*daysPastDue));
            }
        }
        catch (IllegalArgumentException e){
            System.out.println("Can't parse int: " + transaction.getDueDate());
        }
        transactions.remove(transaction);
    }
    

    //see AccessInterface
    //whenever a book is returned and the transaction is going to be removed, call the personAccess to update fees
    
    //searchBy methods: gets a userID as a parameter searches, and creates a TransactionDetails object of all the info
    //addItem method: gets TransactionDetails object as a parameter, then adds to the database
    //editItem method: calls TransactionDetails.editTransaction, calls searchByUserID and replaces changed values
    //removeItem method: gets userID and ISBN as parameters, calculates the fees accrued, edits the total fees in the Person database, then 
    //removes the item from the database    
}
