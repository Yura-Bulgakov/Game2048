package ru.project.board;

public class NotEnoughSpace extends Exception {
    public NotEnoughSpace() {
    }

    public NotEnoughSpace(String message) {
        super(message);
    }

    public NotEnoughSpace(String message, Throwable cause) {
        super(message, cause);
    }
}
