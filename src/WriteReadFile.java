import java.io.*;
import java.util.ArrayList;

public class WriteReadFile {
    File file;

    WriteReadFile(String file) {
        this.file = new File(file);
    }

    ArrayList<Contact> readFile() throws IOException, ClassNotFoundException {
        new FileOutputStream(file, true).close();
        FileInputStream fis = new FileInputStream(file);
        if(fis.available() != 0) {
            ObjectInputStream ois = new ObjectInputStream(fis);
            ArrayList<Contact> lista = (ArrayList<Contact>) ois.readObject(); //read object
            ois.close();
            return lista;
        }
        return new ArrayList<>();
    }

    void writeFile(ArrayList<Contact> contactsList) {
        try {
            FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(contactsList); //write object
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
