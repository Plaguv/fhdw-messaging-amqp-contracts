package com.github.plaguv.contracts.event.common;

public enum EventType {
    // Store related events
    STORE_CREATED,
    STORE_UPDATED,

    // Cashier related events
    CASHIER_ASSIGNED,
    CASHIER_REMOVED,

    // Payment related events
    PAYMENT_REQUESTED,
    PAYMENT_CONFIRMED,
    PAYMENT_FAILED;

    public String routingKey() {
        return name().toLowerCase().replaceAll("_", ".");
    }
}
