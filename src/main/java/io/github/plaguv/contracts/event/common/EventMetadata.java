package io.github.plaguv.contracts.event.common;

import java.time.Instant;
import java.util.UUID;

public record EventMetadata(
        UUID eventId,
        EventType eventType,
        EventVersion eventVersion,
        EventScope eventScope,
        Instant occurredAt,
        Class<?> producer)
{
    public EventMetadata {
        if (eventId == null) {
            throw new IllegalArgumentException("EventId cannot be null");
        }
        if (eventType == null) {
            throw new IllegalArgumentException("EventType cannot be null");
        }
        if (eventVersion == null) {
            throw new IllegalArgumentException("EventVersion cannot be null");
        }
        if (eventScope == null) {
            throw new IllegalArgumentException("EventScope cannot be null");
        }
        if (occurredAt == null) {
            throw new IllegalArgumentException("OccurredAt cannot be null");
        }
        if (producer == null) {
            throw new IllegalArgumentException("Producer cannot be null");
        }
    }

    public EventMetadata(Builder builder) {
        this(builder.eventId, builder.eventType, builder.eventVersion, builder.eventScope, builder.occurredAt, builder.producer);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private UUID eventId = UUID.randomUUID();
        private EventType eventType;
        private EventVersion eventVersion;
        private EventScope eventScope;
        private Instant occurredAt = Instant.now();
        private Class<?> producer;

        public Builder withEventId(UUID eventId) {
            this.eventId = eventId;
            return this;
        }

        public Builder withEventType(EventType eventType) {
            this.eventType = eventType;
            return this;
        }

        public Builder withEventVersion(EventVersion eventVersion) {
            this.eventVersion = eventVersion;
            return this;
        }

        public Builder withEventScope(EventScope eventScope) {
            this.eventScope = eventScope;
            return this;
        }

        public Builder withOccurredAt(Instant occurredAt) {
            this.occurredAt = occurredAt;
            return this;
        }

        public Builder withProducer(Class<?> producer) {
            this.producer = producer;
            return this;
        }

        public EventMetadata build() {
            return new EventMetadata(this);
        }
    }

    @Override
    public String toString() {
        return "EventMetadata{" +
                "eventId=" + eventId +
                ", eventType=" + eventType +
                ", eventVersion=" + eventVersion +
                ", eventScope=" + eventScope +
                ", occurredAt=" + occurredAt +
                ", producer=" + producer +
                '}';
    }
}