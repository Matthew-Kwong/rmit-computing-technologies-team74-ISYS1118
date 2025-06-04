public class tester {
    public static void main(String[] args) {
        Person p = new Person();

        // Set valid person details
        p.setPersonID("56@_d%fGZQ");
        p.setFirstName("John");
        p.setLastName("Doe");
        p.setAddress("123|Main Street|Melbourne|Victoria|Australia");
        p.setBirthdate("15-11-1990");

        boolean added = p.addPerson();

        if (added) {
            System.out.println("Person added successfully.");
        } else {
            System.out.println("Person failed validation and was not added.");
        }
        // System.out.println(p.processDemeritPoints());
        p.addDemeritPoints("20-05-2025", 5);
        System.out.println(p.processDemeritPoints());
    }
}
