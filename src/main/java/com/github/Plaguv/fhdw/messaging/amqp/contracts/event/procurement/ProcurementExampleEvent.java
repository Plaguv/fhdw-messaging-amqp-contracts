package com.github.Plaguv.fhdw.messaging.amqp.contracts.event.procurement;

import com.github.Plaguv.fhdw.messaging.amqp.contracts.common.EventMetadata;

import java.util.UUID;

/**
 * Example Message for Procurement System
 */
public record ProcurementExampleEvent(
        EventMetadata metadata,
        UUID procurementArticleId,
        String procurementArticleName
) {}