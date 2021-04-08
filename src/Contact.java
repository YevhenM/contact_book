public class Contact {

    private int contactId;
    private int userId;
    public String contactName;
    private String phoneNumber;
    private String adress;

    Contact(int contactId, int userId, String contactName, String phoneNumber, String address){
        this.contactId = contactId;
        this.userId = userId;
        this.contactName = contactName;
        this.phoneNumber = phoneNumber;
        this.adress = address;
    }

    public int getContactId() {
        return contactId;
    }

    public int getUserId() {
        return userId;
    }

    public String getContactName() {
        return contactName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getAdress() {
        return adress;
    }

    public void setContactId(int contactId) {
        this.contactId = contactId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

}
