import java.util.Arrays;
import java.util.Scanner;

public abstract class Input {

    public static String oneLetter(String quest, String expect){

        Scanner in = new Scanner(System.in);
        String[] params = expect.split(", ");
        String inputStr = "";
        String result = "";

        do {
            if (quest.contains("Are you sure?")){
                Print.red(quest);
                } else {
                Print.ln(quest);
                }
            if (in.hasNextLine()) {
                inputStr = in.nextLine().toUpperCase();
                for (String s : params)
                    if (inputStr.equals(s)) {
                        result = inputStr;
                        break;
                    }
            } else {
                for (String s : params)
                    if (inputStr.equals(s)) {
                        result = inputStr;
                        break;
                    }
            }
        } while (result.equals(""));
        return result;
    }

    public static String text(String quest){
        Scanner scanner = new Scanner(System.in);

        String inputStr = "";
        System.out.print(quest);
        if (scanner.hasNextLine()) {
            inputStr = scanner.nextLine();
        }
        return inputStr;
    }

    public static String login(Boolean registration){
        String result;
        do {
            result = text("Login :");
        } while(!loginValidation(result,registration));

        return result;
    }

    public static String password(String quest){
        String result;
        boolean success = false;
        do {
            result = text(quest);
            if (!passwordValidation(result)) {
                quest = "Please input minimum 6 symbols: ";
            } else { success = true;}
        } while (!success);
        return result;
    }

    public static String someText(String text){
        String result;
        do {
            result = text(text);
        } while (result.equals(""));
        return result;
    }

    public static LetterIndex letterOrIndex(String quest, String expect, int startIndex, int endIndex){

        Scanner in = new Scanner(System.in);
        String[] params = expect.split(", ");
        String inputStr = "";
        String stringAnswer = "";
        int index = -1;
        LetterIndex result = new LetterIndex();

        do {
            System.out.println(quest);
            if (in.hasNextLine()) {
                inputStr = in.nextLine().toUpperCase();
            }

            try {
                index = Integer.parseInt(inputStr);
            } catch (NumberFormatException e) {
                index = -1;
            }
            result.index = index;

            for (String s : params) {
                if (inputStr.equals(s)) {
                    stringAnswer = inputStr;
                }
            }

        } while (!(!stringAnswer.equals("") | (startIndex <= index && endIndex >= index)));

        result.letter = stringAnswer;
        return result;
    }


    public static boolean loginValidation(String str, Boolean registration){

        if (str.length()<2){
            Print.yellow("<!> Login is too short");
            return false;
        }
        if(registration && Authorisation.loginExist(str)){
            Print.yellow("<!> Such name already has been used");
            return false;
        }
        return true;
    }

    public static boolean passwordValidation(String str){

        if (str.length()<6){
            Print.yellow("<!> Password is too short");
            return false;
        }
        return true;
    }
}

class LetterIndex {
    public int index;
    public String letter;
}