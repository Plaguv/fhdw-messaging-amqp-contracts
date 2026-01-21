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

    private EventMetadata eventMetadata;
    private EventRouting eventRouting;
    private EventInstance eventInstance;

    @BeforeEach
    void beforeEach() {
        eventMetadata = new EventMetadata(
                new EventVersion(1),
                EventEnvelopeTest.class
        );
        eventRouting = new EventRouting(
                EventType.STORE_OPENED,
                EventDispatchType.DIRECT
        );
        eventInstance = new StoreClosedEvent(5L);
    }

    @Test
    @DisplayName("Should throw if null parameter in constructor")
    void throwsOnNull() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new EventEnvelope(null, null, null));
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new EventEnvelope(eventMetadata, eventRouting, null));
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new EventEnvelope(eventMetadata, null, eventInstance));
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new EventEnvelope(null, eventRouting, eventInstance));
    }

    @Test
    @DisplayName("Should throw if class mismatch is present")
    void throwsOnClassMismatch() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new EventEnvelope(eventMetadata, eventRouting, eventInstance));
    }

    @Test
    @DisplayName("Should construct if classes match")
    void constructsOnClassMatch() {
        Assertions.assertDoesNotThrow(
                () -> new EventEnvelope(eventMetadata, eventRouting, new StoreOpenedEvent(5L)));
    }
}