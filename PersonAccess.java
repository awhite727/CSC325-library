//MD: 11.9.23
import java.util.ArrayList;
public class PersonAccess {
    private ArrayList<People> people = new ArrayList<People>();
    //searchBy methods: gets a search element (i.e. firstName) as a parameter, and returns the corresponding object
    //Or ArrayList of objects; Returns null if not found    
    public People searchByLibraryNumber(int ID) { 
        int checkID;
        for (int i=0; i<people.size();i++){
            checkID = people.get(i).getLibraryNumber();
            if (ID == checkID){
                return people.get(i);
            }
        }
        return null; 
    }

    public ArrayList<People> searchByFirstName(String firstName) {
        ArrayList<People> allNames = new ArrayList<>();
        String checkName = "";
        for (int i=0; i<allNames.size();i++){
            checkName = people.get(i).getFirstName();
            if (firstName.equalsIgnoreCase(checkName)){
                allNames.add(people.get(i));
            }
        }
        if (allNames.isEmpty()){
            return null;
        }
        return allNames;
    }

    public ArrayList<People> searchByLastName(String lastName) {
        ArrayList<People> allNames = new ArrayList<>();
        String checkName = "";
        for (int i=0; i<allNames.size();i++){
            checkName = people.get(i).getLastName();
            if (lastName.equalsIgnoreCase(checkName)){
                allNames.add(people.get(i));
            }
        }
        if (allNames.isEmpty()){
            return null;
        }
        return allNames;
    }

    public ArrayList<People> searchByPhoneNumber(String phoneNumber) {
        ArrayList<People> allNumbers = new ArrayList<>();
        String checkNumber = "";
        for (int i=0; i<allNumbers.size();i++){
            checkNumber = people.get(i).getPhoneNumber();
            if (phoneNumber.equalsIgnoreCase(checkNumber)){
                allNumbers.add(people.get(i));
            }
        }
        if (allNumbers.isEmpty()){
            return null;
        }
        return allNumbers;
    }
    
    public void addItem(People peopleObj){//adds a People object to the ArrayList of People
        people.add(peopleObj);
    }

    public void editItem(People peopleObj) {}//Empty because the book should automatically update as it is now, 
    //but it will be needed with the database

    public void removeItem(People peopleObj) {//removes a People object from the ArrayList of People
        people.remove(peopleObj);
    }

}