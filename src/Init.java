public abstract class Init {
    private static boolean isInit = false;


    public static void init() {
        if (isInit) { return; }
        else {
            boolean dataSourceSelected = false;
            do {
                String answer = Input.oneLetter("Select data source [T]ext file | [D]ata base", "T, D");
                switch (answer) {
                    case "T" : Repository.setUseDataBase(false); dataSourceSelected = true; break;
                    case "D" : Repository.setUseDataBase(true); dataSourceSelected = true; break;
                }
            } while(!dataSourceSelected);

            Print.white("Initialisation ! Work with data base = " + Repository.isUseDataBase());
            Repository.readUsers();
            Repository.readContacts();
            if (System.console() != null) {
                Print.colorOff();
            }
            isInit = true;
        }
    }
}
