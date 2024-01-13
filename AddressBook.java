import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
public class AddressBook {
        private List<Contact> contacts;
        private List<Group> groups;

        public AddressBook() {
            this.contacts = new ArrayList<>();
            this.groups = new ArrayList<>();
        }

        // Sprint 3 - Search Functionality
        public List<Contact> searchContacts(String query) {
            List<Contact> results = new ArrayList<>();
            for (Contact contact : contacts) {
                if (contact.name.toLowerCase().contains(query.toLowerCase())
                        || contact.phoneNumber.contains(query)
                        || contact.email.toLowerCase().contains(query.toLowerCase())) {
                    results.add(contact);
                }
            }
            return results;
        }

        // Sprint 3 - Group Contacts
        public void createGroup(String groupName) {
            Group group = new Group(groupName);
            groups.add(group);
        }

        public void editGroup(String groupName, String newGroupName) {
            for (Group group : groups) {
                if (group.name.equals(groupName)) {
                    group.name = newGroupName;
                    break;
                }
            }
        }

        public void deleteGroup(String groupName) {
            groups.removeIf(group -> group.name.equals(groupName));
        }

        public void addContactToGroup(Contact contact, String groupName) {
            for (Group group : groups) {
                if (group.name.equals(groupName)) {
                    group.contacts.add(contact);
                    break;
                }
            }
        }

        public void removeContactFromGroup(Contact contact, String groupName) {
            for (Group group : groups) {
                if (group.name.equals(groupName)) {
                    group.contacts.remove(contact);
                    break;
                }
            }
        }

        // Sprint 4 - Export/Import Functionality
        public void exportAddressBook(String fileName) {
            try (PrintWriter writer = new PrintWriter(new FileWriter(fileName))) {
                for (Contact contact : contacts) {
                    writer.println(contact.name + "," + contact.phoneNumber + "," +
                            contact.email + "," + contact.address + "," + contact.birthday);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void importAddressBook(String fileName) {
            try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] data = line.split(",");
                    Contact contact = new Contact(data[0], data[1], data[2], data[3], data[4]);
                    contacts.add(contact);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // Sprint 4 - Basic Sorting
        public void sortContacts(boolean ascending) {
            contacts.sort(Comparator.comparing(contact -> contact.name.toLowerCase()));
            if (!ascending) {
                Collections.reverse(contacts);
            }
        }

        // Sprint 4 - Pagination for Large Address Books
        public List<Contact> getContactsByPage(int page, int pageSize) {
            int startIndex = (page - 1) * pageSize;
            int endIndex = Math.min(startIndex + pageSize, contacts.size());
            return contacts.subList(startIndex, endIndex);
        }

        public static void main(String[] args) {
            AddressBook addressBook = new AddressBook();

            // Sample usage (you can replace this with user input or other interaction logic)
            Contact contact1 = new Contact("John Doe", "1234567890", "john.doe@email.com", "123 Main St", "01/01/1990");
            Contact contact2 = new Contact("Jane Smith", "9876543210", "jane.smith@email.com", "456 Oak St", "02/15/1985");

            addressBook.contacts.add(contact1);
            addressBook.contacts.add(contact2);

            addressBook.createGroup("Friends");
            addressBook.addContactToGroup(contact1, "Friends");

            // Search functionality
            List<Contact> searchResults = addressBook.searchContacts("John");
            System.out.println("Search Results:");
            for (Contact result : searchResults) {
                System.out.println(result.name);
            }

            // Export/Import functionality
            addressBook.exportAddressBook("address_book.csv");
            addressBook.importAddressBook("address_book.csv");

            // Basic Sorting
            addressBook.sortContacts(true);

            // Pagination for Large Address Books
            List<Contact> page1 = addressBook.getContactsByPage(1, 1);
            System.out.println("Page 1 Contacts:");
            for (Contact contact : page1) {
                System.out.println(contact.name);
            }
        }
    }
