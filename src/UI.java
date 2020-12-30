import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UI {

    List<Contact> lista = new ArrayList<>();
    Scanner scan = new Scanner(System.in);
    Pattern pattern = Pattern.compile("[+]?(\\w+)?(\\s|[-])?([(]\\w{2,}[)])?((\\s|[-])\\w{2,})*");
    Matcher matcher;


    void start() {
        label:
        while (true) {
            System.out.println("Enter action (add, remove, edit, count, list, exit):");
            String input = scan.nextLine();

            switch (input) {
                case "add":
                    add();
                    break;
                case "remove":
                    if (!lista.isEmpty()) {
                        remove();
                    } else {
                        System.out.println("No records to remove");
                    }
                    break;
                case "edit":
                    if (!lista.isEmpty()) {
                        edit();
                    } else {
                        System.out.println("No records to edit");
                    }
                    break;
                case "count":
                    System.out.printf("the Phone book has %s records.\n", lista.size());
                    break;
                case "list":
                    printList();
                    break;
                case "exit":
                    break label;
                default:
                    System.out.println("Wrong input");
                    break;
            }
        }
    }

    private void printList() {
        int counter = 1;
        for (Contact contact : lista) {
            System.out.println(counter + ". " + contact);
            counter++;
        }
    }

    private void remove() {
        System.out.println("Select a record: ");
        int number = selectNumber();
        lista.remove(number);
        System.out.println("The record removed");
    }

    private void add() {
        System.out.println("Enter the name of the person:");
        String name = scan.nextLine();
        System.out.println("Enter the surname of the person:");
        String surname = scan.nextLine();
        System.out.println("Enter the number:");
        String phone = scan.nextLine();
        if (numberValidation(phone)) {
            lista.add(new ContactBuilder()
                    .setName(name)
                    .setSurname(surname)
                    .setNumber(phone)
                    .build());
        } else {
            System.out.println("Wrong number format!");
            lista.add(new ContactBuilder()
                    .setName(name)
                    .setSurname(surname)
                    .build());
        }
        System.out.println("The record added.");

    }

    private void edit() {
        printList();
        System.out.println("Select a record:");
        int number = selectNumber();

        System.out.println("Select a field (name, surname, number): ");
        String input = scan.nextLine();
        switch (input) {
            case "name":
                System.out.println("Enter the name: ");
                input = scan.nextLine();
                lista.get(number).setName(input);
                System.out.println("The record updated!");
                break;
            case "surname":
                System.out.println("Enter the surname: ");
                input = scan.nextLine();
                lista.get(number).setSurname(input);
                System.out.println("The record updated!");
                break;
            case "number":
                System.out.println("Enter the number: ");
                input = scan.nextLine();
                if (numberValidation(input)) {
                    lista.get(number).setNumber(input);
                    System.out.println("The record updated!");
                } else {
                    lista.get(number).setNumber("[no number]");
                    System.out.println("Wrong number format!");
                }
                break;
            default:
                System.out.println("invalid input");
                break;
        }

    }

    private boolean numberValidation(String phone) {
        matcher = pattern.matcher(phone);
        return matcher.matches();
    }

    private int selectNumber() {
        while (true) {
            int number = Integer.parseInt(scan.nextLine());
            number--;
            if (number < lista.size() && number >= 0) {
                return number;
            } else {
                System.out.println("Invalid number");
            }
        }
    }
}
