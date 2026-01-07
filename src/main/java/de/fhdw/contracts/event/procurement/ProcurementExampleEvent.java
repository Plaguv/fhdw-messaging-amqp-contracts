package de.fhdw.contracts.event.procurement;

import de.fhdw.contracts.event.common.EventMetadata;

import java.util.UUID;

/**
 * Example Message for Procurement System
 */
public record ProcurementExampleEvent(
        EventMetadata metadata,
        UUID procurementArticleId,
        String procurementArticleName
) {}