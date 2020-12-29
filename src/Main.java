import java.util.Scanner;

public class Main {

    public static void main(String[] args){

        Scanner scan = new Scanner(System.in);
        System.out.println("Enter the name of the person:");
        String name = scan.nextLine();
        System.out.println("Enter the surname of the person:");
        String surname = scan.nextLine();
        System.out.println("Enter the number:");
        String phone = scan.nextLine();

        Contact contact = new Contact(name, surname, phone);
        System.out.println("A record created!");
        System.out.println("A Phone Book with a single record created!");

    }
}

