public class ContactBuilder {
    String name;
    String surname;
    String number;
    String birthDate;
    String gender;
    String creationDateString;
    String editDateString;
    String address;
    CurrentTime time = new CurrentTime();


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
        if (this.number.equals("")) {
            this.number = "[no number]";
        }
        return this;
    }

    public ContactBuilder setBirthDate(String birthDate) {
        this.birthDate = birthDate;
        if (this.birthDate.equals("")) {
            this.birthDate = "[no data]";
        }
        return this;
    }

    public ContactBuilder setGender(String gender) {
        this.gender = gender;
        if (this.gender.equals("")) {
            this.gender = "[no data]";
        }
        return this;
    }

    public ContactBuilder setAddress(String address) {
        this.address = address;
        return this;
    }

    public ContactBuilder setCreationDate() {
        this.creationDateString = time.getTime();
        return this;
    }

    public ContactBuilder setEditDate() {
        this.editDateString = time.getTime();
        return this;
    }

    public People peopleBuild() {
        return new People(this);
    }
    public Organization organizationBuild(){
        return new Organization(this);
    }

}
