import java.io.Serializable;

public class People extends Contact implements Serializable {
    private static final long serialVersionUID = 3976583715976144232L;
    private String surname;
    private String birthDate;
    private String gender;

    People(ContactBuilder builder) {
        super.name = builder.name;
        this.surname = builder.surname;
        this.gender = builder.gender;
        this.birthDate = builder.birthDate;
        super.number = builder.number;
        super.formattedDateTime = builder.editDateString;
        super.formattedDateTimeCreation = builder.creationDateString;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public String detailedPrint() {
        return "Name: " + super.name + "\n"
                + "Surname: " + this.surname + "\n"
                + "Birth date: " + this.birthDate + "\n"
                + "Gender: " + this.gender + "\n"
                + "Number: " + super.number + "\n"
                + "Time created: " + super.formattedDateTimeCreation + "\n"
                + "Time last edit: " + super.formattedDateTime;
    }

    @Override
    public String toString() {
        return super.name + " " + this.surname;
    }

    @Override
    public boolean isPerson(){
        return true;
    }
}
