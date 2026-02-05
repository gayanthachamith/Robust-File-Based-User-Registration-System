package sef.module11.activity;

import java.util.*;

public class UserRegistrationService {
    private final Map<Integer, User> usersById = new HashMap<>();

    public void register(User user, int lineNumber, String rawLine)
            throws InvalidEmailException, InvalidAgeException {

        validateEmail(user.getEmail(), lineNumber, rawLine);
        validateAge(user.getAge(), lineNumber, rawLine);

        if (usersById.containsKey(user.getId())) {
            // If you want, we can create DuplicateIdException too.
            throw new InvalidAgeException(lineNumber, rawLine, "Duplicate ID: " + user.getId());
        }

        usersById.put(user.getId(), user);
    }

    private void validateEmail(String email, int lineNumber, String rawLine) throws InvalidEmailException {
        if (email == null || email.trim().isEmpty()) {
            throw new InvalidEmailException(lineNumber, rawLine, "Email must not be empty");
        }
        if (!email.contains("@")) {
            throw new InvalidEmailException(lineNumber, rawLine, "Email must contain '@'");
        }
    }

    private void validateAge(int age, int lineNumber, String rawLine) throws InvalidAgeException {
        if (age < 18) {
            throw new InvalidAgeException(lineNumber, rawLine, "Age must be >= 18 (got " + age + ")");
        }
    }

    public Collection<User> getAllUsers() {
        return Collections.unmodifiableCollection(usersById.values());
    }

    public int count() {
        return usersById.size();
    }
}
