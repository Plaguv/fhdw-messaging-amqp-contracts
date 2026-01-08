package com.github.plaguv.contracts.event.procurement;

import com.github.plaguv.contracts.event.AbstractDomainEvent;
import com.github.plaguv.contracts.event.common.EventMetadata;

/**
 * Example Message for Procurement System
 */
public class ProcurementExampleEvent extends AbstractDomainEvent {

    private final Long procurementArticleId;
    private final String procurementArticleName;

    public ProcurementExampleEvent(EventMetadata eventMetadata, Long procurementArticleId, String procurementArticleName) {
        super(eventMetadata);
        this.procurementArticleId = procurementArticleId;
        this.procurementArticleName = procurementArticleName;
    }

    public Long getProcurementArticleId() {
        return procurementArticleId;
    }

    public String getProcurementArticleName() {
        return procurementArticleName;
    }
}