package io.github.plaguv.contracts.event.pos.store;

import io.github.plaguv.contracts.common.EventInstance;

public record StoreOpenedEvent(
        Long storeId
) implements EventInstance {
    public StoreOpenedEvent {
        if (storeId == null) {
            throw new IllegalArgumentException("StoreOpenedEvents storeId cannot be null");
        }
    }
}