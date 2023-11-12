//MD: 11.9.23
import java.util.List;
import java.util.ArrayList;
public class BookAccess {
    //searchBy methods: gets a search element (i.e. isbn) as a parameter, and returns the corresponding object
    //Or ArrayList of objects; Returns null if not found
    private List<Book> books = new ArrayList<Book>();
    private static BookAccess bookAccess = null;

    BookAccess(){
        books.add(new Book("The Great Gatsby", "IDKLOL", "123", "Fantasy", 2));
    }

    public static BookAccess getInstance(){
        if(bookAccess == null){
        bookAccess = new BookAccess();
        }
        return bookAccess;
    }

    public List<Book> getBooks(){
        for(int i=0;i<books.size();i++){
            System.out.println(i + ": " + books.get(i).getISBN());
        }
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
    
    public void addItem(Book book){
        books.add(book);
    }

    public void editItem(Book original) {}//Empty because the book should automatically update as it is now, 
    //but it will be needed with the database

    public void removeItem(Book book) {//removes a Book object from the ArrayList of Books
        books.remove(book);
    }
}
