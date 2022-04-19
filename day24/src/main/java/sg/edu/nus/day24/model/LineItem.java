package sg.edu.nus.day24.model;

public class LineItem {
    // TODO try if can don't set default to 0
    private Integer sku = 0;
    private Integer qty = 0;

    public Integer getSku() {
        return sku;
    }

    public void setSku(int sku) {
        this.sku = sku;
    }

    public Integer getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }
}
