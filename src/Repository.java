import java.util.List;

public class Repository {
    private static boolean useDataBase = true;

    public static void readUsers() {
        if (useDataBase) { DbRepository.readUsers(); }
        else { FileRepository.readUsers(); }
    }

    public static void saveUsers(List<User> users) {
        if (useDataBase) { DbRepository.saveUsers(users); }
        else { FileRepository.saveUsers(); }
    }

    public static void readContacts() {
        if (useDataBase) { DbRepository.readContacts(); }
        else { FileRepository.readContacts(); }
    }

    public static void saveContacts(List<Contact> contacts) {
        if (useDataBase) { DbRepository.saveContacts(contacts); }
        else { FileRepository.saveContacts(); }
    }

    public static void addContact(Contact contact) {
        if (useDataBase) { DbRepository.addContact(contact); }
        else { FileRepository.saveContacts(); }
    }

    public static void updateContact(Contact contact) {
        if (useDataBase) { DbRepository.updateContact(contact); }
        else { FileRepository.saveContacts(); }
    }

    public static void deleteContact(Contact contact) {
        if (useDataBase) { DbRepository.deleteContact(contact); }
        else { FileRepository.saveContacts(); }
    }

    public static boolean isUseDataBase() {
        return useDataBase;
    }

    public static void setUseDataBase(boolean useDataBase) {
        Repository.useDataBase = useDataBase;
    }

}
