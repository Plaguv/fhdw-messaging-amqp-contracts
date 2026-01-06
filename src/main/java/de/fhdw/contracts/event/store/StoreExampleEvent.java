package de.fhdw.contracts.event.store;

import de.fhdw.contracts.common.EventMetadata;

import java.util.UUID;

/**
 * Example Message for Store System
 */
public record StoreExampleEvent(
        EventMetadata metadata,
        UUID storeId,
        String storeName
) {}