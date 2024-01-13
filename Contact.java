import java.io.*;
import java.util.*;
public class Contact {
        String name;
        String phoneNumber;
        String email;
        String address;
        String birthday;

        Contact(String name, String phoneNumber, String email, String address, String birthday) {
            this.name = name;
            this.phoneNumber = phoneNumber;
            this.email = email;
            this.address = address;
            this.birthday = birthday;
        }
    }

    class Group {
        String name;
        List<Contact> contacts;

        Group(String name) {
            this.name = name;
            this.contacts = new ArrayList<>();
        }
    }


