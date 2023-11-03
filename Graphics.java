import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Graphics extends JFrame {
    private JPanel cardPanel;
    private CardLayout cardLayout;
    

    public Graphics() {
        // Set up the JFrame
        setTitle("Multi-Screen Navigation GUI");
        setSize(1250, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create a CardLayout for switching between screens
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        
        JPanel initialScreen = createPanel_Initial("Initial Screen", "Book Management", "Member Management", "Book Search", "Borrow/Return");
        cardPanel.add(initialScreen, "initial");

    // Screens 1 - 5 are associated with Book Management 
        JPanel screen1 = createPanel_1("Screen 1", "Add Book", "Update Book", "Remove Book", "Inital Screen");
        cardPanel.add(screen1, "Screen1");

        JPanel screen2 = createScreen2();   //adding book
        cardPanel.add(screen2, "Screen2");

        JPanel screen3 = createScreen3();   //updating book: Specifying ISBN
        cardPanel.add(screen3, "Screen3");

        JPanel screen4 = createScreen4();   //updating book info
        cardPanel.add(screen4, "Screen4");

        JPanel screen5 = createScreen5();   //removing book
        cardPanel.add(screen5, "Screen5");

    // Screen __ - __ are associated with Member Management
        JPanel screen6 = createPanel_6("Screen 6", "Add Member", "Update Member Info", "View Members", "Inital Screen");
        cardPanel.add(screen6, "Screen6");

        JPanel screen7 = createScreen7(); //adding member
        cardPanel.add(screen7, "Screen7");

        JPanel screen8 = createScreen8(); //update member: enter ID
        cardPanel.add(screen8, "Screen8");

        JPanel screen9 = createScreen9(); // update member info
        cardPanel.add(screen9, "Screen9");

        //JPanel screen10 = createScreen10();  // view members
        //cardPanel.add(screen10, "Screen10");



        // Add the card panel to the JFrame
        add(cardPanel);

        // Display the GUI
        setVisible(true);
    }

    private JPanel createPanel_Initial(String panelName, String... buttonTexts) {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4,1,3,1));

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
                }
            });
            panel.add(button);
        }

        return panel;
    }

    private JPanel createPanel_1(String panelName, String... buttonTexts) {
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
                }
            });
            panel.add(button);
        }

        return panel;
    }


    private JPanel createScreen2() {
        

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6,2,3,1));

        JLabel L00,L0,L1,L2,L3,L4;
        L00 = new JLabel("  ");
        L0 = new JLabel("Please fill in information regardign the book you wish to add.",JLabel.CENTER);
        L1 = new JLabel("Title: ",JLabel.RIGHT);
        L2 = new JLabel("Author: ",JLabel.RIGHT);
        L3 = new JLabel("ISBN: ",JLabel.RIGHT);
        L4 = new JLabel("Quantity: ",JLabel.RIGHT);
        // Add a text box (JTextField)
        JTextField textField1= new JTextField(10);
        JTextField textField2 = new JTextField(10);
        JTextField textField3 = new JTextField(10);
        JTextField textField4 = new JTextField(10);

        L1.setLabelFor(textField1);
        L2.setLabelFor(textField2);
        L3.setLabelFor(textField3);
        L4.setLabelFor(textField4);

        JButton backButton = new JButton("Back to Initial");
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "initial");
            }
        });

        JButton confirmButton = new JButton("Confirm");
        confirmButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "initial");
            }
        });
        panel.add(L00);
        panel.add(L0);
        panel.add(L1);
        panel.add(textField1);
        panel.add(L2);
        panel.add(textField2);
        panel.add(L3);
        panel.add(textField3);
        panel.add(L4);
        panel.add(textField4);
        panel.add(backButton);
        panel.add(confirmButton);
        setVisible(true);
        return panel;
    }

    private JPanel createScreen3() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3,2,3,1));

        JLabel L00,L0,L1;
        L00 = new JLabel("  ");
        L0 = new JLabel("Please enter the ISBN of the book you would like to update.",JLabel.CENTER);
        L1 = new JLabel("ISBN",JLabel.RIGHT);
        // Add a text box (JTextField)
        JTextField textField1= new JTextField(10);


        L1.setLabelFor(textField1);
 

        JButton backButton = new JButton("Back to Initial");
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "initial");
            }
        });

        JButton confirmButton = new JButton("Confirm");
        confirmButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "Screen4");
            }
        });
        panel.add(L00);
        panel.add(L0);
        panel.add(L1);
        panel.add(textField1);
        panel.add(backButton);
        panel.add(confirmButton);
        setVisible(true);
        return panel;

    }

    private JPanel createScreen4() {
        

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6,2,3,1));

        JLabel L00,L0,L1,L2,L3;
        L00 = new JLabel("  ");
        L0 = new JLabel("Type in the boxes whose category you would like to update. Leave other boxes blank.",JLabel.CENTER);
        L1 = new JLabel("Title: ",JLabel.RIGHT);
        L2 = new JLabel("Author: ",JLabel.RIGHT);
        L3 = new JLabel("Quantity: ",JLabel.RIGHT);
        // Add a text box (JTextField)
        JTextField textField1= new JTextField(10);
        JTextField textField2 = new JTextField(10);
        JTextField textField3 = new JTextField(10);
    

        L1.setLabelFor(textField1);
        L2.setLabelFor(textField2);
        L3.setLabelFor(textField3);
  

        JButton backButton = new JButton("Back to Initial");
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "initial");
            }
        });

        JButton confirmButton = new JButton("Confirm");
        confirmButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "initial");
            }
        });
        panel.add(L00);
        panel.add(L0);
        panel.add(L1);
        panel.add(textField1);
        panel.add(L2);
        panel.add(textField2);
        panel.add(L3);
        panel.add(textField3);
        panel.add(backButton);
        panel.add(confirmButton);
        setVisible(true);
        return panel;
    }



    private JPanel createScreen5() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3,2,3,1));

        JLabel L00,L0,L1;
        L00 = new JLabel("  ");
        L0 = new JLabel("Please enter the ISBN of the book you would like to remove.",JLabel.CENTER);
        L1 = new JLabel("ISBN",JLabel.RIGHT);
        // Add a text box (JTextField)
        JTextField textField1= new JTextField(10);


        L1.setLabelFor(textField1);
 

        JButton backButton = new JButton("Back to Initial");
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "initial");
            }
        });

        JButton confirmButton = new JButton("Confirm");
        confirmButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "initial");
            }
        });
        panel.add(L00);
        panel.add(L0);
        panel.add(L1);
        panel.add(textField1);
        panel.add(backButton);
        panel.add(confirmButton);
        setVisible(true);
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
                    //if (buttonText.equals("View Members")){       
                     //   cardLayout.show(cardPanel, "Screen10"); 
                    //}
                }
            });
            panel.add(button);
        }

        return panel;
    }


    private JPanel createScreen7() {
        
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6,2,3,1));

        JLabel L00,L0,L1,L2;
        L00 = new JLabel("  ");
        L0 = new JLabel("Please fill in information regarding the new Library Member.",JLabel.CENTER);
        L1 = new JLabel("Name: ",JLabel.RIGHT);
        L2 = new JLabel("Phone Number: ",JLabel.RIGHT);

        // Add a text box (JTextField)
        JTextField textField1= new JTextField(10);
        JTextField textField2 = new JTextField(10);

        L1.setLabelFor(textField1);
        L2.setLabelFor(textField2);

        JButton backButton = new JButton("Back to Initial");
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "initial");
            }
        });

        JButton confirmButton = new JButton("Confirm");
        confirmButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "initial");
            }
        });
        panel.add(L00);
        panel.add(L0);
        panel.add(L1);
        panel.add(textField1);
        panel.add(L2);
        panel.add(textField2);
        panel.add(backButton);
        panel.add(confirmButton);
        setVisible(true);
        return panel;
    }

    private JPanel createScreen8() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3,2,3,1));

        JLabel L00,L0,L1;
        L00 = new JLabel("  ");
        L0 = new JLabel("Please enter the ID of the member you would like to update.",JLabel.CENTER);
        L1 = new JLabel("ISBN",JLabel.RIGHT);
        // Add a text box (JTextField)
        JTextField textField1= new JTextField(10);


        L1.setLabelFor(textField1);
 

        JButton backButton = new JButton("Back to Initial");
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "initial");
            }
        });

        JButton confirmButton = new JButton("Confirm");
        confirmButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "Screen9");
            }
        });
        panel.add(L00);
        panel.add(L0);
        panel.add(L1);
        panel.add(textField1);
        panel.add(backButton);
        panel.add(confirmButton);
        setVisible(true);
        return panel;

    }

    private JPanel createScreen9() {
        

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6,2,3,1));

        JLabel L00,L0,L1,L2;
        L00 = new JLabel("  ");
        L0 = new JLabel("Type in the boxes whose category you would like to update. Leave other boxes blank.",JLabel.CENTER);
        L1 = new JLabel("Name: ",JLabel.RIGHT);
        L2 = new JLabel("Phone: ",JLabel.RIGHT);

        // Add a text box (JTextField)
        JTextField textField1= new JTextField(10);
        JTextField textField2 = new JTextField(10);
  
        L1.setLabelFor(textField1);
        L2.setLabelFor(textField2);

        JButton backButton = new JButton("Back to Initial");
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "initial");
            }
        });

        JButton confirmButton = new JButton("Confirm");
        confirmButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "initial");
            }
        });
        panel.add(L00);
        panel.add(L0);
        panel.add(L1);
        panel.add(textField1);
        panel.add(L2);
        panel.add(textField2);
        panel.add(backButton);
        panel.add(confirmButton);
        setVisible(true);
        return panel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Graphics();
            }
        });
    }
}