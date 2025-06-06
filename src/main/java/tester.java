
public class tester {

    // public static String[] getLinePartsByID(String filePath, String searchID) {
    //     try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
    //         String line;

    //         while ((line = reader.readLine()) != null) {
    //             String[] parts = line.trim().split(",");

    //             if (parts.length == 5 && parts[0].equals(searchID)) {
    //                 return parts;
    //             }
    //         }
    //     } catch (IOException e) {
    //         e.printStackTrace();
    //     }
    //     return null; // No match found or error occurred
    // }
    public static void main(String[] args) {
        Person person = new Person();

        person.setPersonID("56s_d%&fAB");
        person.setFirstName("Matthew");
        person.setLastName("Kwong");
        person.setAddress("32|Lilac Street|Melbourne|Victoria|Australia");
        person.setBirthdate("19-11-2006");

        boolean flag = person.updatePersonalDetails("","Aryan","Israni","","");
        System.out.println(flag);
    }
}

