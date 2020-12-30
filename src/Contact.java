public class Contact {
    private String name;
    private String surname;
    private String number;

    Contact(String name, String surname, String number) {
        this.name = name;
        this.surname = surname;
        this.number = number;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public boolean hasNumber() {
        return !this.number.equals("[no number]");
    }

    @Override
    public String toString() {
        return this.name + " " + this.surname + ", " + this.number;
    }
}
