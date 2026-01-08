package com.github.plaguv.contracts.event.logistic;

import com.github.plaguv.contracts.event.common.EventMetadata;

import java.util.UUID;

/**
 * Example Message for Logistic System
 */
public record LogisticExampleEvent(
        EventMetadata metadata,
        UUID logisticHallId,
        String logisticHallName
) {}