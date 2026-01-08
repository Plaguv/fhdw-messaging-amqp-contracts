package com.github.plaguv.contracts.event;

import com.github.plaguv.contracts.event.common.EventMetadata;

public interface DomainEvent {
    EventMetadata getEventMetadata();
    boolean isInternal();
    boolean isExternal();
}