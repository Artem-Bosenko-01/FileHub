package io.javaclasses.fileHub.services.files;

import com.google.common.base.Preconditions;

/**
 * Types of file systems object.
 */
public enum ItemType {

    FILE("file"),
    FOLDER("folder");

    private final String type;

    ItemType(String type) {

        this.type = Preconditions.checkNotNull(type);
    }

    public String typeName() {
        return type;
    }
}
