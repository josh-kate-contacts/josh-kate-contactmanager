import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class contactManager {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {


        System.out.println("1. View contacts.\n" +
                "2. Add a new contact.\n" +
                "3. Search a contact by name.\n" +
                "4. Delete an existing contact.\n" +
                "5. Exit.\n" +
                "Enter an option (1, 2, 3, 4 or 5):");


        switch (scanner.nextLine()) {
            case "1":
                view();
                break;
            case "2":
                add();
                break;
            case "3":
                search();
                break;
            case "4":
                delete();
                break;
            case "5":
                exit();
                break;
        }

    }

    public static void view() throws IOException {
        List<String> lines = Files.readAllLines(Paths.get("contacts.txt"));
        String[] bucket;

        for (String line : lines) {
            bucket = line.split(" ");
            System.out.printf("%s %s | %s", bucket[0], bucket[1], bucket[2]);
            System.out.println();
        }
    }

    public static void add() throws IOException {
        System.out.println("Add contact (FirstName LastName Number): ");
        String bucket = scanner.nextLine();
        Path filepath = Paths.get("contacts.txt");
        Files.write(filepath,
                Collections.singleton(bucket),
                StandardOpenOption.APPEND);
    }

    public static void search() throws IOException {
        List<String> lines = Files.readAllLines(Paths.get("contacts.txt"));
        System.out.println("Search a contact name:");
        String bucket = scanner.nextLine().strip();
        for (String line : lines) {
            if (line.contains(bucket)) {
                System.out.printf("%s", line);
            }
        }
    }

    public static void delete() throws IOException {
        List<String> lines = Files.readAllLines(Paths.get("contacts.txt"));

        System.out.println("Enter name of contact to delete: ");
        String bucket = scanner.nextLine().strip();

        lines.removeIf(line -> line.contains(bucket));

        Files.write(Paths.get("contacts.txt"),
                lines);
    }

    public static void exit() {

    }


}
