import java.util.Date;
import java.util.HashMap;

public class Person {

    private String personID;
    private String firstName;
    private String lastName;
    private String address;
    private String birthdate;
    private HashMap<Date, Integer>demeritPoints;
    private boolean isSuspended;

    public boolean addPerson() {


        return true;
    }

    public boolean updatePersonalDetails() {

        
        return true;
    }

    public String addDemeritPoints() {


        return "Success";
    }
}