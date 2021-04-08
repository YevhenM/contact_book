import java.io.FileNotFoundException;

public class Program {
    public static void main(String[] args) {
        Init.init();

        Print.blue("=========================== Welcome to the contact book ===========================");

        flow();
    }
    public static void flow(){
        Authorisation.auth();
        MainMenu.menu();
        flow();
    }
}
