package io.github.plaguv.contracts.common.metadata;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.UUID;

class EventMetadataTest {

    @Test
    @DisplayName("Should throw if null parameter in constructor")
    void throwsOnNull() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new EventMetadata(null, null, null, null));
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new EventMetadata(UUID.randomUUID(), EventVersion.of(1), Instant.now(), null));
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new EventMetadata(UUID.randomUUID(), EventVersion.of(1), null, EventMetadataTest.class));
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new EventMetadata(UUID.randomUUID(), null, Instant.now(), EventMetadataTest.class));
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new EventMetadata(null, EventVersion.of(1), Instant.now(), EventMetadataTest.class));

        Assertions.assertThrows(IllegalArgumentException.class, () -> EventMetadata.builder().build());
    }
}