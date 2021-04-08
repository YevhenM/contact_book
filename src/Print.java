public abstract class Print {

    static String ANSI_RESET = "\u001B[0m";
    static String ANSI_BLACK = "\u001B[30m";
    static String ANSI_RED = "\u001B[31m";
    static String ANSI_GREEN = "\u001B[32m";
    static String ANSI_YELLOW = "\u001B[33m";
    static String ANSI_BLUE = "\u001B[34m";
    static String ANSI_PURPLE = "\u001B[35m";
    static String ANSI_CYAN = "\u001B[36m";
    static String ANSI_WHITE = "\u001B[37m";

    public static void ln(String str) {
        System.out.println(str);
    }

    public static void red(String str) {
        System.out.println(ANSI_RED + str + ANSI_RESET);
    }

    public static void yellow(String str) {
        System.out.println(ANSI_YELLOW + str + ANSI_RESET);
    }

    public static void green(String str) {
        System.out.println(ANSI_GREEN + str + ANSI_RESET);
    }

    public static void blue(String str) {
        Print.emptyln();
        System.out.println(ANSI_BLUE + str + ANSI_RESET);
    }

    public static void white(String str) {
        System.out.println(ANSI_WHITE + str + ANSI_RESET);
    }

    public static void cyan(String str) {
        System.out.println(ANSI_CYAN + str + ANSI_RESET);
    }

    public static void purple(String str) {
        System.out.println(ANSI_PURPLE + str + ANSI_RESET);
    }

    public static void emptyln() {
        System.out.println();
    }

    public static void printContact(SelectedContact c, String substr) {
        String spaces = "                                  ";
        String stIndex = spaces + c.getListIndex();
        stIndex = stIndex.substring(stIndex.length() - 4);
        stIndex = ANSI_WHITE + stIndex + ". " + ANSI_RESET;

        String name = highlightSubstring(c.getContactName(), substr)
                + spaces.substring(0, 22 - c.getContactName().length());

        String tel = ANSI_WHITE + " tel: " + ANSI_RESET + c.getPhoneNumber()
                + spaces.substring(0, 16-c.getPhoneNumber().length());

        String address = ANSI_WHITE + " adr.: " + ANSI_RESET + c.getAdress();
        System.out.println(stIndex + name + tel + address);
    }

    public static String highlightSubstring (String str, String substr) {
        String result = "";
        String lowCaseStr = str.toLowerCase();
        String lowCaseSubstr = substr.toLowerCase();
        int length = substr.length();
        int iterator = 0;

        for (int i=0; i<str.length()-length+1; i++){
            String compareStr = lowCaseStr.substring(i,i+length);
            boolean iterationNeedCheck = false;

            if (i == 0) { iterationNeedCheck = true;
                } else if (str.charAt(i - 1) == ' ')
                { iterationNeedCheck = true; }

            if(compareStr.equals(lowCaseSubstr) && iterationNeedCheck){
                result = result + str.substring(iterator, i)+ANSI_YELLOW+str.substring(i, i+length)+ANSI_RESET;
                iterator = i+length;
                }
        }
        result = result + str.substring(iterator);
        return result;
    }

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void colorOff(){
        ANSI_RESET = "";
        ANSI_BLACK = "";
        ANSI_RED = "";
        ANSI_GREEN = "";
        ANSI_YELLOW = "";
        ANSI_BLUE = "";
        ANSI_PURPLE = "";
        ANSI_CYAN = "";
        ANSI_WHITE = "";
    }
}
