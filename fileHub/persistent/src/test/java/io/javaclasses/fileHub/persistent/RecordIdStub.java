package io.javaclasses.fileHub.persistent;

public final class RecordIdStub implements RecordId {

    private final String id;

    RecordIdStub(String id) {

        this.id = id;
    }

    @Override
    public String value() {
        return id;
    }
}
