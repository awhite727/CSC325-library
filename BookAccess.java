import java.util.List;
import java.util.ArrayList;
public class BookAccess {
    //searchBy methods: gets a search element (i.e. isbn) as a parameter, and returns the corresponding object
    //Or ArrayList of objects; Returns null if not found
    private List<Book> books = new ArrayList<Book>();
    private static BookAccess bookAccess = null;

    BookAccess(){
        books.add(new Book("The Great Gatsby", "F. Scott Fitzgerald", "123", "Fiction", 2));
        books.add(new Book("Lord of the Flies", "William Golding", "222", "Allegorical Novel", 3));
        books.add(new Book("The Hunger Games", "Suzanne Collins", "555", "Dystopian YA", 4));
        books.add(new Book("Catching Fire", "Suzanne Collins", "687", "Dystopian YA", 4));
        books.add(new Book("The Fault in Our Stars", "John Green", "179", "Young Adult", 1));
    }

    public static BookAccess getInstance(){
        if(bookAccess == null){
        bookAccess = new BookAccess();
        }
        return bookAccess;
    }

    public List<Book> getBooks(){
        return books;
    }

    public Book searchByISBN(String ISBN) {
        ISBN = ISBN.strip();
        String checkISBN = "";
        for (int i=0; i<books.size();i++){
            checkISBN = books.get(i).getISBN().strip();
            if (ISBN.equalsIgnoreCase(checkISBN)){
                return books.get(i);
            }
        }
        return null;
    }

    public ArrayList<Book> searchByAuthor(String author) {
        author = author.strip();
        ArrayList<Book> allByAuthor = new ArrayList<>();
        String checkAuthor = "";
        for (int i=0; i<books.size();i++){
            checkAuthor = books.get(i).getAuthor().strip();
            if (author.equalsIgnoreCase(checkAuthor)){
                allByAuthor.add(books.get(i));
            }
        }
        if (allByAuthor.isEmpty()){
            return null;
        }
        return allByAuthor;
    }

    public ArrayList<Book> searchByTitle(String title) {
        title = title.strip();
        ArrayList<Book> allOfTitle = new ArrayList<>();
        String checkTitle = "";
        for (int i=0; i<books.size();i++){
            checkTitle = books.get(i).getTitle().strip();
            if (title.equalsIgnoreCase(checkTitle)){
                allOfTitle.add(books.get(i));
            }
        }
        if (allOfTitle.isEmpty()){
            return null;
        }
        return allOfTitle;
    }

    public ArrayList<Book> searchByGenre(String genre) {
        genre = genre.strip();
        ArrayList<Book> allOfGenre = new ArrayList<>();
        String checkGenre = "";
        for (int i=0; i<books.size();i++){
            checkGenre = books.get(i).getGenre().strip();
            if (genre.equalsIgnoreCase(checkGenre)){
                allOfGenre.add(books.get(i));
            }
        }
        if (allOfGenre.isEmpty()){
            return null;
        }
        return allOfGenre;
    }

    public ArrayList<Book> searchByTotalCopies(String copies) {
        try{
            int totalCopies = Integer.parseInt(copies);
            ArrayList<Book> allOfCopies = new ArrayList<>();
            int checkCopies;
            for (int i=0; i<books.size();i++){
                checkCopies = books.get(i).getTotalCopies();
                if (totalCopies == checkCopies){
                    allOfCopies.add(books.get(i));
                }
            }
            if (allOfCopies.isEmpty()){
                return null;
            }
            return allOfCopies;
        } catch(IllegalArgumentException e){
            e.printStackTrace();
        }
        return null;
    }
    
    public void addItem(Book book){
        books.add(book);
    }

    public void editItem(Book original) {}//Empty because the book should automatically update as it is now, 
    //but it will be needed with the database

    public void removeItem(Book book) {//removes a Book object from the ArrayList of Books
        books.remove(book);
    }
}
