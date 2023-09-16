package com.syntaxgenie.azurestorage.external.storage;

public enum StoragePath {
    PROFILE_IMAGES("profile_images/");

    private final String value;

    StoragePath(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
