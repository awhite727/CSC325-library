import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) throws Exception {
        BookAccess bookAccess = new BookAccess();
        bookAccess.addItem(new Book("17667676767","The Great Gatsby","F. Scott Fitzgerald","Tragedy",5));
        bookAccess.addItem(new Book("87888787872","A Wrinkle in Time","Madeleine L'Engle","Sci-fi",2));
        bookAccess.addItem(new Book("38779878887","Lord of the Flies","William Golding","Tragedy",3));
/*
        PersonAccess personAccess = new PersonAccess();
        personAccess.addItem(new People("1", "Jack", "Russel", "555-555-5555"));
        personAccess.addItem(new People("2", "Jill", "Russel", "111-111-1111"));
        personAccess.addItem(new People("3", "Abby", "Smith", "222-222-2222"));

        TransactionAccess transactionAccess = new TransactionAccess();
        transactionAccess.addItem(new Transaction("1", "2", "1/2/23"));//TO DO: Fix Transaction to not take this parameter and calculate it instead 
        transactionAccess.addItem(new Transaction("1", "1", "1/3/24"));//TO DO: Fix Transaction to not take this parameter and calculate it instead 
        transactionAccess.addItem(new Transaction("3", "2", "11/22/23"));//TO DO: Fix Transaction to not take this parameter and calculate it instead 
        */
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Graphics();
            }
        });
    }
}
