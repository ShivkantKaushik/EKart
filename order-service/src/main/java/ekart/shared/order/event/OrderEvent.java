package ekart.shared.order.event;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME, // Use logical type names
        include = JsonTypeInfo.As.PROPERTY, // Include type info as a property
        property = "type" // The name of the property in JSON that holds the type
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = OrderPlacedEvent.class, name = "orderPlaced")
})
public interface OrderEvent {
}
