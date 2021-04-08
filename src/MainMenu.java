import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class MainMenu {

    public static List<Contact> contacts = new ArrayList<>();
    public static int contactId = 0;

    public static void menu(){

        do {
            Print.blue(Authorisation.authUser+" >");
            String answer = Input.oneLetter(
              "Enter: [F]ind contact | [C]ontact List | [A]dd contact | [L]ogout | [Q]uit program:",
              "F, C, A, L, Q");

            switch (answer) {
                case "F" : findContacts(); break;
                case "C" : getContactList(); break;
                case "A" : addContact(); break;
                case "L" : Authorisation.logout(); break;
                case "Q" : Authorisation.bye(); break;
            }
        } while(Authorisation.isAuth);
    }

    public static void addContact(){
        String contactName;
        String phoneNumber;
        String adress;

        Print.ln("________________ Add contact ________________");
        contactName = Input.someText("Type contact name: ");
        phoneNumber = Input.someText("Type phone number: ");
        adress = Input.someText("Type adress: ");

        Contact newContact = new Contact(contactId, Authorisation.userId, contactName, phoneNumber, adress);

        contacts.add(newContact);
        contactId++;
        Repository.addContact(newContact);
        getContactList();
    }

    public static void getContactList (){
        AtomicInteger index = new AtomicInteger(1);

        List<SelectedContact> selectList = contacts.stream()
                .filter(p->p.getUserId()==Authorisation.userId)
                .sorted(Comparator.comparing(Contact::getContactName))
                .map(p-> new SelectedContact(
                        index.getAndIncrement(),
                        p.getContactId(),
                        p.getUserId(),
                        p.getContactName(),
                        p.getPhoneNumber(),
                        p.getAdress()))
                .collect(Collectors.toList());
        outList(selectList, "");
    }

    public static void findContacts (){
        String str = Input.someText("Find: ");
        AtomicInteger index = new AtomicInteger(1);

        List<SelectedContact> selectList = contacts.stream()
                .filter(p->p.getUserId()==Authorisation.userId)
                .filter(p-> (" " + p.getContactName()).toLowerCase().contains((" " + str).toLowerCase()))
                .sorted(Comparator.comparing(Contact::getContactName))
                .map(p-> new SelectedContact(
                        index.getAndIncrement(),
                        p.getContactId(),
                        p.getUserId(),
                        p.getContactName(),
                        p.getPhoneNumber(),
                        p.getAdress()))
                .collect(Collectors.toList());
        outList(selectList, str);
    }

    public static void outList (List <SelectedContact> selContactList, String substr){
        if (selContactList.size()==0) {
            Print.yellow("<!> No contacts found");
            return; }
        if (selContactList.size()==1) {
            contactHandle(selContactList.get(0));
            return;
        }

        LetterIndex a;
        do{
            Print.clearScreen();
            Print.blue(Authorisation.authUser+" > Contact list >");
            Print.ln("-----------------------------------------------------------------------------------");
            Print.emptyln();
            selContactList.forEach(s -> Print.printContact(s, substr));
            Print.emptyln();
            a = Input.letterOrIndex(
                    "Enter contact number [ 1 - " + selContactList.size() + " ] or [B]ack: ",
                    "B", 1, selContactList.size());
            if (a.index>0) { contactHandle( selContactList.get(a.index-1) );}
        } while ( !a.letter.equals("B") );
    }

    public static void contactHandle(SelectedContact contact){

        String answer;
        do {
            Print.emptyln();
            Print.blue(Authorisation.authUser+" > Contact list > Selected contact >");
            Print.purple("___________________________________________________________________________________");
            Print.printContact(contact, "");
            Print.purple("___________________________________________________________________________________");
            answer = Input.oneLetter("Enter: [E]dit contact | [D]elete contact | [B]ack:", "E, D, B");

            switch (answer) {
                case "E" : editContact(contact.getContactId()); break;
                case "D" : deleteContact(contact.getContactId()); break;
                case "M" : break;
            }
        } while(!answer.equals("B"));
    }

    public static void deleteContact(int index){
        String answer;
        do {
            answer = Input.oneLetter("Are you sure? You want to delete this contact? [Y]/[N]", "Y, N");
            switch (answer) {
                case "Y" : delete(index); answer = "N"; break;
                case "N" : break;
            }
        } while(!answer.equals("N"));
    }

    public static void editContact(int index){
        Contact toEdit = contacts.stream().filter(c-> c.getContactId()==index).findFirst().orElse(null);
        if (toEdit != null) {
            toEdit.setContactName(Input.someText
                    ("Name [ " + Print.ANSI_YELLOW + toEdit.getContactName() + Print.ANSI_RESET + " ] update to> "));
            toEdit.setPhoneNumber(Input.someText
                    ("Phone [ " + Print.ANSI_YELLOW + toEdit.getPhoneNumber() + Print.ANSI_RESET + " ] update to> "));
            toEdit.setAdress(Input.someText
                    ("Address [ " + Print.ANSI_YELLOW + toEdit.getAdress() + Print.ANSI_RESET + " ] update to> "));
            Repository.updateContact(toEdit);
            menu();
        }
    }
    public static void delete(int index){
        Contact toDel = contacts.stream().filter(c-> c.getContactId()==index).findFirst().orElse(null);
        contacts.remove(toDel);
        Repository.deleteContact(toDel);
        menu();
    }
}
