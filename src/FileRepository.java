import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Scanner;

public class FileRepository {
    private final static String SPLITTER = "%/";
    private final static String FILE_USERS = "users.txt";
    private final static String FILE_CONTACTS = "contacts.txt";

    public static void readUsers() {

        try {
            File file = new File(FILE_USERS);
            Scanner scanner = new Scanner(file);

            while(scanner.hasNextLine()) {
                String[] a = scanner.nextLine().split(SPLITTER);
                int id = Integer.parseInt(a[0]);
                String name = a[1];
                String password = a[2];
                User newUser = new User(id, name, password);
                Authorisation.users.add(newUser);
            }
            Authorisation.id = Authorisation.users.stream()
                    .map(User::getId)
                    .max(Integer::compare)
                    .orElse(-1) + 1;
            scanner.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static void saveUsers() {
        PrintWriter pw = null;
        try {
            pw = new PrintWriter(FILE_USERS);
            for(User u: Authorisation.users){
                pw.println(u.getId()+SPLITTER+u.getName()+SPLITTER+u.getPassword());
            }
            pw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void readContacts() {

        try{
            File file = new File(FILE_CONTACTS);
            Scanner scanner = new Scanner(file);

            while(scanner.hasNextLine()) {
                String[] a = scanner.nextLine().split(SPLITTER);
                int contactId = Integer.parseInt(a[0]);
                int userId = Integer.parseInt(a[1]);
                String contactName = a[2];
                String phoneNumber = a[3];
                String adress = a[4];
                Contact newContact = new Contact(contactId, userId, contactName, phoneNumber, adress);
                MainMenu.contacts.add(newContact);
            }
            if (MainMenu.contacts.size()>0) {
                MainMenu.contactId = MainMenu.contacts.stream()
                        .map(Contact::getContactId)
                        .max(Integer::compare)
                        .orElse(-1) + 1;
            }
            scanner.close();

        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void saveContacts() {

        PrintWriter pw = null;
        try {
            pw = new PrintWriter(FILE_CONTACTS);
            for(Contact c: MainMenu.contacts){
                pw.println( c.getContactId()+SPLITTER
                            +c.getUserId()+SPLITTER
                            +c.getContactName() +SPLITTER
                            +c.getPhoneNumber()+SPLITTER
                            +c.getAdress());
            }
            pw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
