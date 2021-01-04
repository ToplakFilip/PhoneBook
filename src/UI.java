import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UI {

    ArrayList<Contact> contactList;
    Scanner scan = new Scanner(System.in);
    String input;
    Pattern patternPhone = Pattern.compile("[+]?(\\w+)?(\\s|[-])?([(]\\w{2,}[)])?((\\s|[-])\\w{2,})*");
    Pattern patternNumber = Pattern.compile("[0-9]+");
    Matcher matcher;
    WriteReadFile wrFile;
    InvertedIndexes contactSearch = new InvertedIndexes();
    boolean modified = true;
    boolean hasFile = true;

    UI(String filename) throws IOException, ClassNotFoundException {
        if(filename.equals("none")){
            contactList = new ArrayList<>();
            hasFile = false;
        }else{
            wrFile = new WriteReadFile(filename);
            contactList = wrFile.readFile();
        }
    }

    void start() {
        label:
        while (true) {
            System.out.println("\n[menu] ENTER INPUT - add | list | search | count | exit :");
            input = scan.nextLine();
            switch (input) {
                case "add":
                    factory();
                    break;
                case "count":
                    System.out.printf("the Phone book has %s records.\n", contactList.size());
                    break;
                case "list":
                    printList();
                    listMenu();
                    break;
                case "search":
                    searchMenu();
                    break;
                case "exit":
                    if(hasFile) {
                        wrFile.writeFile(contactList);
                    }
                    break label;
                default:
                    System.out.println("Wrong input");
                    break;
            }
        }
    }

    void searchMenu() {
        searchFor();
        while (true) {
            System.out.println("\n[search] ENTER INPUT - [input number] | again | back :");
            input = scan.nextLine();
            matcher = patternNumber.matcher(input);
            if (matcher.matches()) {
                int number = Integer.parseInt(input);
                number--;
                if (number < contactList.size() && number >= 0) {
                    System.out.println(contactList.get(number).detailedPrint());
                    recordMenu(number);
                    break;
                } else {
                    System.out.println("Entry number " + ++number + " does not exist");
                }
            } else if (input.equals("back")) {
                break;
            } else if (input.equals("again")) {
                searchFor();
            } else {
                System.out.println("Invalid input");
            }
        }
    }

    void recordMenu(int number) {
        while (!input.equals("menu") && !input.equals("delete")) {
            System.out.println("\n[record] ENTER INPUT - | edit | delete | menu :");
            input = scan.nextLine();
            matcher = patternNumber.matcher(input);
            switch (input) {
                case "edit":
                    edit(number);
                    break;
                case "delete":
                    remove(number);
                    break;
                case "menu":
                    break;
                default:
                    System.out.println("Invalid input");
                    break;
            }
        }

    }

    void listMenu(){
        while(true){
            System.out.println("\n[list] ENTER INPUT - [number] |  back :");
            input = scan.nextLine();
            matcher = patternNumber.matcher(input);
            if (matcher.matches()) {
                int number = Integer.parseInt(input);
                number--;
                if (number < contactList.size() && number >= 0) {
                    System.out.println(contactList.get(number).detailedPrint());
                    recordMenu(number);
                    break;
                } else {
                    System.out.println("Entry number " + ++number + " does not exist");
                }
            }else if(input.equals("back")){
                break;
            }else{
                System.out.println("Invalid input");
            }

        }
    }

    private void printList() {
        int counter = 1;
        for (Contact contact : contactList) {
            System.out.println(counter + ". " + contact);
            counter++;
        }
    }

    private void remove(int number) {
        contactList.remove(number);
        System.out.println("The record removed");
        this.modified = true;
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
        this.modified = true;

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
        this.modified = true;
    }

    private void edit(int number) {
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
                editFinish(number);
                break;
            case "surname":
                System.out.println("Enter the surname: ");
                input = scan.nextLine();
                ((People) contactList.get(number)).setSurname(input);
                editFinish(number);
                break;
            case "gender":
                System.out.println("Enter gender (M, F): ");
                input = scan.nextLine();
                if (!input.equals("M") && !input.equals("F")) {
                    System.out.println("invalid gender!");
                } else {
                    ((People) contactList.get(number)).setGender(input);
                    editFinish(number);
                }
                break;
            case "birth":
                System.out.println("Enter birth date: ");
                input = scan.nextLine();
                if (input.equals("")) {
                    System.out.println("invalid birth date!");
                } else {
                    ((People) contactList.get(number)).setBirthDate(input);
                    editFinish(number);
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
                editFinish(number);
                break;
            case "address":
                System.out.println("Enter organization address: ");
                input = scan.nextLine();
                ((Organization) contactList.get(number)).setAddress(input);
                editFinish(number);
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
            editFinish(number);
        } else {
            System.out.println("Wrong number format!");
        }
    }

    private void editFinish(int number) {
        System.out.println("The record updated!");
        contactList.get(number).updateLastEdited();
        this.modified = true;
    }

    private boolean numberValidation(String phone) {
        matcher = patternPhone.matcher(phone);
        return matcher.matches();
    }

    private void searchFor() {
        if (contactList.isEmpty()) {
            System.out.println("Empty contact list");
        } else if (modified) {
            contactSearch.invertedIndexing(contactList);
            modified = false;
            System.out.println("Enter search query: ");
            this.input = scan.nextLine();
            contactSearch.findWords(input, contactList);
        } else {
            System.out.println("Enter search query: ");
            this.input = scan.nextLine();
            contactSearch.findWords(input, contactList);
        }
    }
}
