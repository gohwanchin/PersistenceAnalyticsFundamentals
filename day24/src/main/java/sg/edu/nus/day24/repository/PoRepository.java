package sg.edu.nus.day24.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import sg.edu.nus.day24.model.LineItem;
import sg.edu.nus.day24.model.PO;
import static sg.edu.nus.day24.repository.Queries.*;

@Repository
public class PoRepository {

    @Autowired
    JdbcTemplate template;

    public boolean addPO(PO po) {
        int poAdded = template.update(SQL_INSERT_PO, po.getOrderId(), po.getName(), po.getOrderDate());
        return poAdded == 1;
    }

    public boolean addLineItem(PO po) {
        int lineItemAdded = 0;
        try {
            for (LineItem item : po.getLineItems()) {
                if (item.getSku() != null || item.getQty() > 0)
                    lineItemAdded += template.update(SQL_INSERT_LINE_ITEMS, item.getQty().toString(), po.getOrderId(),
                            item.getSku());
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return lineItemAdded > 0;
    }
}
