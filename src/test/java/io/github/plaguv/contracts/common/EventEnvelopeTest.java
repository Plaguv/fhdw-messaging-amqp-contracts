package io.github.plaguv.contracts.common;

import io.github.plaguv.contracts.common.metadata.EventMetadata;
import io.github.plaguv.contracts.common.metadata.EventVersion;
import io.github.plaguv.contracts.common.routing.EventDispatchType;
import io.github.plaguv.contracts.common.routing.EventRouting;
import io.github.plaguv.contracts.common.routing.EventType;
import io.github.plaguv.contracts.event.pos.store.StoreClosedEvent;
import io.github.plaguv.contracts.event.pos.store.StoreOpenedEvent;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class EventEnvelopeTest {

    private EventMetadata metadata;
    private EventRouting routing;
    private EventInstance payload;

    @BeforeEach
    void beforeEach() {
        metadata = new EventMetadata(
                new EventVersion(1),
                EventEnvelopeTest.class
        );
        routing = new EventRouting(
                EventType.STORE_OPENED,
                EventDispatchType.DIRECT
        );
        payload = new StoreOpenedEvent(5L);
    }

    @Test
    @DisplayName("Should throw if null parameter in constructor")
    void throwsOnNull() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new EventEnvelope(null, null, null));
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new EventEnvelope(metadata, routing, null));
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new EventEnvelope(metadata, null, payload));
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new EventEnvelope(null, routing, payload));
    }

    @Test
    @DisplayName("Should throw if class mismatch is present")
    void throwsOnClassMismatch() {
        payload = new StoreClosedEvent(5L);
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new EventEnvelope(metadata, routing, payload));
    }

    @Test
    @DisplayName("Should construct if classes match")
    void constructsOnClassMatch() {
        Assertions.assertDoesNotThrow(
                () -> new EventEnvelope(metadata, routing, new StoreOpenedEvent(5L)));
    }

    @Test
    @DisplayName("Should throw if null parameter in builder")
    void builderThrowsOnNull() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> EventEnvelope.builder()
                        .build());
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> EventEnvelope.builder()
                        .ofMetadata(metadata)
                        .ofRouting(routing)
                        .ofMetadata(null)
                        .build());
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> EventEnvelope.builder()
                        .ofMetadata(metadata)
                        .ofRouting(null)
                        .ofMetadata(metadata)
                        .build());
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> EventEnvelope.builder()
                        .ofMetadata(null)
                        .ofRouting(routing)
                        .ofMetadata(metadata)
                        .build());
    }

    @Test
    @DisplayName("Builder should keep given values")
    void builderConstructsWithGivenValues() {
        EventEnvelope eventEnvelope = EventEnvelope.builder()
                    .ofMetadata(metadata)
                    .ofRouting(routing)
                    .ofPayload(payload)
                    .build();

        Assertions.assertEquals(metadata, eventEnvelope.metadata());
        Assertions.assertEquals(routing, eventEnvelope.routing());
        Assertions.assertEquals(payload, eventEnvelope.payload());
    }
}