package io.github.plaguv.contracts.event.logistic;

import io.github.plaguv.contracts.event.AbstractDomainEvent;
import io.github.plaguv.contracts.event.common.EventMetadata;

/**
 * Example Message for Logistic System
 */
public class LogisticExampleEvent extends AbstractDomainEvent {

    private final Long logisticHallId;
    private final String logisticHallName;

    public LogisticExampleEvent(EventMetadata eventMetadata, Long logisticHallId, String logisticHallName) {
        super(eventMetadata);
        this.logisticHallId = logisticHallId;
        this.logisticHallName = logisticHallName;
    }

    public Long getLogisticHallId() {
        return logisticHallId;
    }

    public String getLogisticHallName() {
        return logisticHallName;
    }
}