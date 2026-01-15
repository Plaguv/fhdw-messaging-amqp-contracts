package io.github.plaguv.contracts.common.routing;

public record EventRouting(
        EventType eventType,
        EventDispatchType eventDispatchType
) {
    public EventRouting {
        if (eventType == null) {
            throw new IllegalArgumentException("EventRouting attribute 'eventType' cannot be null");
        }
        if (eventDispatchType == null) {
            throw new IllegalArgumentException("EventRouting attribute 'eventDispatchType' cannot be null");
        }
    }

    public EventRouting(Builder builder) {
        this(builder.eventType, builder.eventDispatchType);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private EventType eventType;
        private EventDispatchType eventDispatchType;

        private Builder() {}

        public Builder withEventType(EventType eventType) {
            this.eventType = eventType;
            return this;
        }

        public Builder withEventDispatchType(EventDispatchType eventDispatchType) {
            this.eventDispatchType = eventDispatchType;
            return this;
        }

        public EventRouting build() {
            return new EventRouting(this);
        }
    }

    @Override
    public String toString() {
        return "EventRouting{" +
                "eventType=" + eventType +
                ", eventDispatchType=" + eventDispatchType +
                '}';
    }
}