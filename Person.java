import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashMap;

public class Person {

    private String personID;
    private String firstName;
    private String lastName;
    private String address;
    private String birthdate;
    private HashMap<LocalDate, Integer>demeritPoints = new HashMap<>();
    private boolean isSuspended;

    public Person(){
        firstName = "Bob";
        birthdate = "05-12-2005";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate date_1 = LocalDate.parse("20-03-2020", formatter);
        LocalDate date_2 = LocalDate.parse("05-12-2019", formatter);
        LocalDate date_3 = LocalDate.parse("25-03-2024", formatter);
        LocalDate date_4 = LocalDate.parse("11-02-2025", formatter);
        demeritPoints.put(date_1, 2);
        demeritPoints.put(date_2, 4);
        demeritPoints.put(date_3, 3);
        demeritPoints.put(date_4, 5);
    }

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








    // add demerit point to hash map
    public String addDemeritPoints(String date, Integer points){
        try{
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            LocalDate formattedDate = LocalDate.parse(date, formatter);
            if (points >= 1 && points <= 6){
                demeritPoints.put(formattedDate, points);
            }
            else{
                return "Failed";
            }
        }
        catch(DateTimeParseException e){
            return "Failed";
        }
        
        return "Success";
    }

    // write offense date and points to text file
    public String processDemeritPoints() {
        // create text file
        String fileName = firstName + ".txt";
        createFile(fileName);

        // calculate age
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate localBirthDate = LocalDate.parse(birthdate, formatter);
        LocalDate now = LocalDate.now(); // get current date
        Period period = Period.between(localBirthDate, now);
        int age = period.getYears();
        // System.out.println(age);

        int totalDemeritsPoint = 0;
        boolean validEntry = false;
        for (LocalDate offenseDate : demeritPoints.keySet()){
            // Check if offense date is within the past 2 years
            Period periodSinceOffense = Period.between(offenseDate, now);
            int yearsSinceOffense = periodSinceOffense.getYears();
            
            if (yearsSinceOffense < 2){
                int points = demeritPoints.get(offenseDate);
                // System.out.println(offenseDate + " " + points);

                if (points >= 1 && points <= 6){
                    totalDemeritsPoint += points;
                    fileWriter(fileName, formatter.format(offenseDate) + ": " + points);
                    validEntry = true;
                }
                else {
                    System.out.println("Invalid demerit point: " + points);
                    return "Failed";
                }
            }
        }

        if (!validEntry) {
            System.out.println("No valid demerit points to record.");
            return "Failed"; // Nothing valid within 2 years
        }

        // check License suspended
        // System.out.println(totalDemeritsPoint);
        isSuspended = false;
        if (age < 21){
            if (totalDemeritsPoint > 6){
                isSuspended = true;
            }
        }
        else{
            if (totalDemeritsPoint > 12){
                isSuspended = true;
            }
        } 
        System.out.println("License suspended: " + isSuspended + "\n");

        return "Success";
    }

    public void createFile(String fileName){
        File file = new File(fileName);

        try{
            if (file.createNewFile()) {
                System.out.println("File created: " + file.getName());
            } else {
                System.out.println("File already exists.");
            }
        }
        catch (IOException e){
            System.out.println("An error occur while create file");
            e.printStackTrace();
        }
    }

    public void fileWriter(String fileName, String content){
        try{
            FileWriter fileWriter = new FileWriter(fileName, true);
            fileWriter.write(content + "\n");
            fileWriter.close();
            // System.out.println("Write " + content + " to file successfully");
        }
        catch(IOException e){
            System.out.println("An error occur while writing to the file");
            e.printStackTrace();
        }
    }
}