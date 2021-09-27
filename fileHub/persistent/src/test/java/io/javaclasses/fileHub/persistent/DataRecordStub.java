package io.javaclasses.fileHub.persistent;

public final class DataRecordStub implements DataRecord<RecordIdStub> {

    private final RecordIdStub id;
    private final String field;

    DataRecordStub(RecordIdStub id, String field) {

        this.id = id;

        this.field = field;
    }

    @Override
    public RecordIdStub id() {

        return id;
    }

    String field() {

        return field;
    }
}
