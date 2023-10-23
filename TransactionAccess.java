public class TransactionAccess implements AccessInterface {
    //see AccessInterface
    //whenever a book is returned and the transaction is going to be removed, call the personAccess to update fees
    
    //searchBy methods: gets a userID as a parameter searches, and creates a TransactionDetails object of all the info
    //addItem method: gets TransactionDetails object as a parameter, then adds to the database
    //editItem method: calls TransactionDetails.editTransaction, calls searchByUserID and replaces changed values
    //removeItem method: gets userID and ISBN as parameters, calculates the fees accrued, edits the total fees in the Person database, then 
    //removes the item from the database    
}
