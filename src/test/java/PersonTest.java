import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;


public class PersonTest {
    
    @Test
    public void testAddPersonValid() {
        Person person1 = new Person();

        person1.setPersonID("56s_d%&fAB");
        person1.setFirstName("Matthew");
        person1.setLastName("Kwong");
        person1.setAddress("32|Lilac Street|Melbourne|Victoria|Australia");
        person1.setBirthdate("19-11-1990");

        assertEquals(true, person1.addPerson(),"Person added successfully");
    }

    @Test
    public void testInvalidIDLength() {
        Person person2 = new Person();

        person2.setPersonID("56s_d%&fA"); //ID length is 9 instead of 10
        person2.setFirstName("Matthew");
        person2.setLastName("Kwong");
        person2.setAddress("32|Lilac Street|Melbourne|Victoria|Australia");
        person2.setBirthdate("15-11-1990");

        assertEquals(false, person2.addPerson(), "Person failed validation and was not added");
    }

    @Test
    public void testInvalidAddressFormat() {
        Person person3 = new Person();

        person3.setPersonID("56s_d%&fAB");
        person3.setFirstName("Matthew");
        person3.setLastName("Kwong");
        person3.setAddress("32-Lilac Street-Melbourne-Victoria-Australia"); //Wrong address format
        person3.setBirthdate("19-11-1990");

        assertEquals(false, person3.addPerson(), "Person failed validation and was not added");
    }

    @Test
    public void testInvalidBirthDateFormat() {
        Person person4 = new Person();

        person4.setPersonID("56s_d%&fAB");
        person4.setFirstName("Aryan");
        person4.setLastName("Israni");
        person4.setAddress("32|Lilac Street|Melbourne|Victoria|Australia");
        person4.setBirthdate("19/11/1990"); //Invalid Date of Birth

        assertEquals(false, person4.addPerson(), "");
    }

    @Test
    public void testInvalidIDLengthAndInvalidState() {
        Person person5 = new Person();

        person5.setPersonID("56s_d%&fABg"); //Character length is 11 instead of 10s
        person5.setFirstName("Aditya");
        person5.setLastName("Janson");
        person5.setAddress("32|Lilac Street|Melbourne|New South Wale|Australia"); //Invalid state New South Wale
        person5.setBirthdate("19-11-1990");

        assertEquals(false, person5.addPerson(), "Person failed validation and was not added");
    }

    // Testing updateProfile()
    @Test
    public void testUnderageChangeAddress() {
        Person person6 = new Person();

        person6.setPersonID("56s_d%&fAB");
        person6.setFirstName("Matthew");
        person6.setLastName("Kwong");
        person6.setAddress("32|Lilac Street|Melbourne|Victoria|Australia");
        person6.setBirthdate("19-11-2010");

        assertEquals(false, person6.updatePersonalDetails("","","","42|La Trobe Street|Melbourne|Victoria|Australia",""),"You are not eligible to update address as you are under 18 years old or address format is invalid.");
    }

    @Test
    public void testMultipleFieldUpdate() {
        Person person7 = new Person();

        person7.setPersonID("56s_d%&fAB");
        person7.setFirstName("Matthew");
        person7.setLastName("Kwong");
        person7.setAddress("32|Lilac Street|Melbourne|Victoria|Australia");
        person7.setBirthdate("19-11-2010");

        assertEquals(false, person7.updatePersonalDetails("", "Kevin","","42|Little Collins Street|Melbourne|Victoria|Australia","20-19-2000"),"Birthdate updated, Nothing else can be updated.");
    }

    @Test
    public void testValidInformationChange() {
        Person person8 = new Person();

        person8.setPersonID("56s_d%&fAB");
        person8.setFirstName("Matthew");
        person8.setLastName("Kwong");
        person8.setAddress("32|Lilac Street|Melbourne|Victoria|Australia");
        person8.setBirthdate("19-11-2000");

        assertEquals(true, person8.updatePersonalDetails("","","","42|Lilac Street|Melbourne|Victoria|Australia",""));
    }

    @Test
    public void testChangeIDFirstCharacterEven() {
        Person person9 = new Person();

        person9.setPersonID("46s_d%&fAB");
        person9.setFirstName("Matthew");
        person9.setLastName("Kwong");
        person9.setAddress("32|Lilac Street|Melbourne|Victoria|Australia");
        person9.setBirthdate("19-11-2000");

        assertEquals(false, person9.updatePersonalDetails("56s_d%&fAB","","","",""),"Invalid Person ID format or ID starts with an even number.");
    }

    @Test
    public void testValidFirstAndLastNameChange() {
        Person person10 = new Person();
        person10.setPersonID("56s_d%&fAB");
        person10.setFirstName("Matthew");
        person10.setLastName("Kwong");
        person10.setAddress("32|Lilac Street|Melbourne|Victoria|Australia");
        person10.setBirthdate("19-11-2006");

        assertEquals(true, person10.updatePersonalDetails("","Aryan","Israni","",""));
    }


    // Testing addDemeritPoints and Process demerit point

    @Test
    public void testInvalidDateFormat()  {
        Person person11 = new Person();

        person11.setPersonID("56s_d%&fAB");
        person11.setFirstName("Matthew");
        person11.setLastName("Kwong");
        person11.setAddress("32|Lilac Street|Melbourne|Victoria|Australia");
        person11.setBirthdate("19-11-1990");

        assertEquals("Failed", person11.addDemeritPoints("6-23-2025", 4));
    }

    @Test
    public void testDemeritOverLimit() {
        Person person12 = new Person();

        person12.setPersonID("56s_d%&fAB");
        person12.setFirstName("Matthew");
        person12.setLastName("Kwong");
        person12.setAddress("32|Lilac Street|Melbourne|Victoria|Australia");
        person12.setBirthdate("19-11-2005");
        //Initialize the starting demerit point as 6
        person12.addDemeritPoints("17-06-2023", 6);

        person12.addDemeritPoints("10-10-2024", 1);
        person12.processDemeritPoints();

        assertEquals(true, person12.getIsSuspended());
    }

    @Test
    public void testValidDemeritInput() {
        Person person13 = new Person();

        person13.setPersonID("56s_d%&fAB");
        person13.setFirstName("Matthew");
        person13.setLastName("Kwong");
        person13.setAddress("32|Lilac Street|Melbourne|Victoria|Australia");
        person13.setBirthdate("19-11-2005");
        //Initialize the starting demerit point as 2
        person13.addDemeritPoints("17-06-2023", 2);

        person13.addDemeritPoints("10-10-2024", 1);

        assertEquals("Success", person13.processDemeritPoints());
    }

    @Test
    public void testDemeritUnderlimit() {
        Person person14 = new Person();

        person14.setPersonID("56s_d%&fAB");
        person14.setFirstName("Matthew");
        person14.setLastName("Kwong");
        person14.setAddress("32|Lilac Street|Melbourne|Victoria|Australia");
        person14.setBirthdate("19-11-1997");
        //Initialize the starting demerit point as 6
        person14.addDemeritPoints("17-06-2023", 6);
        person14.addDemeritPoints("18-06-2023", 3);

        person14.addDemeritPoints("10-10-2024", 1);
        person14.processDemeritPoints();

        assertEquals(false, person14.getIsSuspended());
    }

    @Test public void testInputOutOfBounds() {
        Person person15 = new Person();

        person15.setPersonID("56s_d%&fAB");
        person15.setFirstName("Matthew");
        person15.setLastName("Kwong");
        person15.setAddress("32|Lilac Street|Melbourne|Victoria|Australia");
        person15.setBirthdate("19-11-2000");

        assertEquals("Failed", person15.addDemeritPoints("10-06-2025", 9));
    }
}
