public class Contact {
    private String name;
    private String surname;
    private String number;

    Contact(String name, String surname, String number){
        this.name = name;
        this.surname = surname;
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public boolean hasNumber(){
        return false;
    }
}
