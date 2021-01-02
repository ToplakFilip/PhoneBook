public class Organization extends Contact {
    private String address;

    Organization(ContactBuilder builder) {
        super.name = builder.name;
        this.address = builder.address;
        super.number = builder.number;
        super.formattedDateTime = builder.editDateString;
        super.formattedDateTimeCreation = builder.creationDateString;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setNumber(String number) {
        this.number = number;
    }


    public String detailedPrint() {
        return super.name + "\n"
                + this.address + "\n"
                + this.number + "\n"
                + "Time created: " + super.formattedDateTimeCreation + "\n"
                + "Time last edited: " + super.formattedDateTime;
    }

    public String toString() {
        return super.name + " " + this.address;
    }
}
