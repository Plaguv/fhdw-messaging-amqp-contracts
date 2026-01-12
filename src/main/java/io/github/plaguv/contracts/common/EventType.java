package io.github.plaguv.contracts.common;

import io.github.plaguv.contracts.DomainEvent;
import io.github.plaguv.contracts.event.pos.store.StoreClosedEvent;
import io.github.plaguv.contracts.event.pos.store.StoreOpenedEvent;

import java.util.*;

/**
 * Defines the canonical set of event types available in the messaging domain.
 */
public enum EventType {

    /**
     * Indicates that a store has been opened and is now operational.
     * Domain: {@link EventDomain#STORE}
     */
    STORE_OPENED(EventDomain.STORE, StoreOpenedEvent.class),

    /**
     * Indicates that a store has been closed.
     * Domain: {@link EventDomain#STORE}
     */
    STORE_CLOSED(EventDomain.STORE, StoreClosedEvent.class);

    private final EventDomain eventDomain;
    private final Class<? extends DomainEvent> eventClass;

    /**
     * Constructs a new event type definition.
     *
     * @param eventDomain the logical domain this event belongs to
     * @param eventClass  the concrete event payload class associated with this type
     */
    EventType(EventDomain eventDomain, Class<? extends DomainEvent> eventClass) {
        this.eventDomain = eventDomain;
        this.eventClass = eventClass;
    }

    /**
     * Returns the logical domain this event belongs to.
     *
     * @return the event domain
     */
    public EventDomain eventDomain() {
        return eventDomain;
    }

    /**
     * Returns the concrete payload class associated with this event type.
     * The class must be unique across all event types.
     *
     * @return the event payload class
     */
    public Class<? extends DomainEvent> eventClass() {
        return eventClass;
    }

    /**
     * Computes the AMQP routing key for this event type.
     * The routing key is guaranteed to be unique across all event types.
     *
     * @return the routing key in dot-delimited lowercase format
     */
    public String eventRoutingKey() {
        String normalizedName = name().toLowerCase().replace("_", ".");

        if (normalizedName.startsWith(eventDomain.name().toLowerCase() + ".")) {
            return normalizedName;
        } else {
            return eventDomain.name().toLowerCase() + "." + normalizedName;
        }
    }

    /**
     * Returns a list of all {@link EventType} instances that belong to the given {@link EventDomain}.
     * <p>
     * This allows consumers of the enum to retrieve all events associated with a specific domain,
     * for example to register listeners or route messages dynamically.
     *
     * @param eventDomain the domain for which to retrieve the event types
     * @return a list of {@link EventType} instances in the given domain; never null, empty if no events exist
     */
    public static List<EventType> fromEventDomain(EventDomain eventDomain) {
        return Arrays.stream(EventType.values())
                .filter(eventType -> eventType.eventDomain() == eventDomain)
                .toList();
    }

    /**
     * Returns the {@link EventType} associated with a given {@link DomainEvent} class.
     * <p>
     * This method provides a way to map an event class to its corresponding enum value,
     * which can be useful for determining the domain, routing key, or other metadata dynamically.
     *
     * @param eventClass the class of the event to look up
     * @return an {@link Optional} containing the matching {@link EventType} if found, or empty if no mapping exists
     */
    public static Optional<EventType> fromEventClass(Class<? extends DomainEvent> eventClass) {
        return Arrays.stream(EventType.values())
                .filter(eventType -> eventType.eventClass() == eventClass)
                .findFirst();
    }

    /*
     * --- Event Class uniqueness checks ---
     */
    static {
        validateNotNull();
        validateUniqueEventClasses();
        validateRoutingKeys();
    }

    /**
     * Validates that all enum values have a {@code not null} attributes assigned to them
     */
    private static void validateNotNull() {
        for (EventType eventType : EventType.values()) {
            if (eventType.eventDomain() == null) {
                throw new IllegalStateException("Null eventDomain registration detected for eventType: %s".formatted(eventType.name()));
            }
            if (eventType.eventClass() == null) {
                throw new IllegalStateException("Null eventClass registration detected for eventType: %s".formatted(eventType.name()));
            }
        }
    }

    /**
     * Validates that all enum values have a unique {@link DomainEvent} assigned as their {@code eventClass} attribute
     */
    private static void validateUniqueEventClasses() {
        Map<Class<?>, EventType> seen = new HashMap<>();

        for (EventType type : EventType.values()) {
            Class<?> eventClass = type.eventClass();

            EventType existing = seen.putIfAbsent(eventClass, type);
            if (existing != null) {
                throw new IllegalStateException("""
                        Duplicate eventClass registration detected:
                        Event class: %s
                        Used by: %s AND %s
                        """
                        .formatted(
                                eventClass.getName(),
                                existing.name(),
                                type.name()
                        )
                );
            }
        }
    }

    /**
     * Validates that all enum values have a unique {@code routing key}
     */
    private static void validateRoutingKeys() {
        Map<String, EventType> seen = new HashMap<>();

        for (EventType type : EventType.values()) {
            String key = type.eventRoutingKey();
            EventType existing = seen.putIfAbsent(key, type);
            if (existing != null) {
                throw new IllegalStateException("Duplicate routing key: %s used by %s and %s".formatted(key, existing, type));
            }
        }
    }
}