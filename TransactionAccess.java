//MD: 11.9.23
import java.util.ArrayList;

public class TransactionAccess {
    ArrayList<Transaction> transactions = new ArrayList<Transaction>();

    //searchBy unneeded?
    public void addItem(Transaction transaction) {
        transactions.add(transaction);
    }

    public void editItem() {}

    public void removeItem(Transaction transaction) {//calculates fees due and updates People fees accrued, then removes the item
            long currDay = (System.currentTimeMillis()/1000/60/60/24); //converts to days and stores
            long daysPastDue = currDay - transaction.getDueDate(); //ex.) turned in 10.31 due 10.30
            if (daysPastDue > 0) {
                PersonAccess findPerson = new PersonAccess();
                People person = findPerson.searchByID(transaction.getLibraryNumber());
                //NOTE: Change to proper fees per day 
                person.setFeesDue(person.getFeesDue()+(0.15*daysPastDue));
            }
        transactions.remove(transaction);
    }
}
