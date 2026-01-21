package io.github.plaguv.contracts.common.routing;

import io.github.plaguv.contracts.common.EventInstance;
import io.github.plaguv.contracts.event.pos.store.StoreClosedEvent;
import io.github.plaguv.contracts.event.pos.store.StoreOpenedEvent;

import java.util.*;

public enum EventType {

    STORE_OPENED(EventDomain.STORE, StoreOpenedEvent.class),
    STORE_CLOSED(EventDomain.STORE, StoreClosedEvent.class);

    private final EventDomain eventDomain;
    private final Class<? extends EventInstance> eventClass;

    EventType(EventDomain eventDomain, Class<? extends EventInstance> eventClass) {
        this.eventDomain = eventDomain;
        this.eventClass = eventClass;
    }

    public EventDomain getEventDomain() {
        return eventDomain;
    }

    public Class<? extends EventInstance> getEventClass() {
        return eventClass;
    }

    /* =========================
     * --- Validation checks ---
     * ========================= */
    static {
        validateNotNull();
        validateUniqueEventClasses();
    }

    private static void validateNotNull() {
        for (EventType eventType : EventType.values()) {
            if (eventType.getEventDomain() == null) {
                throw new IllegalStateException("EvenType attribute 'eventDomain' null-registration detected for eventType: ".concat(eventType.name()));
            }
            if (eventType.getEventClass() == null) {
                throw new IllegalStateException("EventType attribute 'eventClass' null-registration detected for eventType: ".concat(eventType.name()));
            }
        }
    }

    private static void validateUniqueEventClasses() {
        Map<Class<?>, EventType> seen = new HashMap<>();

        for (EventType type : EventType.values()) {
            Class<?> eventClass = type.getEventClass();

            EventType existing = seen.putIfAbsent(eventClass, type);
            if (existing != null) {
                throw new IllegalStateException(
                        """
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
}