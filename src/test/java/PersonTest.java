import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;


public class PersonTest {
    
    @Test
    public void testAddPersonValid() {
        Person person1 = new Person();

        person1.setPersonID("29_aYb#4*m");
        person1.setFirstName("Gayath");
        person1.setLastName("King");
        person1.setAddress("Street|Melbourne|Victoria|Australia");
        person1.setBirthdate("19-11-1990");

        assertEquals(true, person1.addPerson(),"Person added successfully");
    }

    @Test
    public void testInvalidIDLength() {
        Person person2 = new Person();

        person2.setPersonID("29_aYb#4*"); //ID length is 9 instead of 10
        person2.setFirstName("Matthew");
        person2.setLastName("Kwong");
        person2.setAddress("Highland Street|Melbourne|Victoria|Australia");
        person2.setBirthdate("15-11-1990");

        assertEquals(false, person2.addPerson(), "Person failed validation and was not added");
    }

    @Test
    public void testInvalidAddressFormat() {
        Person person3 = new Person();

        person3.setPersonID("29_aYb#4*m");
        person3.setFirstName("Kevin");
        person3.setLastName("Hart");
        person3.setAddress("Lilac Street-Melbourne-Victoria-Australia"); //Wrong address format
        person3.setBirthdate("19-11-1990");

        assertEquals(false, person3.addPerson(), "Person failed validation and was not added");
    }

    @Test
    public void testInvalidBirthDateFormat() {
        Person person4 = new Person();

        person4.setPersonID("29_aYb#4*m");
        person4.setFirstName("Aryan");
        person4.setLastName("Israni");
        person4.setAddress("Lilac Street|Melbourne|Victoria|Australia");
        person4.setBirthdate("19/11/1990"); //Invalid Date of Birth

        assertEquals(false, person4.addPerson(), "");
    }

    @Test
    public void testInvalidIDLengthAndInvalidState() {
        Person person5 = new Person();

        person5.setPersonID("29_aYb#4*mg");
        person5.setFirstName("Aditya");
        person5.setLastName("Janson");
        person5.setAddress("Lilac Street|Melbourne|New South Wale|Australia");
        person5.setBirthdate("19-11-1990");

        assertEquals(false, person5.addPerson(), "Person failed validation and was not added");
    }

    //Test
}
