public class ContactBuilder {
    String name;
    String surname;
    String number = "[no number]";

    public ContactBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public ContactBuilder setSurname(String surname) {
        this.surname = surname;
        return this;
    }

    public ContactBuilder setNumber(String number) {
        this.number = number;
        return this;
    }

    public Contact build(){
        return new Contact(this);
    }

}
