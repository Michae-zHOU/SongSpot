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
        switch (type) {
            case 0:
                return ARTIST;
            case 1:
                return CURATOR;
            case 2:
                return ADMIN;
            case 3:
                return SUPERUSER;
            default:
                throw new IllegalArgumentException();
        }
    }

    public int getUserType() {
        return this.value;
    }
}
