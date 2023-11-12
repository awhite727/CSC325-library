import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) throws Exception {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Graphics();
            }
        });
        System.out.println(BookAccess.getInstance().getBooks().size());
    }
}
