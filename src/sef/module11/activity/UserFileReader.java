package sef.module11.activity;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class UserFileReader {

    public void loadUsers(Path filePath, UserRegistrationService service) throws FileReadException {
        int lineNumber = 0;

        // try-with-resources (auto close)
        try (BufferedReader reader = Files.newBufferedReader(filePath)) {
            String line;

            while ((line = reader.readLine()) != null) {
                lineNumber++;
                String rawLine = line;

                if (line.isBlank()) {
                    continue; // safely skip blank lines
                }

                try {
                    User user = parseUser(lineNumber, rawLine);
                    service.register(user, lineNumber, rawLine);
                } catch (InvalidRecordException e) {
                    // per-line error -> report and continue, program never crashes
                    System.out.println("[ERROR] " + e.getMessage());
                }
            }

        } catch (IOException e) {
            throw new FileReadException("Failed to read file: " + filePath, e);
        }
    }

    private User parseUser(int lineNumber, String rawLine) throws InvalidRecordException {
        String[] parts = rawLine.split(",", -1); // keep empty values

        if (parts.length != 3) {
            throw new InvalidRecordException(lineNumber, rawLine,
                    "Invalid format. Expected 3 values: id,email,age");
        }

        int id = parseInt(parts[0].trim(), lineNumber, rawLine, "id");
        String email = parts[1].trim();
        int age = parseInt(parts[2].trim(), lineNumber, rawLine, "age");

        return new User(id, email, age);
    }

    private int parseInt(String value, int lineNumber, String rawLine, String fieldName)
            throws InvalidRecordException {
        if (value.isEmpty()) {
            throw new InvalidRecordException(lineNumber, rawLine, "Missing " + fieldName);
        }
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            throw new InvalidRecordException(lineNumber, rawLine,
                    "Invalid number for " + fieldName + ": '" + value + "'");
        }
    }
}
