package io.github.plaguv.contracts.common;

import io.github.plaguv.contracts.DomainEvent;

public record EventEnvelope<T extends DomainEvent>(
    EventMetadata metadata,
    T payload
) {
    public EventEnvelope {
        if (metadata == null) {
            throw new IllegalArgumentException("EventEnvelopes metadata cannot be null");
        }
        if (payload == null) {
            throw new IllegalArgumentException("EventEnvelopes payload cannot be null");
        }
        if (metadata.eventType().eventClass() != payload.getClass()) {
            throw new IllegalArgumentException("EventEnvelopes target class and payload class mismatch");
        }
    }
}