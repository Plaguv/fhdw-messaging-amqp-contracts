package com.github.plaguv.contracts.event.procurement;

import com.github.plaguv.contracts.event.common.EventMetadata;

import java.util.UUID;

/**
 * Example Message for Procurement System
 */
public record ProcurementExampleEvent(
        EventMetadata metadata,
        UUID procurementArticleId,
        String procurementArticleName
) {}