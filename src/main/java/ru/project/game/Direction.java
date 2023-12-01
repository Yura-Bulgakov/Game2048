package ru.project.game;

public enum Direction {
    RIGHT("Право"),
    LEFT("Лево"),
    UP("Верх"),
    DOWN("Низ"),
    ;

    private final String description;

    Direction(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
