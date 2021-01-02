import java.time.LocalDateTime;

public class Contact {

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
}

