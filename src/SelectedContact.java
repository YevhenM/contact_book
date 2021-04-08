public class SelectedContact extends Contact{

    private int listIndex;

    SelectedContact(int listIndex, int contactId, int userId, String contactName, String phoneNumber, String address) {
        super(contactId, userId, contactName, phoneNumber, address);
        this.listIndex = listIndex;
    }
    public int getListIndex() {
        return listIndex;
    }


}
