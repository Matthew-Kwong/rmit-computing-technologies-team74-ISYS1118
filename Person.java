import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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




  
    public boolean updatePersonalDetails() {
        try {
            deletePerson();
        } catch (IOException e) {
            return false;
        }
        boolean UserContinue = true;
        Scanner scanner = new Scanner(System.in);
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
                                System.out.print("Enter new Person ID: ");
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
                            case 3:
                                System.out.print("Enter new Last Name: ");
                                setLastName(scanner.nextLine());
                            case 4:
                                System.out.print("Enter new Address: ");
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
        
        scanner.close();
        
        boolean added = addPerson();
        if (!added) {
            System.out.println("Failed to update personal details & user may not exist.");
            return false;
        }
        
        return true;
    }

    public String addDemeritPoints() {


        return "Success";
    }
}