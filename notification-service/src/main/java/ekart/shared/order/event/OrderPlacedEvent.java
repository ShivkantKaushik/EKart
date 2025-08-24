package ekart.shared.order.event;


//this is placed in shared folder because when producer send message, it sends class with package name
// to consumer, means consumer will deserialize it to same class with package, means both services should
//have OrderPlacedEvent in same package
//so created shared package under ekart in both the services

public class OrderPlacedEvent {

    private String orderNumber;
    private String email;
    private String type;

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
