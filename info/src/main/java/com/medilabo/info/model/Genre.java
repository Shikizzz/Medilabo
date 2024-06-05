package com.medilabo.info.model;

public enum Genre {
    MALE("M"), FEMALE("F"), OTHER("O"), UNREADABLE("U");

    private String code;

    private Genre(String code) {
        this.code = code;
    }

    public String getGenre() {
        return code;
    }
}

