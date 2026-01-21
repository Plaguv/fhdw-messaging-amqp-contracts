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

    @Override
    public String toString() {
        return "EventRouting{" +
                "eventType=" + eventType +
                ", eventDispatchType=" + eventDispatchType +
                '}';
    }
}