import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

public class DbRepository {

    private static final String dbUserName = "root";
    private static final String password = "SQ1122Rt";
    private static final String url = "jdbc:mysql://localhost:3306/mydb";

    public static Connection connect() {
        Connection connection;
        try {
            connection = DriverManager.getConnection(url, dbUserName, password);
            return connection;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void readUsers() {
        Connection conn = null;
        try {
            conn = connect();
            if (conn != null) {
                Statement statement = conn.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM users");
                while (resultSet.next()) {
                    int id = resultSet.getInt("idUser");
                    String name = resultSet.getString("Name");
                    String password = resultSet.getString("Password");
                    User newUser = new User(id, name, password);
                    Authorisation.users.add(newUser);
                }
                conn.close();
            }
            Authorisation.id = Authorisation.users.stream()
                    .map(User::getId)
                    .max(Integer::compare)
                    .orElse(-1) + 1;
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void saveUsers(List<User> users) {
        Connection conn = null;
        try {
            conn = connect();
            if (conn != null) {
                Statement statement = conn.createStatement();
                statement.execute("DROP TABLE IF EXISTS users");
                statement.execute(
                        "CREATE TABLE IF NOT EXISTS `mydb`.`Users` (" +
                                " `idUser` INT NOT NULL," +
                                "  `Name` VARCHAR(45) NULL," +
                                "  `Password` VARCHAR(45) NULL," +
                                "  PRIMARY KEY (`idUser`)," +
                                "  UNIQUE INDEX `idUser_UNIQUE` (`idUser` ASC) )" +
                                "ENGINE = InnoDB;");
                for (User u : users) {
                    String id = Integer.toString(u.getId());
                    statement.execute("INSERT INTO users (idUser, Name, Password) VALUES('"
                            + id + "', '" + u.getName() + "', '" + u.getPassword() + "');");
                }
                conn.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void readContacts() {
        Connection conn = null;
        try {
            conn = connect();
            if (conn != null) {
                Statement statement = conn.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM Contacts");

                while (resultSet.next()) {
                    int id = resultSet.getInt("idUser");

                    int contactId = resultSet.getInt("idContact");
                    int userId = resultSet.getInt("idUser");
                    String contactName = resultSet.getString("ContactName");
                    String phoneNumber = resultSet.getString("ContactPhone");
                    String adress = resultSet.getString("ContactAddress");
                    Contact newContact = new Contact(contactId, userId, contactName, phoneNumber, adress);
                    MainMenu.contacts.add(newContact);
                }
                if (MainMenu.contacts.size() > 0) {
                    MainMenu.contactId = MainMenu.contacts.stream()
                            .map(Contact::getContactId)
                            .max(Integer::compare)
                            .orElse(-1) + 1;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void saveContacts(List<Contact> contacts) {
        Connection conn = null;
        try {
            conn = connect();
            if (conn != null) {
                Statement statement = conn.createStatement();
                statement.execute("DROP TABLE IF EXISTS Contacts");
                statement.execute(
                        "CREATE TABLE IF NOT EXISTS `mydb`.`Contacts` (" +
                                "  `idContact` INT NOT NULL," +
                                "  `idUser` INT NOT NULL," +
                                "  `ContactName` VARCHAR(45) NULL," +
                                "  `ContactPhone` VARCHAR(16) NULL," +
                                "  `ContactAddress` VARCHAR(80) NULL," +
                                "  PRIMARY KEY (`idContact`)," +
                                "  UNIQUE INDEX `idContact_UNIQUE` (`idContact` ASC))");
                for (Contact c : contacts) {
                    String id = Integer.toString(c.getContactId());
                    String userId = Integer.toString(c.getUserId());
                    String cName = c.getContactName();
                    String cPhone = c.getPhoneNumber();
                    String cAddress = c.getAdress();

                    statement.execute(
                      "INSERT INTO Contacts (idContact, idUser, ContactName, ContactPhone, ContactAddress) VALUES('"
                                    + id + "', '"
                                    + userId + "', '"
                                    + cName + "', '"
                                    + cPhone + "', '"
                                    + cAddress + "');");
                }
                conn.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void addContact(Contact contact) {
        Connection conn = null;

        try {
            conn = connect();
            if (conn != null) {
                Statement statement = conn.createStatement();

                String id = Integer.toString(contact.getContactId());
                String userId = Integer.toString(contact.getUserId());
                String cName = contact.getContactName();
                String cPhone = contact.getPhoneNumber();
                String cAddress = contact.getAdress();
                statement.execute(
                     "INSERT INTO Contacts (idContact, idUser, ContactName, ContactPhone, ContactAddress) VALUES('"
                                + id + "', '"
                                + userId + "', '"
                                + cName + "', '"
                                + cPhone + "', '"
                                + cAddress + "');");

                conn.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void updateContact(Contact contact) {
        Connection conn = null;

        try {
            conn = connect();
            if (conn != null) {
                Statement statement = conn.createStatement();
                String id = Integer.toString(contact.getContactId());
                String cName = contact.getContactName();
                String cPhone = contact.getPhoneNumber();
                String cAddress = contact.getAdress();

                statement.execute(
                        "UPDATE Contacts " +
                            "SET ContactName = '" + cName +
                            "', ContactPhone = '" + cPhone +
                            "', ContactAddress = '" + cAddress +
                            "' WHERE idContact = " + id + " ;");

                conn.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void deleteContact(Contact contact) {
        Connection conn = null;

        try {
            conn = connect();
            if (conn != null) {
                Statement statement = conn.createStatement();
                String id = Integer.toString(contact.getContactId());
                statement.execute(
                        "DELETE FROM Contacts WHERE idContact = " + id + " ;");

                conn.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}




