public class PersonAccess implements AccessInterface {
    //searchBy methods: gets a search element (i.e. userID) as a parameter, searches corresponding column, and creates a PersonDetails object of all the info
    //addItem method: gets PersonDetails object as a parameter, then adds to the database
    //editItem method: calls PersonDetails.editPerson, calls searchByUserID and replaces changed values
    //removeItem method: gets ISBN as parameter, calls searchByUserID and deletes that book  
}