package io.github.plaguv.contracts.common;

import io.github.plaguv.contracts.common.metadata.EventMetadata;
import io.github.plaguv.contracts.common.routing.EventRouting;

public record EventEnvelope<T extends EventInstance>(
    EventMetadata metadata,
    EventRouting routing,
    T payload
) {
    public EventEnvelope {
        if (metadata == null) {
            throw new IllegalArgumentException("EventEnvelope attribute 'metadata' cannot be null");
        }
        if (routing == null) {
            throw new IllegalArgumentException("EventEnvelope attribute 'routing' cannot be null");
        }
        if (payload == null) {
            throw new IllegalArgumentException("EventEnvelope attribute 'payload' cannot be null");
        }
    }
}