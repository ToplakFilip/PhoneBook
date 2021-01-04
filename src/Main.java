import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        if (args.length == 0) {
            UI ui = new UI("none");
            ui.start();
        } else {
            UI ui = new UI(args[0]);
            ui.start();
        }
    }
}