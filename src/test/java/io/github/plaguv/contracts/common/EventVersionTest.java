package io.github.plaguv.contracts.common;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class EventVersionTest {

    @Test
    @DisplayName("Constructor should reject negative major, minor, patch")
    void constructorRejectsNegativeNumbers() {
        assertThrows(IllegalArgumentException.class, () -> new EventVersion(-1, 0, 0));
        assertThrows(IllegalArgumentException.class, () -> new EventVersion(0, -1, 0));
        assertThrows(IllegalArgumentException.class, () -> new EventVersion(0, 0, -1));
    }

    @Test
    @DisplayName("Static 'of' methods should create correct versions")
    void ofMethodsWork() {
        EventVersion v1 = EventVersion.of(1);
        assertEquals(1, v1.major());
        assertEquals(0, v1.minor());
        assertEquals(0, v1.patch());

        EventVersion v2 = EventVersion.of(1, 2);
        assertEquals(1, v2.major());
        assertEquals(2, v2.minor());
        assertEquals(0, v2.patch());

        EventVersion v3 = EventVersion.of(1, 2, 3);
        assertEquals(1, v3.major());
        assertEquals(2, v3.minor());
        assertEquals(3, v3.patch());

        EventVersion version = EventVersion.of("1.2.3");
        assertEquals(1, version.major());
        assertEquals(2, version.minor());
        assertEquals(3, version.patch());
    }

    @Test
    @DisplayName("Static 'of(String)' should reject null, empty, or malformed strings")
    void ofStringRejectsInvalidInput() {
        assertThrows(IllegalArgumentException.class, () -> EventVersion.of(null));
        assertThrows(IllegalArgumentException.class, () -> EventVersion.of(""));
        assertThrows(IllegalArgumentException.class, () -> EventVersion.of("1.2"));
        assertThrows(IllegalArgumentException.class, () -> EventVersion.of("1.2.3.4"));
        assertThrows(IllegalArgumentException.class, () -> EventVersion.of("1.b.c"));
        assertThrows(IllegalArgumentException.class, () -> EventVersion.of("a.1.c"));
        assertThrows(IllegalArgumentException.class, () -> EventVersion.of("a.b.1"));
        assertThrows(IllegalArgumentException.class, () -> EventVersion.of("a.b.c"));
    }
}