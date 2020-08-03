package com.songspot.server.controller.model;

public enum UserType {
    ARTIST(0),
    CURATOR(1),
    ADMIN(2),
    SUPERUSER(3);

    private final int value;

    UserType(int value) {
        this.value = value;
    }

    public static UserType getType(int type) {
        return switch (type) {
            case 0 -> ARTIST;
            case 1 -> CURATOR;
            case 2 -> ADMIN;
            case 3 -> SUPERUSER;
            default -> throw new IllegalArgumentException("No valid user type found");
        };
    }

    public static UserType getType(String type) {
        return switch (type.toLowerCase()) {
            case "artist" -> ARTIST;
            case "curator" -> CURATOR;
            case "admin" -> ADMIN;
            case "superuser" -> SUPERUSER;
            default -> throw new IllegalArgumentException("No valid user type found");
        };
    }

    public int getUserTypeValue() {
        return this.value;
    }
}
