public class BookAccess implements AccessInterface {
    //searchBy methods: gets a search element (i.e. isbn) as a parameter, searches corresponding column, and creates a BookDetails object of all the info
    //addItem method: gets BookDetails object as a parameter, then adds to the database
    //editItem method: calls BookDetails.editBook, calls retrieveItem and replaces changed values
    //removeItem method: gets ISBN as parameter, calls searchByISBN and deletes that book  
}
