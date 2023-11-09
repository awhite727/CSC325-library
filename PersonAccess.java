//MD: 11.9.23
import java.util.ArrayList;
public class PersonAccess {
    //searchBy methods: gets a search element (i.e. isbn) as a parameter, searches corresponding column, and creates a BookDetails object of all the info
    private ArrayList<People> people = new ArrayList<People>();
    //TO DO ALL: Make it take parameter
    

//CHECK: When he menat hard code data did he mean actually just use book class to grab data and then just return the stuff or like is this it or osmething headache brain go brrrr ugh



    public People searchByID(int ID) {//NOTE: Not efficient 
        int checkID;
        for (int i=0; i<people.size();i++){
            checkID = people.get(i).getLibraryNumber();
            if (ID == checkID){
                return people.get(i);
            }
        }
        return null; //couldn't find the object
    }

    public ArrayList<People> searchByFirstName(String firstName) {//NOTE: Not efficient
        ArrayList<People> allNames = new ArrayList<>();
        String checkName = "";
        for (int i=0; i<allNames.size();i++){
            checkName = people.get(i).getFirstName();
            if (firstName.equalsIgnoreCase(checkName)){
                allNames.add(people.get(i));
            }
        }
        if (allNames.isEmpty()){
            return null;//couldn't find the object
        }
        return allNames;
    }

    public ArrayList<People> searchByLastName(String lastName) {//NOTE: Not efficient
        ArrayList<People> allNames = new ArrayList<>();
        String checkName = "";
        for (int i=0; i<allNames.size();i++){
            checkName = people.get(i).getLastName();
            if (lastName.equalsIgnoreCase(checkName)){
                allNames.add(people.get(i));
            }
        }
        if (allNames.isEmpty()){
            return null;//couldn't find the object
        }
        return allNames;
    }

    public ArrayList<People> searchByPhoneNumber(String phoneNumber) {//NOTE: Not efficient
        ArrayList<People> allNumbers = new ArrayList<>();
        String checkNumber = "";
        for (int i=0; i<allNumbers.size();i++){
            checkNumber = people.get(i).getPhoneNumber();
            if (phoneNumber.equalsIgnoreCase(checkNumber)){
                allNumbers.add(people.get(i));
            }
        }
        if (allNumbers.isEmpty()){
            return null;//couldn't find the object
        }
        return allNumbers;
    }
    
    public void addItem(People peopleObj){
        people.add(peopleObj);
    }

    public void editItem(People peopleObj) {}//Empty because the book should automatically update as it is now, but will be needed with the database

    public void removeItem(People peopleObj) {
        people.remove(peopleObj);
    }

}