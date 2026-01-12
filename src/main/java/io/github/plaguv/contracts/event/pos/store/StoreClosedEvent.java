package io.github.plaguv.contracts.event.pos.store;

import io.github.plaguv.contracts.DomainEvent;

public record StoreClosedEvent (
        Long storeId
) implements DomainEvent {
    public StoreClosedEvent {
        if (storeId == null) {
            throw new IllegalArgumentException("StoreClosedEvents storeId cannot be null");
        }
    }
}