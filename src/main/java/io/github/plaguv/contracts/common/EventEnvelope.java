package io.github.plaguv.contracts.common;

import io.github.plaguv.contracts.common.metadata.EventMetadata;
import io.github.plaguv.contracts.common.routing.EventRouting;

public record EventEnvelope(
        EventMetadata metadata,
        EventRouting routing,
        EventInstance payload
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

        if (payload.getClass() != routing.eventType().getEventClass()) {
            throw new IllegalArgumentException(
                    """
                    EventClass conflict detected in EventEnvelope. Choose one class, but not both:
                    routing class: %s
                    payload class: %s
                    """
                    .formatted(
                            routing.eventType().getEventClass().getSimpleName(),
                            payload.getClass().getSimpleName()
                    )
            );
        }
    }

    @Override
    public String toString() {
        return "EventEnvelope{" +
                "metadata=" + metadata +
                ", routing=" + routing +
                ", payload=" + payload +
                '}';
    }
}