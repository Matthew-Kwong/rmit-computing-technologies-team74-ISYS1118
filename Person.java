import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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

    // Gayath : dont change this (Let me know if you need to)
    public boolean addPerson() {
        boolean isValid = true;

        if (!isValidPersonID(personID)) {
        isValid = false;
        }

        if (isValid && !isValidAddress(address)) {
        isValid = false;
        }

        if (isValid && !isValidBirthdate(birthdate)) {
        isValid = false;
        }

        if (isValid) {
            try {
                FileWriter writer = new FileWriter("persons.txt", true);
                String line = personID + "," + firstName + "," + lastName + "," + address + "," + birthdate + "\n";
                writer.write(line);
                writer.close();
            } catch (IOException e) {
                isValid = false;
            }
        }

        return isValid;
    }


    private boolean isValidPersonID(String id) {
        boolean result = true;

        if (id == null || id.length() != 10) {
            result = false;
        }

        if (result) {
            char c0 = id.charAt(0);
            char c1 = id.charAt(1);

            if (!Character.isDigit(c0) || !Character.isDigit(c1)) {
                result = false;
            } else {
                if (c0 < '2' || c0 > '9' || c1 < '2' || c1 > '9') {
                result = false;
                }
            }
        }

        if (result) {
            int specialCount = 0;
            int i = 2;
            while (i <= 7) {
                char c = id.charAt(i);
                if (!Character.isLetterOrDigit(c)) {
                specialCount = specialCount + 1;
                }
                i = i + 1;
            }
            if (specialCount < 2) {
                result = false;
            }
        }

        if (result) {
            char c8 = id.charAt(8);
            char c9 = id.charAt(9);
            if (!Character.isUpperCase(c8) || !Character.isUpperCase(c9)) {
                result = false;
            }
        }

        return result;
    }

    private boolean isValidAddress(String addr) {
        boolean result = true;

        if (addr == null) {
            result = false;
        }

        String[] parts = null;

        if (result) {
            parts = addr.split("\\|");
            if (parts.length != 5) {
                result = false;
            }
        }

        if (result && !parts[3].equals("Victoria")) {
            result = false;
        }

        return result;
    }

    private boolean isValidBirthdate(String dateStr) {
        boolean result = true;

        if (dateStr == null) {
            result = false;
        }

        if (result) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            sdf.setLenient(false);
            try {
                sdf.parse(dateStr);
            } catch (ParseException e) {
                result = false;
            }
        }

        return result;
    }


    //  setters to assign data before calling addPerson()
    public void setPersonID(String id) {
        this.personID = id;
      }
    
    public void setFirstName(String name) {
        this.firstName = name;
      }
    
    public void setLastName(String name) {
        this.lastName = name;
      }
    
    public void setAddress(String addr) {
        this.address = addr;
      }
    
    public void setBirthdate(String dob) {
        this.birthdate = dob;
      }















      
    public boolean updatePersonalDetails() {

        
        return true;
    }

    public String addDemeritPoints() {


        return "Success";
    }
}