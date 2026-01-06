package com.github.Plaguv.fhdw.messaging.amqp.contracts.event.logistic;

import com.github.Plaguv.fhdw.messaging.amqp.contracts.common.EventMetadata;

import java.util.UUID;

/**
 * Example Message for Logistic System
 */
public record LogisticExampleEvent(
        EventMetadata metadata,
        UUID logisticHallId,
        String logisticHallName
) {}