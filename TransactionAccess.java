import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class TransactionAccess {
    ArrayList<Transaction> transactions = new ArrayList<Transaction>();
    private static TransactionAccess transactionAccess;
    TransactionAccess(){
        EntityManager entityManager = new EntityManager();
        transactions.add(entityManager.createTransaction("123","2"));
        transactions.add(entityManager.createTransaction("123","3"));
        Date dateTest = new Date();
        dateTest.add(Calendar.DATE, -7);
        transactions.add(entityManager.createTransaction("555","4").setDueDate(dateTest));


    }
    public static TransactionAccess getInstance(){
        if(transactionAccess == null){
            transactionAccess = new TransactionAccess();
        }
        return transactionAccess;
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

    //searchBy unneeded?
    public void addItem(Transaction transaction) {//adds a Transaction object to the ArrayList of Transactions 
        transactions.add(transaction);
    }

    public void editItem(){}//Currently unnecessary, but will edit the sql database

    public void removeItem(Transaction transaction) {//removes the transaction from the arraylist
        transactions.remove(transaction);
    }
}
