import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UI {

    List<Contact> contactList = new ArrayList<>();
    Scanner scan = new Scanner(System.in);
    String input;
    Pattern pattern = Pattern.compile("[+]?(\\w+)?(\\s|[-])?([(]\\w{2,}[)])?((\\s|[-])\\w{2,})*");
    Matcher matcher;


    void start() {
        System.out.println("Enter action (add, remove, edit, count, list, exit, info):");
        input = scan.nextLine();
        label:
        while (true) {
            switch (input) {
                case "add":
                    factory();
                    break;
                case "remove":
                    if (!contactList.isEmpty()) {
                        remove();
                    } else {
                        System.out.println("No records to remove");
                    }
                    break;
                case "edit":
                    if (!contactList.isEmpty()) {
                        edit();
                    } else {
                        System.out.println("No records to edit");
                    }
                    break;
                case "count":
                    System.out.printf("the Phone book has %s records.\n", contactList.size());
                    break;
                case "list":
                    printList();
                    break;
                case "info":
                    detailedPrint();
                    break;
                case "exit":
                    break label;
                default:
                    System.out.println("Wrong input");
                    break;
            }
            System.out.println("\nEnter action (add, remove, edit, count, list, exit, info):");
            input = scan.nextLine();
        }
    }

    private void printList() {
        int counter = 1;
        for (Contact contact : contactList) {
            System.out.println(counter + ". " + contact);
            counter++;
        }
    }

    private void remove() {
        System.out.println("Select a record: ");
        int number = selectNumber();
        contactList.remove(number);
        System.out.println("The record removed");
    }

    private void factory() {
        System.out.println("Enter the type (person, organization):");
        input = scan.nextLine();
        switch (input) {
            case "person":
                addPerson();
                break;
            case "organization":
                addOrganization();
                break;
            default:
                System.out.println("Wrong input");
                break;
        }
    }

    private void addPerson() {
        System.out.println("Enter the name of the person:");
        String name = scan.nextLine();
        System.out.println("Enter the surname of the person:");
        String surname = scan.nextLine();
        System.out.println("Enter the birth date:");
        String birthDate = scan.nextLine();
        if (birthDate.equals("")) {
            System.out.println("invalid birth date!");
        }
        System.out.println("Enter the gender (M, F):");
        String gender = scan.nextLine();
        if (!gender.equals("M") && !gender.equals("F")) {
            gender = "";
            System.out.println("invalid gender!");
        }
        System.out.println("Enter the number:");
        String phone = scan.nextLine();
        if (!numberValidation(phone)) {
            phone = "";
            System.out.println("invalid number!");
        }
        contactList.add(new ContactBuilder()
                .setName(name)
                .setSurname(surname)
                .setBirthDate(birthDate)
                .setGender(gender)
                .setNumber(phone)
                .setCreationDate()
                .setEditDate()
                .peopleBuild());
        System.out.println("The record added.");

    }

    private void addOrganization() {
        System.out.println("Enter organization name:");
        String name = scan.nextLine();
        System.out.println("Enter the address:");
        String address = scan.nextLine();
        System.out.println("Enter the number:");
        String phone = scan.nextLine();
        if (!numberValidation(phone)) {
            phone = "";
            System.out.println("invalid number!");
        }
        contactList.add(new ContactBuilder()
                .setName(name)
                .setAddress(address)
                .setNumber(phone)
                .setCreationDate()
                .setEditDate()
                .organizationBuild());
        System.out.println("The record added.");
    }

    private void edit() {
        printList();
        System.out.println("Select a record:");
        int number = selectNumber();

        if (contactList.get(number).isPerson()) {
            editPerson(number);
        } else {
            editOrganization(number);
        }
    }

    private void editPerson(int number) {
        System.out.println("Select a field (name, surname, birth, gender, number):");
        input = scan.nextLine();
        switch (input) {
            case "name":
                System.out.println("Enter the name: ");
                input = scan.nextLine();
                contactList.get(number).setName(input);
                System.out.println("The record updated!");
                contactList.get(number).updateLastEdited();
                break;
            case "surname":
                System.out.println("Enter the surname: ");
                input = scan.nextLine();
                ((People) contactList.get(number)).setSurname(input);
                System.out.println("The record updated!");
                contactList.get(number).updateLastEdited();
                break;
            case "gender":
                System.out.println("Enter gender (M, F): ");
                input = scan.nextLine();
                if (!input.equals("M") && !input.equals("F")) {
                    System.out.println("invalid gender!");
                } else {
                    ((People) contactList.get(number)).setGender(input);
                    System.out.println("The record updated!");
                    contactList.get(number).updateLastEdited();
                }
                break;
            case "birth":
                System.out.println("Enter birth date: ");
                input = scan.nextLine();
                if (!input.equals("")) {
                    System.out.println("invalid birth date!");
                } else {
                    ((People) contactList.get(number)).setBirthDate(input);
                    System.out.println("The record updated!");
                    contactList.get(number).updateLastEdited();
                }
                break;
            case "number":
                setContactNumber(number);
                break;
            default:
                System.out.println("invalid input");
                break;
        }
    }

    private void editOrganization(int number) {
        System.out.println("Select a field (name, address, number):");
        input = scan.nextLine();
        switch (input) {
            case "name":
                System.out.println("Enter organization name: ");
                input = scan.nextLine();
                contactList.get(number).setName(input);
                contactList.get(number).updateLastEdited();
                break;
            case "address":
                System.out.println("Enter organization address: ");
                input = scan.nextLine();
                ((Organization) contactList.get(number)).setAddress(input);
                contactList.get(number).updateLastEdited();
                break;
            case "number":
                setContactNumber(number);
                break;
        }
    }

    private void setContactNumber(int number) {
        System.out.println("Enter the number: ");
        input = scan.nextLine();
        if (numberValidation(input)) {
            contactList.get(number).setNumber(input);
            System.out.println("The record updated!");
            contactList.get(number).updateLastEdited();
        } else {
            System.out.println("Wrong number format!");
        }
    }

    private void detailedPrint() {
        printList();
        System.out.println("Select a record:");
        int number = selectNumber();
        System.out.println(contactList.get(number).detailedPrint());

    }

    private boolean numberValidation(String phone) {
        matcher = pattern.matcher(phone);
        return matcher.matches();
    }

    private int selectNumber() {
        while (true) {
            int number = Integer.parseInt(scan.nextLine());
            number--;
            if (number < contactList.size() && number >= 0) {
                return number;
            } else {
                System.out.println("Invalid number");
            }
        }
    }
}
