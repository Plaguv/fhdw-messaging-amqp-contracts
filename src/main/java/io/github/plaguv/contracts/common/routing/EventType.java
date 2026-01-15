package io.github.plaguv.contracts.common.routing;

import io.github.plaguv.contracts.common.EventInstance;
import io.github.plaguv.contracts.event.pos.store.StoreClosedEventInstance;
import io.github.plaguv.contracts.event.pos.store.StoreOpenedEventInstance;

import java.util.*;

/**
 * Defines the canonical set of event types available in the messaging domain.
 */
public enum EventType {

    /**
     * Indicates that a store has been opened and is now operational.
     * Domain: {@link EventDomain#STORE}
     */
    STORE_OPENED(EventDomain.STORE, StoreOpenedEventInstance.class),

    /**
     * Indicates that a store has been closed.
     * Domain: {@link EventDomain#STORE}
     */
    STORE_CLOSED(EventDomain.STORE, StoreClosedEventInstance.class);

    private final EventDomain eventDomain;
    private final Class<? extends EventInstance> eventClass;
    private final String routingKey;

    /**
     * Constructs a new event type definition.
     *
     * @param eventDomain the logical domain this event belongs to
     * @param eventClass  the concrete event payload class associated with this type
     */
    EventType(EventDomain eventDomain, Class<? extends EventInstance> eventClass) {
        this.eventDomain = eventDomain;
        this.eventClass = eventClass;
        this.routingKey = generateEventRoutingKey();
    }

    /**
     * Constructs a new event type definition with a routingKey override.
     *
     * @param eventDomain the logical domain this event belongs to
     * @param eventClass  the concrete event payload class associated with this type
     * @param routingKey  the override routing key
     */
    EventType(EventDomain eventDomain, Class<? extends EventInstance> eventClass, String routingKey) {
        this.eventDomain = eventDomain;
        this.eventClass = eventClass;
        this.routingKey = routingKey;
    }

    /**
     * Returns the logical domain this event belongs to.
     *
     * @return the event domain
     */
    public EventDomain getEventDomain() {
        return eventDomain;
    }

    /**
     * Returns the concrete payload class associated with this event type.
     * The class must be unique across all event types.
     *
     * @return the event payload class
     */
    public Class<? extends EventInstance> getEventClass() {
        return eventClass;
    }

    /**
     * Returns the concrete routing key associated with this event type.
     * The routing key must be unique across all event types.
     *
     * @return the event routing key
     */
    public String getRoutingKey() {
        return routingKey;
    }

    /**
     * Computes the AMQP routing key for this event type.
     * The routing key is guaranteed to be unique across all event types.
     *
     * @return the routing key in dot-delimited lowercase format
     */
    private String generateEventRoutingKey() {
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
                .filter(eventType -> eventType.getEventDomain() == eventDomain)
                .toList();
    }

    /**
     * Returns the {@link EventType} associated with a given {@link EventInstance} class.
     * <p>
     * This method provides a way to map an event class to its corresponding enum value,
     * which can be useful for determining the domain, routing key, or other metadata dynamically.
     *
     * @param eventClass the class of the event to look up
     * @return an {@link Optional} containing the matching {@link EventType} if found, or empty if no mapping exists
     */
    public static Optional<EventType> fromEventClass(Class<? extends EventInstance> eventClass) {
        return Arrays.stream(EventType.values())
                .filter(eventType -> eventType.getEventClass() == eventClass)
                .findFirst();
    }

    public static Optional<EventType> fromRoutingKey(String routingKey) {
        return Arrays.stream(EventType.values())
                .filter(eventType -> eventType.getRoutingKey().equals(routingKey))
                .findFirst();
    }

    /*
     * --- Validation checks ---
     */

    static {
        validateNotNull();
        validateUniqueEventClasses();
        validateUniqueRoutingKeys();
    }

    /**
     * Validates that all enum values have a {@code not null} attributes assigned to them
     */
    private static void validateNotNull() {
        for (EventType eventType : EventType.values()) {
            if (eventType.getEventDomain() == null) {
                throw new IllegalStateException("Null eventDomain registration detected for eventType: %s".formatted(eventType.name()));
            }
            if (eventType.getEventClass() == null) {
                throw new IllegalStateException("Null eventClass registration detected for eventType: %s".formatted(eventType.name()));
            }
        }
    }

    /**
     * Validates that all enum values have a unique {@link EventInstance} assigned as their {@code eventClass} attribute
     */
    private static void validateUniqueEventClasses() {
        Map<Class<?>, EventType> seen = new HashMap<>();

        for (EventType type : EventType.values()) {
            Class<?> eventClass = type.getEventClass();

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
     * Validates that all enum values have a unique {@code routingKey}
     */
    private static void validateUniqueRoutingKeys() {
        Map<String, EventType> seen = new HashMap<>();

        for (EventType type : EventType.values()) {
            String key = type.getRoutingKey();
            EventType existing = seen.putIfAbsent(key, type);
            if (existing != null) {
                throw new IllegalStateException("""
                        Duplicate routing key: %s
                        Used by %s and %s
                        """
                        .formatted(key, existing, type));
            }
        }
    }
}