package sef.module11.activity;

import java.nio.file.Path;

public class Main {
    public static void main(String[] args) {
        UserRegistrationService service = new UserRegistrationService();
        UserFileReader reader = new UserFileReader();

        Path filePath = Path.of("users.txt"); // keep users.txt in project root

        // try-catch-finally (never crash)
        try {
            reader.loadUsers(filePath, service);

            System.out.println("\n=== Loaded Users (" + service.count() + ") ===");
            service.getAllUsers().forEach(System.out::println);

        } catch (FileReadException exception) {
            System.out.println("[FATAL] " + exception.getMessage());
            if (exception.getCause() != null) {
                System.out.println("[CAUSE] " + exception.getCause().getClass().getSimpleName()
                        + ": " + exception.getCause().getMessage());
            }
        } finally {
            System.out.println("\n[INFO] Program finished safely (no crash).");
        }
    }
}
