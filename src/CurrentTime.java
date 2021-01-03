import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CurrentTime implements Serializable {

    private static final long serialVersionUID = 3976583715976144232L;
    LocalDateTime now;
    String formattedDateTime;
    private final transient DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;

    String getTime(){
        this.now = LocalDateTime.now();
        return formattedDateTime = now.format(formatter);
    }
}
