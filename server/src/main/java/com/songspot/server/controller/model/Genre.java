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
        switch (type) {
            case 0:
                return HOUSE;
            case 1:
                return HARDSTYLE;
            case 2:
                return LOFI;
            case 3:
                return DUBSTEP;
            case 4:
                return HIPHOP;
            case 5:
                return CUSTOM;
            default:
                throw new IllegalArgumentException();
        }
    }

    public int getGenre() {
        return this.value;
    }

}
