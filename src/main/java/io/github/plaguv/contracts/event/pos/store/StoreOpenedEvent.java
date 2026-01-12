package io.github.plaguv.contracts.event.pos.store;

import io.github.plaguv.contracts.DomainEvent;

public record StoreOpenedEvent (
        Long storeId
) implements DomainEvent {
    public StoreOpenedEvent {
        if (storeId == null) {
            throw new IllegalArgumentException("StoreOpenedEvents storeId cannot be null");
        }
    }
}