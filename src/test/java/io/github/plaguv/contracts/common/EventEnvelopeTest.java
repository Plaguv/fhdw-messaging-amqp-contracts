package io.github.plaguv.contracts.common;

import io.github.plaguv.contracts.common.metadata.EventMetadata;
import io.github.plaguv.contracts.common.metadata.EventVersion;
import io.github.plaguv.contracts.common.routing.EventDispatchType;
import io.github.plaguv.contracts.common.routing.EventRouting;
import io.github.plaguv.contracts.common.routing.EventType;
import io.github.plaguv.contracts.event.pos.store.StoreClosedEventInstance;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class EventEnvelopeTest {

    @Test
    @DisplayName("Should throw if null parameter in constructor")
    void throwsOnNull() {
        EventMetadata eventMetadata = EventMetadata.builder()
                .withEventVersion(EventVersion.of(1))
                .withProducer(EventEnvelopeTest.class)
                .build();
        EventRouting eventRouting = EventRouting.builder()
                .withEventDispatchType(EventDispatchType.DIRECT)
                .withEventType(EventType.STORE_OPENED)
                .build();
        EventInstance eventInstance = new StoreClosedEventInstance(5L);

        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new EventEnvelope<>(null, null, null));
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new EventEnvelope<>(eventMetadata, eventRouting, null));
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new EventEnvelope<>(eventMetadata, null, eventInstance));
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new EventEnvelope<>(null, eventRouting, eventInstance));
    }
}