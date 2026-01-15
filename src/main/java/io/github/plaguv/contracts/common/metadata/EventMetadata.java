package io.github.plaguv.contracts.common.metadata;

import java.time.Instant;
import java.util.UUID;

public record EventMetadata(
        UUID eventId,
        EventVersion eventVersion,
        Instant occurredAt,
        Class<?> producer
) {
    public EventMetadata {
        if (eventId == null) {
            throw new IllegalArgumentException("EventMetadata attribute 'eventId' cannot be null");
        }
        if (eventVersion == null) {
            throw new IllegalArgumentException("EventMetadata attribute 'eventVersion' cannot be null");
        }
        if (occurredAt == null) {
            throw new IllegalArgumentException("EventMetadata attribute 'occurredAt' cannot be null");
        }
        if (producer == null) {
            throw new IllegalArgumentException("EventMetadata attribute 'producer' cannot be null");
        }
    }

    public EventMetadata(Builder builder) {
        this(builder.eventId, builder.eventVersion, builder.occurredAt, builder.producer);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private UUID eventId = UUID.randomUUID();
        private EventVersion eventVersion;
        private Instant occurredAt = Instant.now();
        private Class<?> producer;

        private Builder() {}

        public Builder withEventId(UUID eventId) {
            this.eventId = eventId;
            return this;
        }

        public Builder withEventVersion(EventVersion eventVersion) {
            this.eventVersion = eventVersion;
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
                ", eventVersion=" + eventVersion +
                ", occurredAt=" + occurredAt +
                ", producer=" + producer +
                '}';
    }
}