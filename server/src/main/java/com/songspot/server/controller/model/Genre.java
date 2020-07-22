package com.songspot.server.controller.model;

public enum Genre {
    HOUSE(0),
    HARDSTYLE(1),
    LOFI(2),
    DUBSTEP(3),
    HIPHOP(4),
    CUSTOM(5);

    private final int value;

    Genre(int value) {
        this.value = value;
    }

    public static Genre getType(int type) {
        return switch (type) {
            case 0 -> HOUSE;
            case 1 -> HARDSTYLE;
            case 2 -> LOFI;
            case 3 -> DUBSTEP;
            case 4 -> HIPHOP;
            case 5 -> CUSTOM;
            default -> throw new IllegalArgumentException();
        };
    }

    public int getGenre() {
        return this.value;
    }

}
