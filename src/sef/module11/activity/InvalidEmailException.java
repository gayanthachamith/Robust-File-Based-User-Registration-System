package sef.module11.activity;



public class InvalidEmailException extends InvalidRecordException {
    public InvalidEmailException(int lineNumber, String rawLine, String message) {
        super(lineNumber, rawLine, message);
    }
}

