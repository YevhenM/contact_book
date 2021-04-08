import java.util.ArrayList;
import java.util.List;

public class Authorisation {

    public static List<User> users = new ArrayList<>();
    protected static int id = 0;
    public static boolean isAuth=false;
    public static int userId;
    public static String authUser;

    public static void auth() {
        do {
            Print.blue("unauthorised > ");
            String answer = Input.oneLetter(
                    "Enter: [L]ogin | [R]egistration | [Q]uit program:",
                    "L, R, Q");
            switch (answer) {
                case "L" : logginIn(); break;
                case "R" : registration(); break;
                case "Q" : bye(); break;
            }
        } while(!isAuth);
    }

    public static void logginIn(){
        Print.ln("---------------- Login ----------------");
        String login;
        String password;

        login = Input.login(false);
        password = Input.password("Password: ");
        users.forEach(u-> {
            if (u.getName().equals(login) && u.getPassword().equals(password) ){
                isAuth = true;
                userId = u.getId();
                authUser = u.getName();
            }
        });
        if(!isAuth){ Print.yellow("<!> Wrong login/password"); return; }
        Print.ln("---------------------------------------");
    }

    public static void registration(){
        Print.ln("---------------- Registration ----------------");
        boolean firstInput = true;
        String login = Input.login(true);
        String password;
        String confrimPassword;

        do {
            if (!firstInput){Print.yellow("<!> Please be careful! Password does not match ");}
            password = Input.password("Password: ");
            confrimPassword = Input.password("Confirm password: ");
            firstInput = false;

        } while ( !password.equals(confrimPassword) );

        User newUser;
        newUser = new User(id,login,password);
        users.add(newUser);
        id++;

        Repository.saveUsers(users);

        Print.emptyln();
        Print.green("- Successful registration -");
        Print.emptyln();

        Print.ln("----------------------------------------------");
    }

    public static void logout()  {
        isAuth = false;
    }

    public static void bye(){
        Print.blue("===================================  Goodbye!  ====================================");
        Print.white("(c)2021 Yevhen Marukhnyak - ISG.Lviv");
        System.exit(0);
    }

    public static boolean loginExist(String userName){
        return users.stream().anyMatch(u -> u.getName().equals(userName));
    }
}
