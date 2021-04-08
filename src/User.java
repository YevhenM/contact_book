public class User {
    private int id;
    private String name;
    private String password;

    User(int id, String name, String password){
        this.id = id;
        this.name = name;
        this.password = password;
    }

    public String getName(){
        return name;
    }

    public String getPassword(){
        return password;
    }

    public int getId(){
        return id;
    }

    public void setName(String name){
        this.name = name;
    }
}
