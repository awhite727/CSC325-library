//MD: 11.9.23
import java.util.List;
import java.util.ArrayList;
public class BookAccess {
    //searchBy methods: gets a search element (i.e. isbn) as a parameter, and returns the corresponding object
    //Or ArrayList of objects; Returns null if not found
    private List<Book> books = new ArrayList<Book>();

    public List<Book> getBooks(){
        return books;
    }

    public Book searchByISBN(String ISBN) {
        String checkISBN = "";
        for (int i=0; i<books.size();i++){
            checkISBN = books.get(i).getISBN();
            if (ISBN.equalsIgnoreCase(checkISBN)){
                return books.get(i);
            }
        }
        return null;
    }

    public List<Book> searchByAuthor(String author) {
        List<Book> allByAuthor = new ArrayList<>();
        String checkAuthor = "";
        for (int i=0; i<books.size();i++){
            checkAuthor = books.get(i).getAuthor();
            if (author.equalsIgnoreCase(checkAuthor)){
                allByAuthor.add(books.get(i));
            }
        }
        if (allByAuthor.isEmpty()){
            return null;
        }
        return allByAuthor;
    }

    public List<Book> searchByTitle(String title) {
        ArrayList<Book> allOfTitle = new ArrayList<>();
        String checkTitle = "";
        for (int i=0; i<books.size();i++){
            checkTitle = books.get(i).getTitle();
            if (title.equalsIgnoreCase(checkTitle)){
                allOfTitle.add(books.get(i));
            }
        }
        if (allOfTitle.isEmpty()){
            return null;
        }
        return allOfTitle;
    }

    public List<Book> searchByGenre(String genre) {
        List<Book> allOfGenre = new ArrayList<>();
        String checkGenre = "";
        for (int i=0; i<books.size();i++){
            checkGenre = books.get(i).getGenre();
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
