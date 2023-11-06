//MD: 11.5.23


/*import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;*/
import java.util.ArrayList;
//SQL Database called Books
public class BookAccess {//implements AccessInterface {
    //searchBy methods: gets a search element (i.e. isbn) as a parameter, searches corresponding column, and creates a BookDetails object of all the info
    private ArrayList<Book> books = new ArrayList<Book>();
    //TO DO ALL: Make it take parameter
    

//CHECK: When he menat hard code data did he mean actually just use book class to grab data and then just return the stuff or like is this it or osmething headache brain go brrrr ugh



    public Book searchByISBN(String ISBN) {//NOTE: Not efficient 
        String checkISBN = "";
        for (int i=0; i<books.size();i++){
            checkISBN = books.get(i).getISBN();
            if (ISBN.equalsIgnoreCase(checkISBN)){
                return books.get(i);
            }
        }
        return null; //couldn't find the object
    }

    public ArrayList<Book> searchByAuthor(String author) {//NOTE: Not efficient
        ArrayList<Book> allByAuthor = new ArrayList<>();
        String checkAuthor = "";
        for (int i=0; i<books.size();i++){
            checkAuthor = books.get(i).getAuthor();
            if (author.equalsIgnoreCase(checkAuthor)){
                allByAuthor.add(books.get(i));
            }
        }
        if (allByAuthor.isEmpty()){
            return null;//couldn't find the object
        }
        return allByAuthor;
    }

    public ArrayList<Book> searchByTitle(String title) {//NOTE: Not efficient
        ArrayList<Book> allOfTitle = new ArrayList<>();
        String checkTitle = "";
        for (int i=0; i<books.size();i++){
            checkTitle = books.get(i).getTitle();
            if (title.equalsIgnoreCase(checkTitle)){
                allOfTitle.add(books.get(i));
            }
        }
        if (allOfTitle.isEmpty()){
            return null;//couldn't find the object
        }
        return allOfTitle;
    }

    public ArrayList<Book> searchByGenre(String genre) {//NOTE: Not efficient
        ArrayList<Book> allOfGenre = new ArrayList<>();
        String checkGenre = "";
        for (int i=0; i<books.size();i++){
            checkGenre = books.get(i).getGenre();
            if (genre.equalsIgnoreCase(checkGenre)){
                allOfGenre.add(books.get(i));
            }
        }
        if (allOfGenre.isEmpty()){
            return null;//couldn't find the object
        }
        return allOfGenre;
    }
    
    public void addItem(Book book){
        books.add(book);
    }

    public void editItem(Book original) {}//Empty because the book should automatically update as it is now, but will be needed with the database

    public void removeItem(Book book) {
        books.remove(book);
    }

    /* @Override
    public void addItem(Book book){
        String[] bookInfo = new String[6];
        String url;
        String username;
        String password;
        Connection connection;
        String insertQuery;
        try {
            //set up connection
            url = "DATABASEURL"; // JDBC URL for the database
            username = "SQLUSERNAME"; // Your MySQL username
            password = "SQLPASSWORD"; // Your MySQL password
            connection = DriverManager.getConnection(url, username, password); //connect to url with your user and password
            //insert statement basically defining the Sql "Books" we're connecting to
            insertQuery = "INSERT INTO Books (isbn, title, author, genre, totalCopies, availableCopies) VALUES (?, ?, ?, ?, ?, ?)";
            //Prepping for insertion
            try (PreparedStatement preparedStatement =
            connection.prepareStatement(insertQuery)) {
            // Set the parameter values for the INSERT statement.
            preparedStatement.setString(1, book.getISBN());
            preparedStatement.setString(2, book.getTitle());
            preparedStatement.setString(3, book.getAuthor());
            preparedStatement.setString(4, book.getGenre());
            //TO DO: Following 2 have error because expects a string and got an int
            preparedStatement.setString(5, book.getTotalCopies());
            preparedStatement.setString(6, book.availableCopies()); //TO DO: Edit once naming corrected
            //Actually inserting
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
            System.out.println("New user record inserted successfully.");
            } else {
            System.out.println("Insert operation failed.");
            }
            }
            connection.close();
            } catch (SQLException e) {
            e.printStackTrace();
            }
    } */
}
