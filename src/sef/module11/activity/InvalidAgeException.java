package sef.module11.activity;


public class InvalidAgeException extends InvalidRecordException {
    public InvalidAgeException(int lineNumber, String rawLine, String message) {
        super(lineNumber, rawLine, message);
    }
}

