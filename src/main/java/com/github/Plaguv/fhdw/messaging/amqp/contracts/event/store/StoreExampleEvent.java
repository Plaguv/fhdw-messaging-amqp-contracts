package com.github.Plaguv.fhdw.messaging.amqp.contracts.event.store;

import com.github.Plaguv.fhdw.messaging.amqp.contracts.common.EventMetadata;

import java.util.UUID;

/**
 * Example Message for Store System
 */
public record StoreExampleEvent(
        EventMetadata metadata,
        UUID storeId,
        String storeName
) {}