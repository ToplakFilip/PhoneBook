public class People extends Contact {
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

    public String detailedPrint() {
        return super.name + "\n"
                + this.surname + "\n"
                + this.birthDate + "\n"
                + this.gender + "\n"
                + super.number + "\n"
                + "Time created: " + super.formattedDateTimeCreation + "\n"
                + "Time last edited: " + super.formattedDateTime;
    }

    @Override
    public String toString() {
        return super.name + " " + this.surname;
    }
}
