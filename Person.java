import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.Reader;
import java.time.LocalDate;
import java.time.Period;

public class Person {

    private String personID;
    private String firstName;
    private String lastName;
    private String address;
    private String birthdate;
    private HashMap<LocalDate, Integer>demeritPoints = new HashMap<>();
    private boolean isSuspended;

    public Person(){
        firstName = "John";
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

        // Validate ID format
        if (!isValidPersonID(personID)) {
        isValid = false;
        }

        // Validate address format
        if (isValid && !isValidAddress(address)) {
        isValid = false;
        }

        // Validate birthdate format
        if (isValid && !isValidBirthdate(birthdate)) {
        isValid = false;
        }

        // Write to file if all checks passed
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


    // Validates person ID based on specific format
    private boolean isValidPersonID(String id) {
        boolean result = true;

        // Condition 1: Must be exactly 10 characters
        if (id == null || id.length() != 10) {
            result = false;
        }

        // Condition 2: First two characters must be digits between '2' and '9'
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

        // Condition 3: Characters at positions 2 to 7 must include at least two non-alphanumeric characters
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

        // Condition 4: Last two characters must be uppercase letters
        if (result) {
            char c8 = id.charAt(8);
            char c9 = id.charAt(9);
            if (!Character.isUpperCase(c8) || !Character.isUpperCase(c9)) {
                result = false;
            }
        }

        return result;
    }

    //  Validates address format 
    private boolean isValidAddress(String addr) {
        boolean result = true;

        // Must not be null
        if (addr == null) {
            result = false;
        }

        String[] parts = null;

        // Must contain 5 fields separated by '|'
        if (result) {
            parts = addr.split("\\|");
            if (parts.length != 5) {
                result = false;
            }
        }

        // State (4th field) must be 'Victoria'
        if (result && !parts[3].equals("Victoria")) {
            result = false;
        }

        return result;
    }


    // Validates date format (dd-MM-yyyy) 
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


    public void deletePerson() throws IOException {
        String fileName = "persons.txt";
        File file = new File(fileName);
        File tempFile = new File("temp.txt");
        if (!file.exists()) {
            System.out.println("File does not exist.");
            return;
        }
        BufferedReader reader = new BufferedReader(new FileReader(file));
        PrintWriter writer = new PrintWriter(new FileWriter(tempFile));
        String currLine;


        while ((currLine = reader.readLine()) != null) {
            if (!currLine.startsWith(personID + ",")) {
                writer.println(currLine);
            }
        }
        writer.close();
        reader.close();
        if (file.delete()) {
            tempFile.renameTo(file);
        }
        else {
            System.out.println("Error deleting the file.");
        }
    }

    public boolean isOverEighteen() {
        LocalDate today = LocalDate.now();
        LocalDate DOB = LocalDate.parse(this.birthdate, java.time.format.DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        Period age = Period.between(DOB, today);
        if (age.getYears() >= 18) {
            return true;
        }
        else {
            return false;
        }
    }












  
    public boolean updatePersonalDetails(Scanner scanner) {
        try {
            deletePerson();
        } catch (IOException e) {
            return false;
        }
        boolean UserContinue = true;
        while (UserContinue) {

            System.out.println("Select a number from 1-6 for the information you want to update:");
            System.out.println("1. Person ID");
            System.out.println("2. First Name");
            System.out.println("3. Last Name");
            System.out.println("4. Address");
            System.out.println("5. Birthdate");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            boolean canUpdateDOB = isOverEighteen();

            
            
            boolean validInt = false;
            while (!validInt) {
                try {
                    int choice = Integer.parseInt(scanner.nextLine());
                    if (choice < 1 || choice > 6) {
                        System.out.println("Invalid choice. Please select a number between 1 and 6.");
                    } else {
                        validInt = true;
                        switch (choice) {
                            case 1:
                                if (personID.charAt(0) % 2 == 0) {
                                    System.out.println("Person ID cannot be updated for even-numbered IDs.");
                                    continue;
                                }
                                System.out.print("""
                                Enter new Person ID: should be exactly 10 characters long, 
                                the first two characters should be numbers between 2 and 9, 
                                there should be at least two special characters between characters 3 and 8, 
                                and the last two characters should be uppercase letters (A-Z). 
                                Example: \"56s_d%&fAB\"
                                """);
                                String newID = scanner.nextLine();
                                if (isValidPersonID(newID)) {
                                    setPersonID(newID);
                                } else {
                                    System.out.println("Invalid Person ID.");
                                }
                                break;
                            case 2:
                                System.out.print("Enter new First Name: ");
                                setFirstName(scanner.nextLine());
                                break;
                            case 3:
                                System.out.print("Enter new Last Name: ");
                                setLastName(scanner.nextLine());
                                break;
                            case 4:
                                System.out.print("Enter new Address: Format: 123|Main Street|Melbourne|Victoria|Australia\n");
                                String newAddress = scanner.nextLine();
                                if (isValidAddress(newAddress)) {
                                    setAddress(newAddress);
                                } else {
                                    System.out.println("Invalid Address.");
                                }
                                break;
                            case 5:
                                if (!canUpdateDOB) {
                                    System.out.println("You cannot update the birthdate. You are not over 18.");
                                    continue;
                                }
                                System.out.print("Enter new Birthdate (dd-MM-yyyy): ");
                                String newBirthdate = scanner.nextLine();
                                if (isValidBirthdate(newBirthdate)) {
                                    setBirthdate(newBirthdate);
                                    System.out.println("Birthdate updated successfully, nothing else can be changed now!");
                                    addPerson();
                                    return true;
                                } else {
                                    System.out.println("Invalid Birthdate.");
                                }
                                break;
                            case 6:
                                System.out.println("Exiting update process.");
                                UserContinue = false;
                                continue;
                        }
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a number between 1 and 6.");
                }
            }
            
        }
        
        
        
        boolean added = addPerson();
        if (!added) {
            System.out.println("Failed to update personal details & user may not exist.");
            return false;
        }
        return true;
    }

    

    // add demerit point to hash map
    public String addDemeritPoints(String date, Integer points){
        try{
            // check date format
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            LocalDate formattedDate = LocalDate.parse(date, formatter);
            // points check
            if (points >= 1 && points <= 6){
                demeritPoints.put(formattedDate, points);
            }
            else{
                System.out.println("Invalid demerit point: " + points);
                return "Failed";
            }
        }
        catch(DateTimeParseException e){
            System.out.println("Incorrect date format");
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
        LocalDate localBirthDate = LocalDate.parse(birthdate, formatter); // format birth date to LocalDate for age calculation
        LocalDate now = LocalDate.now(); // get current date
        Period period = Period.between(localBirthDate, now);
        int age = period.getYears();

        int totalDemeritsPoint = 0;
        boolean validEntry = false;
        for (LocalDate offenseDate : demeritPoints.keySet()){
            // Check if offense date is within the past 2 years
            Period periodSinceOffense = Period.between(offenseDate, now);
            int yearsSinceOffense = periodSinceOffense.getYears();
            
            if (yearsSinceOffense < 2){
                int points = demeritPoints.get(offenseDate);

                // check if points is within 1 to 6 or not
                if (points >= 1 && points <= 6){
                    totalDemeritsPoint += points;
                    fileWriter(fileName, formatter.format(offenseDate) + ": " + points);
                    validEntry = true;
                }
                else {
                    System.out.println("Invalid demerit point: " + points);
                    return "Failed"; // points is not in 1 to 6
                }
            }
        }

        if (!validEntry) {
            System.out.println("No valid demerit points to record.");
            return "Failed"; // Nothing valid within 2 years
        }

        // check License suspension
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
        System.out.println("License suspended: " + isSuspended);

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