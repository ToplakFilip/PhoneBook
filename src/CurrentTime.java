import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CurrentTime {

    LocalDateTime now;
    String formattedDateTime;
    private final DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;

    String getTime(){
        this.now = LocalDateTime.now();
        return formattedDateTime = now.format(formatter);
    }
}
