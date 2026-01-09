package io.github.plaguv.contracts.event.common;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class EventMetadataTest {

    @Test
    @DisplayName("Constructor should reject null arguments")
    void constructorRejectsNullArguments() {
        UUID eventId = UUID.randomUUID();
        EventType eventType = EventType.CASHIER_ASSIGNED;
        EventVersion eventVersion = EventVersion.of(1);
        EventScope eventScope = EventScope.INTERNAL;
        Instant occurredAt = Instant.now();
        Class<?> producer = String.class;

        assertThrows(IllegalArgumentException.class,
                () -> new EventMetadata(null, eventType, eventVersion, eventScope, occurredAt, producer));

        assertThrows(IllegalArgumentException.class,
                () -> new EventMetadata(eventId, null, eventVersion, eventScope, occurredAt, producer));

        assertThrows(IllegalArgumentException.class,
                () -> new EventMetadata(eventId, eventType, null, eventScope, occurredAt, producer));

        assertThrows(IllegalArgumentException.class,
                () -> new EventMetadata(eventId, eventType, eventVersion, null, occurredAt, producer));

        assertThrows(IllegalArgumentException.class,
                () -> new EventMetadata(eventId, eventType, eventVersion, eventScope, null, producer));

        assertThrows(IllegalArgumentException.class,
                () -> new EventMetadata(eventId, eventType, eventVersion, eventScope, occurredAt, null));
    }

    @Test
    @DisplayName("Builder should reject null arguments")
    void builderRejectsNullArguments() {
        assertThrows(IllegalArgumentException.class,
                () -> EventMetadata.builder().build());
        assertThrows(IllegalArgumentException.class,
                () -> EventMetadata.builder().withEventId(null).build());
        assertThrows(IllegalArgumentException.class,
                () -> EventMetadata.builder().withEventType(null).build());
        assertThrows(IllegalArgumentException.class,
                () -> EventMetadata.builder().withEventVersion(null).build());
        assertThrows(IllegalArgumentException.class,
                () -> EventMetadata.builder().withOccurredAt(null).build());
        assertThrows(IllegalArgumentException.class,
                () -> EventMetadata.builder().withProducer(null).build());
    }

    @Test
    @DisplayName("Constructor initializes fields with given arguments")
    void constructorInitializesFields() {
        UUID eventId = UUID.randomUUID();
        EventType eventType = EventType.CASHIER_ASSIGNED;
        EventVersion eventVersion = EventVersion.of(1);
        EventScope eventScope = EventScope.INTERNAL;
        Instant occurredAt = Instant.now();
        Class<?> producer = String.class;

        EventMetadata eventMetadata = new EventMetadata(eventId, eventType, eventVersion, eventScope, occurredAt, producer);

        assertEquals(eventId, eventMetadata.eventId());
        assertEquals(eventType, eventMetadata.eventType());
        assertEquals(eventVersion, eventMetadata.eventVersion());
        assertEquals(eventScope, eventMetadata.eventScope());
        assertEquals(occurredAt, eventMetadata.occurredAt());
        assertEquals(producer, eventMetadata.producer());
    }

    @Test
    @DisplayName("Constructor initializes fields with a builder as an argument")
    void constructorAcceptsBuilder() {
        UUID eventId = UUID.randomUUID();
        EventType eventType = EventType.CASHIER_ASSIGNED;
        EventVersion eventVersion = EventVersion.of(1);
        EventScope eventScope = EventScope.INTERNAL;
        Instant occurredAt = Instant.now();
        Class<?> producer = String.class;

        EventMetadata eventMetadata = new EventMetadata(
                EventMetadata.builder()
                .withEventId(eventId)
                .withEventType(eventType)
                .withEventVersion(eventVersion)
                .withEventScope(eventScope)
                .withOccurredAt(occurredAt)
                .withProducer(producer)
        );

        assertEquals(eventId, eventMetadata.eventId());
        assertEquals(eventType, eventMetadata.eventType());
        assertEquals(eventVersion, eventMetadata.eventVersion());
        assertEquals(eventScope, eventMetadata.eventScope());
        assertEquals(occurredAt, eventMetadata.occurredAt());
        assertEquals(producer, eventMetadata.producer());
    }

    @Test
    @DisplayName("Builder initializes fields with default values if not given")
    void builderInitializesDefaults() {
        EventMetadata eventMetadata = EventMetadata.builder()
                .withEventType(EventType.CASHIER_ASSIGNED)
                .withEventVersion(EventVersion.of(1))
                .withEventScope(EventScope.INTERNAL)
                .withProducer(Object.class)
                .build();

        // Not set above. Should be default
        assertNotNull(eventMetadata.eventId());
        assertNotNull(eventMetadata.occurredAt());
    }

    @Test
    @DisplayName("Builder initializes fields with given arguments")
    void builderInitializesFields() {
        UUID eventId = UUID.randomUUID();
        EventType eventType = EventType.CASHIER_ASSIGNED;
        EventVersion eventVersion = EventVersion.of(1);
        EventScope eventScope = EventScope.INTERNAL;
        Instant occurredAt = Instant.now();
        Class<?> producer = String.class;

        EventMetadata eventMetadata = EventMetadata.builder()
                .withEventId(eventId)
                .withEventType(eventType)
                .withEventVersion(eventVersion)
                .withEventScope(eventScope)
                .withOccurredAt(occurredAt)
                .withProducer(producer)
                .build();

        assertEquals(eventId, eventMetadata.eventId());
        assertEquals(eventType, eventMetadata.eventType());
        assertEquals(eventVersion, eventMetadata.eventVersion());
        assertEquals(eventScope, eventMetadata.eventScope());
        assertEquals(occurredAt, eventMetadata.occurredAt());
        assertEquals(producer, eventMetadata.producer());
    }

    @Test
    @DisplayName("Builder creates unique builder instance")
    void builderCreatesUniqueInstance() {
        EventMetadata.Builder builderOne = EventMetadata.builder();
        EventMetadata.Builder builderTwo = EventMetadata.builder();
        assertNotEquals(builderOne, builderTwo);
    }
}