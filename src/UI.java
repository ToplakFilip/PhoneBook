import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UI {

    List<Contact> lista = new ArrayList<>();

    void start() {
        while (true) {
            Scanner scan = new Scanner(System.in);
            System.out.println("Enter action (add, remove, edit, count, list, exit):");
            String input = scan.nextLine();

            if (input.equals("add")) {

            } else if (input.equals("remove")) {

            } else if (input.equals("edit")) {

            } else if (input.equals("count")) {
                System.out.printf("the Phone book has %s records.\n", lista.size());

            } else if (input.equals("list")) {
                for (Contact contact : lista) {
                    System.out.println(contact);
                }

            } else if (input.equals("exit")) {
                break;
            } else {
                System.out.println("Wrong input");
            }
        }

    }
}
