import java.io.*;
import java.util.Scanner;

public class NotesApp {

    private static final String NOTES_FILE = "notes.txt";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;
       
        do {
            System.out.println("\n--- Simple Notes App ---");
            System.out.println("1. Add Note");
            System.out.println("2. View Notes");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    addNote(scanner);
                    break;
                case 2:
                    viewNotes();
                    break;
                case 3:
                    System.out.println("Exiting Notes App. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        } while (choice != 3);
        
        scanner.close();
    }

    private static void addNote(Scanner scanner) {
        System.out.print("Enter your note: ");
        String note = scanner.nextLine();
        // Using try-with-resources. FileWriter in append mode.
        try (FileWriter fw = new FileWriter(NOTES_FILE, true);
             BufferedWriter bw = new BufferedWriter(fw)) {
            bw.write(note);
            bw.newLine();
            System.out.println("Note added successfully.");
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void viewNotes() {
        System.out.println("--- All Notes ---");
        // Using try-with-resources for safe auto-close
        try (FileReader fr = new FileReader(NOTES_FILE);
             BufferedReader br = new BufferedReader(fr)) {
            String line;
            int count = 1;
            while ((line = br.readLine()) != null) {
                System.out.println(count + ". " + line);
                count++;
            }
            if (count == 1) {
                System.out.println("[No notes found]");
            }
        } catch (FileNotFoundException e) {
            System.out.println("[No notes yet. Add one first!]");
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
