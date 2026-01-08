package com.github.plaguv.contracts.event;

import com.github.plaguv.contracts.event.common.EventMetadata;
import com.github.plaguv.contracts.event.common.EventScope;

public abstract class AbstractDomainEvent implements DomainEvent {

    private final EventMetadata eventMetadata;

    public AbstractDomainEvent(EventMetadata eventMetadata) {
        this.eventMetadata = eventMetadata;
    }

    @Override
    public EventMetadata getEventMetadata() {
        return eventMetadata;
    }

    @Override
    public boolean isExternal() {
        return eventMetadata.eventScope() == EventScope.EXTERNAL;
    }

    @Override
    public boolean isInternal() {
        return eventMetadata.eventScope() == EventScope.INTERNAL;
    }
}
