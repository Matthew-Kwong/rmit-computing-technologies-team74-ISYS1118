import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class tester {

    public static String[] getLinePartsByID(String filePath, String searchID) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.trim().split(",");

                if (parts.length == 5 && parts[0].equals(searchID)) {
                    return parts;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null; // No match found or error occurred
    }
    public static void main(String[] args) {
        
        Person p = new Person();
        Scanner scanner = new Scanner(System.in);
        // Set valid person details
       
        boolean exit = false;
        while (!exit) {
            
            System.out.println("Select the number for the following options:");
            System.out.println("1. Add Person Details");
            System.out.println("2. Update Person Details");
            System.out.println("3. Delete Person Details");
            System.out.println("4. Add Demerit Points");
            System.out.println("5. Process Demerit Points");
            System.out.println("6. Exit");
            try {
                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        System.out.println("Enter Person ID (Format: 56@_d%fGZQ):");
                        String personID = scanner.nextLine();
                        System.out.println("Enter First Name:");
                        String firstName = scanner.nextLine();
                        System.out.println("Enter Last Name:");
                        String lastName = scanner.nextLine();
                        System.out.println("Enter Address (Format: 123|Main Street|Melbourne|Victoria|Australia):");
                        String address = scanner.nextLine();
                        System.out.println("Enter Birthdate (Format: dd-MM-yyyy):");
                        String birthdate = scanner.nextLine();
                        Person AddingPerson = new Person(); 
                        AddingPerson.setPersonID(personID);
                        AddingPerson.setFirstName(firstName);
                        AddingPerson.setLastName(lastName);
                        AddingPerson.setAddress(address);
                        AddingPerson.setBirthdate(birthdate);
                        if (AddingPerson.addPerson()) {
                            System.out.println("Person details added successfully.");
                        } else {
                            System.out.println("Failed to add person details. Please check the input format.");
                        }
                        break;
                    case 2:
                        System.out.println("Enter Person ID to update:");
                        String updatePersonID = scanner.nextLine();
                        String[] existingDetails = getLinePartsByID("persons.txt", updatePersonID);
                        if (existingDetails == null) {
                            System.out.println("No person found with that ID.");
                            break;
                        }
                        Person currPerson = new Person();
                        System.out.println("Current Person ID: " + existingDetails[0]);
                        currPerson.setPersonID(existingDetails[0]);
                        System.out.println("Current First Name: " + existingDetails[1]);
                        currPerson.setFirstName(existingDetails[1]);
                        System.out.println("Current Last Name: " + existingDetails[2]);
                        currPerson.setLastName(existingDetails[2]);
                        System.out.println("Current Address: " + existingDetails[3]); 
                        currPerson.setAddress(existingDetails[3]);
                        System.out.println("Current Birthdate: " + existingDetails[4]);
                        currPerson.setBirthdate(existingDetails[4]);
                        System.out.println(currPerson.updatePersonalDetails("28(*&)IJKJ", existingDetails[1], "GA", "345|wendell street|Melbourne|Victoria|Australia", "29-04-2004"));


                        break;
                    case 3:
                        System.out.println("Enter Person ID to delete:");
                        String deletePersonID = scanner.nextLine();
                        Person deletePersonOfID = new Person();
                        deletePersonOfID.setPersonID(deletePersonID);
                        deletePersonOfID.deletePerson();
                        break;
                    case 4:
                        System.out.println("Enter offense date in dd-MM-yyyy format");
                        String date = scanner.nextLine(); 
                        System.out.println("Enter demerit points");
                        Integer points = scanner.nextInt();
                        System.out.println("Add demerit points: "+ p.addDemeritPoints(date, points));

                        break;

                    case 5:
                        System.out.println("Process Demerit points started");
                        System.out.println("Process Demerit points: " + p.writeDemeritPoints());

                        break;

                    case 6:
                        exit = true;
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            } catch (IOException e) {
                System.out.println("An error occurred: " + e.getMessage());
            }
            
        }

        scanner.close();
        



    }
}