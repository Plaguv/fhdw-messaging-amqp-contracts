package io.github.plaguv.contracts.event;

import io.github.plaguv.contracts.event.common.EventMetadata;

/**
 * Represents a domain event published within the system. A {@code DomainEvent}
 * acts as a carrier of business-relevant state changes, enriched with
 * {@link EventMetadata} describing the event's identity, type, version,
 * producer, and lifecycle information.
 */
public interface DomainEvent {

    /**
     * Returns the {@link EventMetadata} describing this event
     * @return the non-null metadata associated with the event
     */
    EventMetadata getEventMetadata();

    /**
     * Indicates whether this event is intended only for internal distribution
     * within the producing application or bounded context.
     * @return {@code true} if the event is internal; {@code false} otherwise
     */
    boolean isInternal();

    /**
     * Indicates whether this event is intended for external distribution across
     * service boundaries.
     * @return {@code true} if the event is external; {@code false} otherwise
     */
    boolean isExternal();
}