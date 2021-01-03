import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {


    public static void main(String[] args) throws IOException, ClassNotFoundException {
        UI ui = new UI(args[0]);
        ui.start();
    }
}

        /*
        File file = new File(filename);
        Writer writer = new OutputStreamWriter(new FileOutputStream(file));

         */