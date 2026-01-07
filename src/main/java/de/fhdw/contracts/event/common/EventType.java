package de.fhdw.contracts.event.common;

public enum EventType {
    // Store
    STORE_CREATED,
    STORE_UPDATED,

    // Cashier
    CASHIER_ASSIGNED,
    CASHIER_REMOVED,

    // Payment
    PAYMENT_REQUESTED,
    PAYMENT_CONFIRMED,
    PAYMENT_FAILED;

    public String routingKey() {
        return name().toLowerCase().replaceAll("_", ".");
    }
}
