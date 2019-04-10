package ru.mail.polis;

import org.jetbrains.annotations.NotNull;

import java.nio.ByteBuffer;
import java.util.Objects;

/**
 * Record from {@link DAO}.
 *
 * @author Dmitry Schitinin <dmitry.schitinin@corp.mail.ru>
 */
public class Record implements Comparable<Record> {

    public static Record of(@NotNull ByteBuffer key,
                            @NotNull ByteBuffer value) {
        return new Record(key, value);
    }

    private final ByteBuffer key;
    private final ByteBuffer value;

    Record(@NotNull ByteBuffer key,
           @NotNull ByteBuffer value) {
        this.key = key;
        this.value = value;
    }

    public ByteBuffer getKey() {
        return key.asReadOnlyBuffer();
    }

    public ByteBuffer getValue() {
        return value.asReadOnlyBuffer();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Record record = (Record) o;
        return Objects.equals(key, record.key) &&
                Objects.equals(value, record.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(key, value);
    }

    @Override
    public int compareTo(@NotNull Record other) {
        return this.key.compareTo(other.key);
    }
}
