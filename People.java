public class People {

    private int libraryNumber;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private double feesDue;
    //constructor
    public People(int libraryNumber, String firstName, String lastName, String phoneNumber) {
        this.libraryNumber = libraryNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        feesDue = 0;     //all new members start with a fee of 0
    }
    //getters for the attributes
    public int getLibraryNumber() {
        return libraryNumber;
    }
    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public double getFeesDue() {
        return feesDue;
    }
    //setters for the attributes
    public void setLibraryNumber(int libraryNumber) {
        this.libraryNumber = libraryNumber;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public void updateFeesDue(double feesDue) {
        this.feesDue += feesDue;
    }

} 