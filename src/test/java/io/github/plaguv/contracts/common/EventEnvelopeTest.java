package io.github.plaguv.contracts.common;

import io.github.plaguv.contracts.event.pos.store.StoreClosedEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class EventEnvelopeTest {

    private EventMetadata eventMetadata;
    private StoreClosedEvent eventPayload;

    @BeforeEach
    void beforeEach() {
        eventMetadata = EventMetadata
                .builder()
                .withEventScope(EventScope.INTERNAL)
                .withEventType(EventType.STORE_CLOSED)
                .withEventVersion(EventVersion.of(1))
                .withProducer(EventEnvelopeTest.class)
                .build();
        eventPayload = new StoreClosedEvent(1L);
    }

    @Test
    @DisplayName("Should throw if either parameter is null")
    void throwsOnNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            new EventEnvelope<>(null, null);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            new EventEnvelope<>(eventMetadata, null);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            new EventEnvelope<>(null, eventPayload);
        });
    }

    @Test
    @DisplayName("Should throw on mismatching classes in the metadata and payload")
    void throwsOnMismatchClass() {
        eventMetadata = EventMetadata
                .builder()
                .withEventScope(EventScope.INTERNAL)
                .withEventType(EventType.STORE_OPENED) // Intentional mismatch
                .withEventVersion(EventVersion.of(1))
                .withProducer(EventEnvelopeTest.class)
                .build();

        assertThrows(IllegalArgumentException.class, () -> {
            new EventEnvelope<>(eventMetadata, eventPayload);
        });
    }

    @Test
    @DisplayName("Should construct on correct parameters")
    void constructsOnCorrectParameters() {
        new EventEnvelope<>(eventMetadata, eventPayload);
    }
}