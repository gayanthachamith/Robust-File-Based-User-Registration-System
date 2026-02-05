package sef.module11.activity;


public class InvalidRecordException extends RegistrationException {
    private final int lineNumber;
    private final String rawLine;

    public InvalidRecordException(int lineNumber, String rawLine, String message) {
        super("Line " + lineNumber + ": " + message + " | Raw: [" + rawLine + "]");
        this.lineNumber = lineNumber;
        this.rawLine = rawLine;
    }

    public int getLineNumber() { return lineNumber; }
    public String getRawLine() { return rawLine; }
}
