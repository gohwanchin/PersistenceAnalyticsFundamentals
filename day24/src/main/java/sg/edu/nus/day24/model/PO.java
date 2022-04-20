package sg.edu.nus.day24.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PO {
    private String orderId;
    private String name;
    private Date orderDate;
    private List<LineItem> lineItems = new ArrayList<>();

    public PO() {
        this.orderId = generateID(8);
        for (int i = 0; i < 4; i++) {
            this.addLineItem(new LineItem());
        }
    }

    private synchronized String generateID(int numChars) {
        return UUID.randomUUID().toString().substring(0, numChars);
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public List<LineItem> getLineItems() {
        return lineItems;
    }

    public void setLineItems(List<LineItem> lineItems) {
        this.lineItems = lineItems;
    }

    public void addLineItem(LineItem lineItem) {
        this.lineItems.add(lineItem);
    }
}
