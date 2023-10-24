public class Book {

    private String ISBN;
    private String title;
    private String author;
    private String genre;
    private int totalCopies;
    private int availableCopies;
    //constructor method
    public Book(String ISBN, String title, String author, String genre, int copies) {
        this.ISBN = ISBN;
        this.title = title;
        this.author = author;
        this.genre = genre;
        totalCopies = copies;       //when initialized, book has an equal number of available and total copies
        availableCopies = copies;
    }
    //getters for each attribute
    public String getISBN() {
        return ISBN;
    }
    public String getTitle() {
        return title;
    }
    public String getAuthor() {
        return author;
    }
    public String getGenre() {
        return genre;
    }
    public int getTotalCopies() {
        return totalCopies;
    }
    public int availableCopies() {
        return availableCopies;
    }
    //setters for each attribute
    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public void setGenre(String genre) {
        this.genre = genre;
    }
    public void setTotalCopies(int totalCopies) {
        this.totalCopies = totalCopies;
    }
    public void setAvailableCopies(int availableCopies) {
        this.availableCopies = availableCopies;
    }

}