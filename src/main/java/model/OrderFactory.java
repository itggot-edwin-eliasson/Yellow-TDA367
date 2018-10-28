package model;

public class OrderFactory {

    public OrderInterface createOrder(String orderId){
        return new Order(orderId);
    }

}
