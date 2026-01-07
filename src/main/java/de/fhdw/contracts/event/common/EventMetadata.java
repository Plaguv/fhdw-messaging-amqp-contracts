package de.fhdw.contracts.event.common;

import java.time.Instant;
import java.util.UUID;

public record EventMetadata(
        UUID eventId,
        EventType eventType,
        EventVersion eventVersion,
        EventScope eventScope,
        Instant occurredAt,
        String producer
) {
    public static EventMetadata now(EventType type, EventVersion version, EventScope eventScope, String producer) {
        return new EventMetadata(
                UUID.randomUUID(),
                type,
                version,
                eventScope,
                Instant.now(),
                producer
        );
    }
}