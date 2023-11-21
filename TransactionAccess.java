import java.util.ArrayList;

public class TransactionAccess {
    ArrayList<Transaction> transactions = new ArrayList<Transaction>();
    private static TransactionAccess transactionAccess;
    TransactionAccess(){
        EntityManager entityManager = new EntityManager();
        transactions.add(entityManager.createTransaction("123","2"));
        transactions.add(entityManager.createTransaction("123","3"));
        transactions.add(entityManager.createTransaction("555","4"));


    }
    public static TransactionAccess getInstance(){
        if(transactionAccess == null){
            transactionAccess = new TransactionAccess();
        }
        return transactionAccess;
    }

    public Transaction searchByLibraryNumber(String libraryNumber){
        Transaction transactionHold; 
        for (int i=0;i<transactions.size();i++){
            transactionHold = transactions.get(i);
            if(libraryNumber.strip().equalsIgnoreCase(Integer.toString(transactionHold.getLibraryNumber()))){
                return transactionHold;
            }
        }
        return null;
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

    public void editItem(){}//Currently unnecessary, but will edit the sql database

    public void removeItem(Transaction transaction) {//removes the transaction from the arraylist
        transactions.remove(transaction);
    }
}
