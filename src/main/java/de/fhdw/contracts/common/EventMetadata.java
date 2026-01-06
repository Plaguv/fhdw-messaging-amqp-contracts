package de.fhdw.contracts.common;

import java.time.Instant;
import java.util.UUID;

public record EventMetadata(
        UUID eventId,
        EventType eventType,
        EventVersion eventVersion,
        Instant occurredAt,
        String producer
) {
    public static EventMetadata now(EventType type, EventVersion version, String producer) {
        return new EventMetadata(
                UUID.randomUUID(),
                type,
                version,
                Instant.now(),
                producer
        );
    }
}