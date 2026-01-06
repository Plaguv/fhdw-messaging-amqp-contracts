package de.fhdw.contracts.common;

public record EventVersion (
        int major,
        int minor
){
    public static EventVersion v1() {
        return new EventVersion(1, 0);
    }

    public boolean isCompatibleWith(EventVersion other) {
        return this.major == other.major;
    }

    @Override
    public String toString() {
        return "%d.%d".formatted(major, minor);
    }
}
