package io.github.plaguv.contracts.event.common;

public record EventVersion(
        int major,
        int minor,
        int patch
) {
    public EventVersion {
        if (major < 0) {
            throw new IllegalArgumentException("Major version cannot be negative, was '%d'".formatted(major));
        }
        if (minor < 0) {
            throw new IllegalArgumentException("Minor version cannot be negative, was '%d'".formatted(minor));
        }
        if (patch < 0) {
            throw new IllegalArgumentException("Patch version cannot be negative, was '%d'".formatted(patch));
        }
    }

    public static EventVersion of(int major) {
        return new EventVersion(major, 0, 0);
    }

    public static EventVersion of(int major, int minor) {
        return new EventVersion(major, minor, 0);
    }

    public static EventVersion of(int major, int minor, int patch) {
        return new EventVersion(major, minor, patch);
    }

    public static EventVersion of(String version) {
        if (version == null || version.isEmpty()) {
            throw new IllegalArgumentException("Version cannot be null or empty");
        }

        String[] versions = version.split("\\.");
        if (versions.length != 3) {
            throw new IllegalArgumentException("Invalid version format. A version should be in the numerical format of 'MAJOR.MINOR.PATCH'");
        }

        try {
            return new EventVersion(
                    Integer.parseInt(versions[0]),
                    Integer.parseInt(versions[1]),
                    Integer.parseInt(versions[2])
            );
        } catch (NumberFormatException numberFormatException) {
            throw new IllegalArgumentException("Version contains non-numeric characters");
        }
    }

    @Override
    public String toString() {
        return "%d.%d.%d".formatted(major, minor, patch);
    }
}