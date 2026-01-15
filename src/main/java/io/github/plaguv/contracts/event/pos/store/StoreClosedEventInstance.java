package io.github.plaguv.contracts.event.pos.store;

import io.github.plaguv.contracts.common.EventInstance;

public record StoreClosedEventInstance(
        Long storeId
) implements EventInstance {
    public StoreClosedEventInstance {
        if (storeId == null) {
            throw new IllegalArgumentException("StoreClosedEvents storeId cannot be null");
        }
    }
}