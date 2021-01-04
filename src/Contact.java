import java.io.Serializable;

public class Contact implements Serializable {

    private static final long serialVersionUID = 3976583715976144232L;
    String number;
    String name;
    String formattedDateTime;
    String formattedDateTimeCreation;
    CurrentTime time = new CurrentTime();

    public void setNumber(String number) {
        this.number = number;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void updateLastEdited() {
        this.formattedDateTime = time.getTime();
    }

    protected String detailedPrint(){
        return "undefined";
    }

    protected boolean isPerson(){
        return false;
    }

    protected String searchPrint(){
        return "error";
    }
}