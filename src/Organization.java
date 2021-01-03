import java.io.Serializable;

public class Organization extends Contact implements Serializable {
    private static final long serialVersionUID = 3976583715976144232L;
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


    @Override
    public String detailedPrint() {
        return "Organization name: " + super.name + "\n"
                + "Address: " + this.address + "\n"
                + "Number: " + this.number + "\n"
                + "Time created: " + super.formattedDateTimeCreation + "\n"
                + "Time last edit: " + super.formattedDateTime;
    }

    @Override
    public String toString() {
        return super.name + " " + this.address;
    }

    //bad implementation, temporary
    @Override
    public boolean isPerson(){
        return false;
    }
}
