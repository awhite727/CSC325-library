import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class Graphics extends JFrame {
    private JPanel cardPanel;
    private CardLayout cardLayout;
    BookAccess bookAccess = BookAccess.getInstance();
    PersonAccess personAccess = PersonAccess.getInstance();
    TransactionAccess transactionAccess = TransactionAccess.getInstance();
    EntityManager entityManager = new EntityManager();
    

    public Graphics() {
        try {
            // Set the look and feel to Metal
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
        } catch (UnsupportedLookAndFeelException | IllegalAccessException | InstantiationException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        // Set up the JFrame
        setTitle("Library Management System");
        setSize(900, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create a CardLayout for switching between screens
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        
        JPanel initialScreen = createPanel_Initial("Initial Screen", "Book Management", "Member Management", "Book Search", "Borrow/Return");
        cardPanel.add(initialScreen, "initial");

        JPanel confirm = createConfirmScreen();   //confirm screen
        cardPanel.add(confirm, "Confirm");
    // Screens 1 - 5 are associated with Book Management 
        JPanel screen1 = createPanel_1("Screen 1", "Add Book", "Update Book", "Remove Book", "Initial Screen");
        cardPanel.add(screen1, "Screen1");

        JPanel screen2 = createScreen2();   //adding book
        cardPanel.add(screen2, "Screen2");

        JPanel screen3 = createScreen3();   //updating book: Specifying ISBN
        cardPanel.add(screen3, "Screen3");

        //String ISBN = "";
        //JPanel screen4 = createScreen4(ISBN);   //updating book info
        //cardPanel.add(screen4, "Screen4");

        JPanel screen5 = createScreen5();   //removing book
        cardPanel.add(screen5, "Screen5");

    // Screens 6 - 10 are associated with Member Management
        JPanel screen6 = createPanel_6("Screen 6", "Add Member", "Update Member Info", "Delete Member","View Members", "Initial Screen");
        cardPanel.add(screen6, "Screen6");

        JPanel screen7 = createScreen7(); //adding member
        cardPanel.add(screen7, "Screen7");

        JPanel screen8 = createScreen8(); //update member: enter ID
        cardPanel.add(screen8, "Screen8");

        //JPanel screen9 = createScreen9(); // update member info
        //cardPanel.add(screen9, "Screen9");

        JPanel deleteUser = createDeleteUser(); //update member: enter ID
        cardPanel.add(deleteUser, "DeleteUser");

        JPanel screen10 = createScreen10();  // view book
        cardPanel.add(screen10, "Screen10");


    // Screen 11 displays the member info
        JPanel screen11 = createScreen11();  // view members
        cardPanel.add(screen11, "Screen11");


    // Screens 12 - 14 are associated with Borrowing and Returning books
        JPanel screen12 = createScreen12();
        cardPanel.add(screen12, "Screen12");
    
        // Add the card panel to the JFrame
        add(cardPanel);

        // Display the GUI
        setVisible(true);
    }

    private JPanel createPanel_Initial(String panelName, String... buttonTexts) { //start-up screen
        JPanel panel = new JPanel(); 
        panel.setLayout(new GridLayout(5,15,3,1));
        JLabel L0;

        L0 = new JLabel("<html><b>Thank you for choosing to use this Library Management System! Please choose the button corresponding to your needs.</b></html>",JLabel.CENTER);
        panel.add(L0);
        for (String buttonText : buttonTexts) {
            JButton button = new JButton(buttonText);
            button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (buttonText.equals("Book Management")){       
                        cardLayout.show(cardPanel, "Screen1"); 
                    }
                    if (buttonText.equals("Member Management")){       
                        cardLayout.show(cardPanel, "Screen6"); 
                    }
                    if (buttonText.equals("Book Search")){       
                        cardLayout.show(cardPanel, "Screen10"); 
                    }
                    if (buttonText.equals("Borrow/Return")){       
                        cardLayout.show(cardPanel, "Screen12"); 
                    }
                }
                
            });
            
            panel.add(button);
        }
    
        
        return panel;
    }
    private JPanel createConfirmScreen() { //this screen is called to tell the user the database is updated and recreates screens with textboxes
        JPanel panel = new JPanel();
        GroupLayout layout = new GroupLayout(panel);
        panel.setLayout(layout);
    
        JLabel L0 = new JLabel(" The Database has been updated!");
        
        JButton backButton = new JButton("Back to Initial");
    
        GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();
        vGroup.addComponent(L0)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER).addComponent(backButton));
                        
    
        GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup();
        hGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addComponent(L0)
                        .addComponent(backButton));
                
        layout.setVerticalGroup(vGroup);
        layout.setHorizontalGroup(hGroup);

        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //recreates all screens with textboxes so they return to null
                JPanel screen2 = createScreen2();  
                cardPanel.add(screen2, "Screen2");
                JPanel screen3 = createScreen3();   
                cardPanel.add(screen3, "Screen3");
                JPanel screen4 = createScreen4("123"); //"123" is just a random value
                cardPanel.add(screen4, "Screen4");
                JPanel screen5 = createScreen5();   
                cardPanel.add(screen5, "Screen5");
                JPanel screen7 = createScreen7(); 
                cardPanel.add(screen7, "Screen7");
                JPanel screen8 = createScreen8(); 
                cardPanel.add(screen8, "Screen8");
                JPanel screen9 = createScreen9("123"); //"123" is just a random value
                cardPanel.add(screen9, "Screen9");
                JPanel screen12 = createScreen12();
                cardPanel.add(screen12, "Screen12");

                cardLayout.show(cardPanel, "initial"); //takes user back to start-up screen
            }
        });
    
    
        return panel;
    }
    private JPanel createPanel_1(String panelName, String... buttonTexts) { //gives user options for book managemnt
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5,2,3,1));

        for (String buttonText : buttonTexts) {
            JButton button = new JButton(buttonText);
            button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (buttonText.equals("Add Book")){       
                        cardLayout.show(cardPanel, "Screen2"); 
                    }
                    if (buttonText.equals("Update Book")){       
                        cardLayout.show(cardPanel, "Screen3"); 
                    }
                    if (buttonText.equals("Remove Book")){       
                        cardLayout.show(cardPanel, "Screen5"); 
                    }
                    if (buttonText.equals("Initial Screen")){       
                        cardLayout.show(cardPanel, "initial"); 
                    }
                }
            });
            panel.add(button);
        }

        return panel;
    }



    private JPanel createScreen2() { //add book screen
        JPanel panel = new JPanel();
        GroupLayout layout = new GroupLayout(panel);
        panel.setLayout(layout);
    
        
        JLabel L0 = new JLabel(" Please fill in information regarding the book you wish to add:");
        L0.setBorder(new EmptyBorder(20, 0, 40, 0)); // Add vertical space around L0
        JLabel L1 = new JLabel("Title: ", JLabel.RIGHT);
        JLabel L2 = new JLabel("Author: ");
        JLabel L3 = new JLabel("ISBN: ");
        JLabel L4 = new JLabel("Genre: ");
        JLabel L5 = new JLabel("Quantity: ");
        L5.setBorder(new EmptyBorder(0, 0, 40, 0));
    
        JTextField textField1 = new JTextField(10);
        JTextField textField2 = new JTextField(10);
        JTextField textField3 = new JTextField(10);
        JTextField textField4 = new JTextField(10);
        JTextField textField5 = new JTextField(10);

        textField1.setMaximumSize(new Dimension(250, textField1.getPreferredSize().height));
        textField2.setMaximumSize(new Dimension(250, textField2.getPreferredSize().height));
        textField3.setMaximumSize(new Dimension(250, textField3.getPreferredSize().height));
        textField4.setMaximumSize(new Dimension(250, textField4.getPreferredSize().height));
        textField5.setMaximumSize(new Dimension(250, textField4.getPreferredSize().height));
    
        JButton backButton = new JButton("Back to Initial");
        JButton confirmButton = new JButton("Confirm");
    
        //layout.setAutoCreateGaps(true);
        //layout.setAutoCreateContainerGaps(true);
    
        GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();
        vGroup.addComponent(L0)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                        
                        .addComponent(L1)
                        .addComponent(textField1))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(L2)
                        .addComponent(textField2))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(L3)
                        .addComponent(textField3))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(L4)
                        .addComponent(textField4))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(L5)
                        .addComponent(textField5))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(backButton)
                        .addComponent(confirmButton));
    
        GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup();
        hGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                        .addComponent(L0)
                        .addComponent(L1)
                        .addComponent(L2)
                        .addComponent(L3)
                        .addComponent(L4)
                        .addComponent(L5)
                        .addComponent(backButton))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(textField1)
                        .addComponent(textField2)
                        .addComponent(textField3)
                        .addComponent(textField4)
                        .addComponent(textField5)
                        .addComponent(confirmButton));
    
        layout.setVerticalGroup(vGroup);
        layout.setHorizontalGroup(hGroup);

        
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Handle back button action here
                cardLayout.show(cardPanel, "initial");
            }
        });
    
        confirmButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String Title = textField1.getText();
                String Author = textField2.getText();
                String ISBN = textField3.getText();
                String Genre = textField4.getText();
                String Quantity = textField5.getText();

                // Handle confirm button action here
                String errorCheck = entityManager.createBook(Title, Author, ISBN, Genre, Quantity);
                if (errorCheck == null){ //if no error
                    JPanel screen10 = createScreen10();  // view book
                    cardPanel.add(screen10, "Screen10");
                    cardLayout.show(cardPanel, "Confirm");
                }
                else{
                    JOptionPane.showMessageDialog(panel, errorCheck, "Error", JOptionPane.ERROR_MESSAGE);
                    }
            }
        });
    
        return panel;
    }
    
    
    

    private JPanel createScreen3() { //screen for updating book part 1:Enter ISBN
        JPanel panel = new JPanel();
        GroupLayout layout = new GroupLayout(panel);
        panel.setLayout(layout);

        JLabel L0,L1;
        L0 = new JLabel("Please enter the ISBN of the book you would like to update.",JLabel.CENTER);
        L0.setBorder(new EmptyBorder(20, 0, 40, 0));
        L1 = new JLabel("ISBN: ");
        L1.setBorder(new EmptyBorder(0, 0, 40, 0));
        
        JTextField textField1= new JTextField(10);
        textField1.setMaximumSize(new Dimension(250, textField1.getPreferredSize().height));
        

        JButton backButton = new JButton("Back to Initial");
        JButton confirmButton = new JButton("Confirm");
        

        GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();
        vGroup.addComponent(L0)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                        
                        .addComponent(L1)
                        .addComponent(textField1))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(backButton)
                        .addComponent(confirmButton));
    
        GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup();
        hGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                        .addComponent(L0)
                        .addComponent(L1)
                        .addComponent(backButton))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(textField1)
                        .addComponent(confirmButton));
    
        layout.setVerticalGroup(vGroup);
        layout.setHorizontalGroup(hGroup);


        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "initial");
            }
        });

        confirmButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String ISBN = textField1.getText();

                if (bookAccess.searchByISBN(ISBN) == null){
                    JOptionPane.showMessageDialog(panel, "There is not book in database with that ISBN!", "Error", JOptionPane.ERROR_MESSAGE);                        
                }
                else if(ISBN == ""){
                    JOptionPane.showMessageDialog(panel, "Please input an ISBN", "Error", JOptionPane.ERROR_MESSAGE);   
                }
                else{
                    JPanel screen4 = createScreen4(ISBN);
                    cardPanel.add(screen4, "Screen4");
                    cardLayout.show(cardPanel, "Screen4");

                    }
            }
            
        });

        return panel;

    }

    private JPanel createScreen4(String ISBN) { //screen for updating book part 2: entering updated info
        

        JPanel panel = new JPanel();
        GroupLayout layout = new GroupLayout(panel);
        panel.setLayout(layout);

        JLabel L0,L1,L2,L3,L4;
        L0 = new JLabel("Type in the boxes whose category you would like to update.",JLabel.CENTER);
        L0.setBorder(new EmptyBorder(20, 0, 40, 0)); // Add vertical space around L0
        L1 = new JLabel("Title: ",JLabel.RIGHT);
        L2 = new JLabel("Author: ",JLabel.RIGHT);
        L3 = new JLabel("Genre: ",JLabel.RIGHT);
        L4 = new JLabel("Quantity: ",JLabel.RIGHT);
        L4.setBorder(new EmptyBorder(0, 0, 40, 0));
        // Add a text box (JTextField)
        JTextField textField1= new JTextField(10);
        JTextField textField2 = new JTextField(10);
        JTextField textField3 = new JTextField(10);
        JTextField textField4 = new JTextField(10);
    
        textField1.setMaximumSize(new Dimension(250, textField1.getPreferredSize().height));
        textField2.setMaximumSize(new Dimension(250, textField2.getPreferredSize().height));
        textField3.setMaximumSize(new Dimension(250, textField3.getPreferredSize().height));
        textField4.setMaximumSize(new Dimension(250, textField3.getPreferredSize().height));


        JButton backButton = new JButton("Back to Initial");
        JButton confirmButton = new JButton("Confirm");

        GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();
        vGroup.addComponent(L0)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                        
                        .addComponent(L1)
                        .addComponent(textField1))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(L2)
                        .addComponent(textField2))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(L3)
                        .addComponent(textField3))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(L4)
                        .addComponent(textField4))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(backButton)
                        .addComponent(confirmButton));
    
        GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup();
        hGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                        .addComponent(L0)
                        .addComponent(L1)
                        .addComponent(L2)
                        .addComponent(L3)
                        .addComponent(L4)
                        .addComponent(backButton))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(textField1)
                        .addComponent(textField2)
                        .addComponent(textField3)
                        .addComponent(textField4)
                        .addComponent(confirmButton));
    
        layout.setVerticalGroup(vGroup);
        layout.setHorizontalGroup(hGroup);

// allows for strings to be sent to required methods

        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "initial");
            }
        });

        confirmButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //cardLayout.show(cardPanel, "initial");
        

            // Update the book details based on user input
            
            if (textField1.getText().equals("")== false){
                (bookAccess.searchByISBN(ISBN)).setTitle(textField1.getText());
            }
            if (textField2.getText().equals("")== false){
                (bookAccess.searchByISBN(ISBN)).setAuthor(textField2.getText());
            }
            if (textField3.getText().equals("")== false){
                (bookAccess.searchByISBN(ISBN)).setGenre(textField3.getText()); 
            }
            if (textField4.getText().equals("")== false){                      
                (bookAccess.searchByISBN(ISBN)).setTotalCopies(Integer.parseInt(textField4.getText()));
            }
  
            JPanel screen10 = createScreen10();
            cardPanel.add(screen10, "Screen10");
            cardLayout.show(cardPanel, "Confirm");
            }
        });
    

        return panel;
    }


   private JPanel createScreen5() {
        JPanel panel = new JPanel();
        GroupLayout layout = new GroupLayout(panel);
        panel.setLayout(layout);

        JLabel L0,L1;
        L0 = new JLabel("Please enter the ISBN of the book you would like to remove.",JLabel.CENTER);
        L0.setBorder(new EmptyBorder(20, 0, 40, 0));
        L1 = new JLabel("ISBN: ");
        L1.setBorder(new EmptyBorder(0, 0, 40, 0));
        
        JTextField textField1= new JTextField(10);
        textField1.setMaximumSize(new Dimension(250, textField1.getPreferredSize().height));
        

        JButton backButton = new JButton("Back to Initial");
        JButton confirmButton = new JButton("Confirm");
        

        GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();
        vGroup.addComponent(L0)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                        
                        .addComponent(L1)
                        .addComponent(textField1))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(backButton)
                        .addComponent(confirmButton));
    
        GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup();
        hGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                        .addComponent(L0)
                        .addComponent(L1)
                        .addComponent(backButton))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(textField1)
                        .addComponent(confirmButton));
    
        layout.setVerticalGroup(vGroup);
        layout.setHorizontalGroup(hGroup);


        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "initial");
            }
        });

        confirmButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String ISBN2 = textField1.getText();
                
                if (bookAccess.searchByISBN(ISBN2) == null){
                    JOptionPane.showMessageDialog(panel, "There is not book in database with that ISBN!", "Error", JOptionPane.ERROR_MESSAGE);                        
                    }
                else{
                    bookAccess.removeItem(bookAccess.searchByISBN(ISBN2));
                    JPanel screen10 = createScreen10();
                    cardPanel.add(screen10, "Screen10");
                    
                    cardLayout.show(cardPanel, "Confirm");

                    }
            }
        });

        return panel;

    }
   


    private JPanel createPanel_6(String panelName, String... buttonTexts) {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5,2,3,1));

        for (String buttonText : buttonTexts) {
            JButton button = new JButton(buttonText);
            button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (buttonText.equals("Add Member")){       
                        cardLayout.show(cardPanel, "Screen7"); 
                    }
                    if (buttonText.equals("Update Member Info")){       
                        cardLayout.show(cardPanel, "Screen8"); 
                    }
                    if (buttonText.equals("Initial Screen")){       
                        cardLayout.show(cardPanel, "initial"); 
                    }
                    if (buttonText.equals("View Members")){       
                        cardLayout.show(cardPanel, "Screen11"); 
                    }
                    if (buttonText.equals("Delete Member")){       
                        cardLayout.show(cardPanel, "DeleteUser"); 
                    }
                }
            });
            panel.add(button);
        }

        return panel;
    }

    private JPanel createScreen7() {
        JPanel panel = new JPanel();
        GroupLayout layout = new GroupLayout(panel);
        panel.setLayout(layout);
    
        
        JLabel L0 = new JLabel(" Please fill in information regarding the new Library Member:");
        L0.setBorder(new EmptyBorder(20, 0, 40, 0)); // Add vertical space around L0
        JLabel L1 = new JLabel("Member ID: ", JLabel.RIGHT);
        JLabel L2 = new JLabel("First Name: ");
        JLabel L3 = new JLabel("Last Name: ");
        JLabel L4 = new JLabel("Phone Number: ");
        L4.setBorder(new EmptyBorder(0, 0, 40, 0));
    
        JTextField textField1 = new JTextField(10);
        JTextField textField2 = new JTextField(10);
        JTextField textField3 = new JTextField(10);
        JTextField textField4 = new JTextField(10);

        textField1.setMaximumSize(new Dimension(250, textField1.getPreferredSize().height));
        textField2.setMaximumSize(new Dimension(250, textField2.getPreferredSize().height));
        textField3.setMaximumSize(new Dimension(250, textField1.getPreferredSize().height));
        textField4.setMaximumSize(new Dimension(250, textField2.getPreferredSize().height));
    
        JButton backButton = new JButton("Back to Initial");
        JButton confirmButton = new JButton("Confirm");
    
        GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();
        vGroup.addComponent(L0)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                        
                        .addComponent(L1)
                        .addComponent(textField1))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(L2)
                        .addComponent(textField2))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(L3)
                        .addComponent(textField3))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(L4)
                        .addComponent(textField4))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(backButton)
                        .addComponent(confirmButton));

        GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup();
        hGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                        .addComponent(L0)
                        .addComponent(L1)
                        .addComponent(L2)
                        .addComponent(L3)
                        .addComponent(L4)
                        .addComponent(backButton))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(textField1)
                        .addComponent(textField2)
                        .addComponent(textField3)
                        .addComponent(textField4)
                        .addComponent(confirmButton));
    
        layout.setVerticalGroup(vGroup);
        layout.setHorizontalGroup(hGroup);

        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Handle back button action here
                cardLayout.show(cardPanel, "initial");
            }
        });
    
        confirmButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String ID = textField1.getText();
                String firstName = textField2.getText();
                String lastName = textField3.getText();
                String phoneNumber = textField4.getText();
                String errorCheck = entityManager.createPeople(ID, firstName, lastName, phoneNumber);
                if (errorCheck==null){
                JPanel screen11 = createScreen11();  
                cardPanel.add(screen11, "Screen11");
                cardLayout.show(cardPanel, "Confirm");
                }
                else{
                    JOptionPane.showMessageDialog(panel, errorCheck, "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    
        return panel;
    }

       private JPanel createScreen8() {
        JPanel panel = new JPanel();
        GroupLayout layout = new GroupLayout(panel);
        panel.setLayout(layout);

        JLabel L0,L1;
        L0 = new JLabel("Please enter the ID of the member you would like to update.",JLabel.CENTER);
        L0.setBorder(new EmptyBorder(20, 0, 40, 0)); // Add vertical space around L0
        L1 = new JLabel("Member ID: ",JLabel.RIGHT);
        L1.setBorder(new EmptyBorder(0, 0, 40, 0));
        
        JTextField textField1= new JTextField(10);
        textField1.setMaximumSize(new Dimension(250, textField1.getPreferredSize().height));
        

        JButton backButton = new JButton("Back to Initial");
        JButton confirmButton = new JButton("Confirm");
        

        GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();
        vGroup.addComponent(L0)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                        
                        .addComponent(L1)
                        .addComponent(textField1))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(backButton)
                        .addComponent(confirmButton));
    
        GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup();
        hGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                        .addComponent(L0)
                        .addComponent(L1)
                        .addComponent(backButton))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(textField1)
                        .addComponent(confirmButton));
    
        layout.setVerticalGroup(vGroup);
        layout.setHorizontalGroup(hGroup);


        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "initial");
            }
        });

        confirmButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String ID = textField1.getText();
                if(personAccess.searchByLibraryNumber(ID) == null){
                    JOptionPane.showMessageDialog(panel, "There is no member in database with that ID!", "Error", JOptionPane.ERROR_MESSAGE);
                }
                else{
                    JPanel screen9 = createScreen9(ID);
                    cardPanel.add(screen9, "Screen9");
                    cardLayout.show(cardPanel, "Screen9");
                }
            }
        });

        return panel;

    }


    private JPanel createScreen9(String ID) {
        JPanel panel = new JPanel();
        GroupLayout layout = new GroupLayout(panel);
        panel.setLayout(layout);
    
        
        JLabel L0 = new JLabel("Type in the boxes whose category you would like to update: ");
        L0.setBorder(new EmptyBorder(20, 0, 40, 0)); // Add vertical space around L0
        JLabel L1 = new JLabel("First Name: ");
        JLabel L2 = new JLabel("Last Name: ", JLabel.RIGHT);
        JLabel L3 = new JLabel("Phone Number: ");
        //L3.setBorder(new EmptyBorder(0, 0, 40, 0));
    
        JTextField textField1 = new JTextField(10);
        JTextField textField2 = new JTextField(10);
        JTextField textField3 = new JTextField(10);


        textField1.setMaximumSize(new Dimension(250, textField1.getPreferredSize().height));
        textField2.setMaximumSize(new Dimension(250, textField2.getPreferredSize().height));
        textField3.setMaximumSize(new Dimension(250, textField2.getPreferredSize().height));
    
        JButton backButton = new JButton("Back to Initial");
        JButton confirmButton = new JButton("Confirm");
    
        //layout.setAutoCreateGaps(true);
        //layout.setAutoCreateContainerGaps(true);
    
        GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();
        vGroup.addComponent(L0)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addComponent(L1)
                        .addComponent(textField1))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(L2)
                        .addComponent(textField2))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addComponent(L3)
                        .addComponent(textField3))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(backButton)
                        .addComponent(confirmButton));

        GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup();
        hGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                        .addComponent(L0)
                        .addComponent(L1)
                        .addComponent(L2)
                        .addComponent(L3)
                        .addComponent(backButton))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(textField1)
                        .addComponent(textField2)
                        .addComponent(textField3)
                        .addComponent(confirmButton));
    
        layout.setVerticalGroup(vGroup);
        layout.setHorizontalGroup(hGroup);

        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Handle back button action here
                cardLayout.show(cardPanel, "initial");
            }
        });
    
        confirmButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Handle confirm button action here
                if (textField1.getText().equals("")== false){
                    (personAccess.searchByLibraryNumber(ID)).setFirstName(textField1.getText());
                }
                if (textField2.getText().equals("")== false){
                    (personAccess.searchByLibraryNumber(ID)).setLastName(textField2.getText());
                }
                if (textField3.getText().equals("")== false){
                    (personAccess.searchByLibraryNumber(ID)).setPhoneNumber(textField3.getText()); 
                }
              
    
                JPanel screen11 = createScreen11();
                cardPanel.add(screen11, "Screen11");
                cardLayout.show(cardPanel, "Confirm");
            }
        });
    
        return panel;
    }
    private JPanel createDeleteUser() {
        JPanel panel = new JPanel();
        GroupLayout layout = new GroupLayout(panel);
        panel.setLayout(layout);

        JLabel L0,L1;
        L0 = new JLabel("Please enter the ID of the member you would like to remove.",JLabel.CENTER);
        L0.setBorder(new EmptyBorder(20, 0, 40, 0));
        L1 = new JLabel("Member ID: ");
        L1.setBorder(new EmptyBorder(0, 0, 40, 0));
        
        JTextField textField1= new JTextField(10);
        textField1.setMaximumSize(new Dimension(250, textField1.getPreferredSize().height));
        

        JButton backButton = new JButton("Back to Initial");
        JButton confirmButton = new JButton("Confirm");
        

        GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();
        vGroup.addComponent(L0)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                        
                        .addComponent(L1)
                        .addComponent(textField1))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(backButton)
                        .addComponent(confirmButton));
    
        GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup();
        hGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                        .addComponent(L0)
                        .addComponent(L1)
                        .addComponent(backButton))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(textField1)
                        .addComponent(confirmButton));
    
        layout.setVerticalGroup(vGroup);
        layout.setHorizontalGroup(hGroup);


        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "initial");
            }
        });

        confirmButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String ID = textField1.getText();
                if(personAccess.searchByLibraryNumber(ID) == null){
                    JOptionPane.showMessageDialog(panel, "There is no member in database with that ID!", "Error", JOptionPane.ERROR_MESSAGE);
                }
                else{
                    personAccess.removeItem(personAccess.searchByLibraryNumber(ID));
                    JPanel screen11 = createScreen11();
                    cardPanel.add(screen11, "Screen11");
                    
                    cardLayout.show(cardPanel, "Confirm");
                }
            }
        });

        return panel;

    }
    
    private JPanel createScreen10() {
        JPanel panel = new JPanel();
        List<Book> copyOfBooks = bookAccess.getBooks();
        Object[][] data = new Object[copyOfBooks.size()][];
        for (int i = 0; i < copyOfBooks.size(); i++) {
            Book book = copyOfBooks.get(i);
            data[i] = new Object[]{book.getISBN(), book.getTitle(), book.getAuthor(), book.getGenre(), book.getAvailableCopies()};
        }
    
        // String array for column names
        String[] columns = new String[]{"ISBN", "Title", "Author", "Genre", "Total Copies"};
    
        // Class array for column classes
        final Class<?>[] columnClass = new Class[]{String.class, String.class, String.class, String.class, String.class};
    
        // Create table model with data
        DefaultTableModel model = new DefaultTableModel(data, columns) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
    
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return columnClass[columnIndex];
            }
        };
    
        // Create JTable with the model
        JTable table = new JTable(model);
        table.setShowGrid(true);
        table.setShowHorizontalLines(true);
        table.setShowVerticalLines(true);
    
        // Set up the panel layout
        panel.setLayout(new BorderLayout());
    
        // Adds a search bar
        JTextField searchField = new JTextField(20); // changes width of search bar
        JButton searchButton = new JButton("Search"); // search button
        JPanel searchPanel = new JPanel(new FlowLayout());
        searchPanel.add(new JLabel("Search: "));
        searchPanel.add(searchField);
        searchPanel.add(searchButton);
    
        // Adds a listener for the search button
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String searchText = searchField.getText().toLowerCase().strip();
                filterTableData(model, copyOfBooks, searchText);
            }
        });
    
        panel.add(searchPanel, BorderLayout.NORTH);
    
        // Add the table to the panel with a JScrollPane
        panel.add(new JScrollPane(table), BorderLayout.CENTER);
    
        JButton backButton = new JButton("Back to Initial");
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "initial");
            }
        });
    
        panel.add(backButton, BorderLayout.SOUTH);
    
        return panel;
    }
    
    private void filterTableData(DefaultTableModel model, List<Book> copyOfBooks, String searchText) {
        model.setRowCount(0); // clears the existing rows
    
        for (Book book : copyOfBooks) {
            if (bookMatchesSearch(book, searchText)) {
                model.addRow(new Object[]{book.getISBN(), book.getTitle(), book.getAuthor(), book.getGenre(), book.getTotalCopies()});
            }
        }
    }
    
    private boolean bookMatchesSearch(Book book, String searchText) {
        return book.getISBN().toLowerCase().contains(searchText) ||
               book.getTitle().toLowerCase().contains(searchText) ||
               book.getAuthor().toLowerCase().contains(searchText) ||
               book.getGenre().toLowerCase().contains(searchText);
    }


   private JPanel createScreen11() {
        JPanel panel = new JPanel();
        List<People> peoples = personAccess.getPeople();
        Object[][] data = new Object[peoples.size()][];
        for (int i = 0; i < peoples.size(); i++) {
            People people = peoples.get(i);
            data[i] = new Object[]{people.getLibraryNumber(), people.getFirstName(), people.getLastName(), people.getPhoneNumber(), entityManager.formatFees(people.getFeesDue())};
        }
    
        // String array for column names
        String[] columns = new String[]{"Library ID","First Name","Last Name","Phone Number","Fees Due"};
    
        // Class array for column classes
        final Class<?>[] columnClass = new Class[]{int.class, String.class, String.class,String.class, String.class};

        // Create table model with data
        DefaultTableModel model = new DefaultTableModel(data, columns) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
    
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return columnClass[columnIndex];
            }
        };
    
        // Create JTable with the model
        JTable table = new JTable(model);
        table.setShowGrid(true);
        table.setShowHorizontalLines(true);
        table.setShowVerticalLines(true);
    
        // Set up the panel layout
        panel.setLayout(new BorderLayout());
    
        // Adds a search bar
        JTextField searchField = new JTextField(20); // changes width of search bar
        JButton searchButton = new JButton("Search"); // search button
        JPanel searchPanel = new JPanel(new FlowLayout());
        searchPanel.add(new JLabel("Search: "));
        searchPanel.add(searchField);
        searchPanel.add(searchButton);
    
        // Adds a listener for the search button
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String searchText = searchField.getText().toLowerCase().strip();
                filterTableData_p(model, peoples, searchText);
            }
        });
    
        panel.add(searchPanel, BorderLayout.NORTH);
    
        // Add the table to the panel with a JScrollPane
        panel.add(new JScrollPane(table), BorderLayout.CENTER);
    
        JButton backButton = new JButton("Back to Initial");
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "initial");
            }
        });
    
        panel.add(backButton, BorderLayout.SOUTH);
    
        return panel;
    }
    
    private void filterTableData_p(DefaultTableModel model, List<People> peoples, String searchText) {
        model.setRowCount(0); // clears the existing rows
    
        for (People people : peoples) {
            if (peopleMatchesSearch(people, searchText)) {
                model.addRow(new Object[]{people.getLibraryNumber(), people.getFirstName(), people.getLastName(), people.getPhoneNumber(), people.getFeesDue()});
            }
        }
    }
    
    private boolean peopleMatchesSearch(People people, String searchText) {
        return Integer.toString(people.getLibraryNumber()).toLowerCase().contains(searchText) ||
               people.getFirstName().toLowerCase().contains(searchText) ||
               people.getLastName().toLowerCase().contains(searchText) ||
               people.getPhoneNumber().toLowerCase().contains(searchText) ||
               entityManager.formatFees(people.getFeesDue()).toLowerCase().contains(searchText);
    }


    
    private JPanel createScreen12() {
        JPanel panel = new JPanel();
        GroupLayout layout = new GroupLayout(panel);
        panel.setLayout(layout);
    
        
        JLabel L0 = new JLabel("Please enter the Member ID and ISBN of the book being borrowed or returned.");
        L0.setBorder(new EmptyBorder(20, 0, 40, 0)); // Add vertical space around L0
        JLabel L1 = new JLabel("Member ID: ", JLabel.RIGHT);
        JLabel L2 = new JLabel("ISBN: ");
        L2.setBorder(new EmptyBorder(0, 0, 40, 0));
    
        JTextField textField1 = new JTextField(10);
        JTextField textField2 = new JTextField(10);


        textField1.setMaximumSize(new Dimension(250, textField1.getPreferredSize().height));
        textField2.setMaximumSize(new Dimension(250, textField2.getPreferredSize().height));
    
        JButton backButton = new JButton("Back to Initial");
        JButton confirmButton1 = new JButton("Confirm (Returning)");
        JButton confirmButton2 = new JButton("Confirm (Borrowing)");
    
    
        GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();
        vGroup.addComponent(L0)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addComponent(L1)
                        .addComponent(textField1))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(L2)
                        .addComponent(textField2))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(backButton)
                        .addComponent(confirmButton1)
                        .addComponent(confirmButton2));


        GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup();
        hGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                        .addComponent(L0)
                        .addComponent(L1)
                        .addComponent(L2)
                        .addComponent(backButton))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(textField1)
                        .addComponent(textField2)
                        .addComponent(confirmButton1))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(confirmButton2));
    
        layout.setVerticalGroup(vGroup);
        layout.setHorizontalGroup(hGroup);

        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Handle back button action here
                cardLayout.show(cardPanel, "initial");
            }
        });
    
        confirmButton1.addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent e) {
                String memberID = textField1.getText();
                String isbn = textField2.getText();
                String errorCheck = entityManager.returnBook(memberID, isbn);
                if (Character.toString(errorCheck.charAt(0)).equals("$")){ //if starts with "$"
                    JPanel screen13 = createScreen13(errorCheck); //returning book confirmation
                    cardPanel.add(screen13, "Screen13");
                    cardLayout.show(cardPanel, "Screen13");
                } else{
                    JOptionPane.showMessageDialog(panel, errorCheck, "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        confirmButton2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String memberID = textField1.getText();
                String isbn = textField2.getText();
                String errorCheck = entityManager.checkOut(isbn,memberID);
                if (errorCheck == null){
                    String dueDate = entityManager.formatDate(transactionAccess.mostRecentTransaction().getDueDate());
                    JPanel screen14 = createScreen14(dueDate);  //book checked out confirmation
                    cardPanel.add(screen14, "Screen14");
                    cardLayout.show(cardPanel, "Screen14");
                } else{
                    JOptionPane.showMessageDialog(panel, errorCheck, "Error", JOptionPane.ERROR_MESSAGE);
                }
                
            }
        });
    
        return panel;
    }



    private JPanel createScreen13(String fees) {
        JPanel panel = new JPanel();
        GroupLayout layout = new GroupLayout(panel);
        panel.setLayout(layout);
    
        JLabel L0, L1, L2;
        L0 = new JLabel("                           Book Returned!                           ", JLabel.CENTER);
        L0.setBorder(new EmptyBorder(10, 0, 10, 0));
        L1 = new JLabel("Fees: ", JLabel.CENTER);
        L1.setBorder(new EmptyBorder(10, 0, 40, 0));
        L2 = new JLabel(fees, JLabel.CENTER);
        L2.setBorder(new EmptyBorder(10, 0, 10, 0));
        JButton backButton = new JButton("Return to Initial Screen");
    
        GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();
        vGroup.addComponent(L0)
        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                .addComponent(L1)
                .addComponent(L2))
                .addComponent(backButton);
    
        GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup();
        hGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addComponent(L0)
                        .addComponent(L1)
                        .addComponent(L2)
                        .addComponent(backButton));
               
                //.addComponent(backButton);
    
        layout.setVerticalGroup(vGroup);
        layout.setHorizontalGroup(hGroup);
    
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JPanel screen10 = createScreen10();
                cardPanel.add(screen10, "Screen10");
                cardLayout.show(cardPanel, "initial");
            }
        });
    
        return panel;
    }

    private JPanel createScreen14(String dueDate) {
        JPanel panel = new JPanel();
        GroupLayout layout = new GroupLayout(panel);
        panel.setLayout(layout);
    
        JLabel L0, L1, L2;
        L0 = new JLabel("                       Book Has Been Checked Out!                       ", JLabel.CENTER);
        L0.setBorder(new EmptyBorder(10, 0, 10, 0));
        L1 = new JLabel("Due date: ", JLabel.CENTER);
        L1.setBorder(new EmptyBorder(10, 0, 40, 0));
        L2 = new JLabel(dueDate, JLabel.CENTER);
        L2.setBorder(new EmptyBorder(10, 0, 10, 0));
        JButton backButton = new JButton("Return to Initial Screen");
    
        GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();
        vGroup.addComponent(L0)
        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                .addComponent(L1)
                .addComponent(L2))
                .addComponent(backButton);
    
        GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup();
        hGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addComponent(L0)
                        .addComponent(L1)
                        .addComponent(L2)
                        .addComponent(backButton));
               
                //.addComponent(backButton);
    
        layout.setVerticalGroup(vGroup);
        layout.setHorizontalGroup(hGroup);
    
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JPanel screen10 = createScreen10();
                cardPanel.add(screen10, "Screen10");
                cardLayout.show(cardPanel, "initial");

            }
        });
    
        return panel;
    }
}
