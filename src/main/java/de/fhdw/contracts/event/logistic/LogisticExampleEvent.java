package de.fhdw.contracts.event.logistic;

import de.fhdw.contracts.common.EventMetadata;

import java.util.UUID;

/**
 * Example Message for Logistic System
 */
public record LogisticExampleEvent(
        EventMetadata metadata,
        UUID logisticHallId,
        String logisticHallName
) {}