package com.github.plaguv.contracts.event.store;

import com.github.plaguv.contracts.event.AbstractDomainEvent;
import com.github.plaguv.contracts.event.common.EventMetadata;

/**
 * Example Message for Store System
 */
public class StoreExampleEvent extends AbstractDomainEvent {

    private final Long storeId;
    private final String storeName;

    public StoreExampleEvent(EventMetadata eventMetadata, Long storeId, String storeName) {
        super(eventMetadata);
        this.storeId = storeId;
        this.storeName = storeName;
    }

    public Long getStoreId() {
        return storeId;
    }

    public String getStoreName() {
        return storeName;
    }
}