package sg.edu.nus.day24.repository;

public interface Queries {
    public static final String SQL_INSERT_PO 
        = "insert into purchase_order (order_id, name, order_date) values(?, ?, ?)";
    public static final String SQL_INSERT_LINE_ITEMS
        = "insert into line_item (quantity, order_id, prod_id) values(?, ?, ?)";
}
