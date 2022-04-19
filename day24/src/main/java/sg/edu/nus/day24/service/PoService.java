package sg.edu.nus.day24.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sg.edu.nus.day24.model.PO;
import sg.edu.nus.day24.repository.PoRepository;

@Service
public class PoService {

    @Autowired
    PoRepository poRepo;

    @Transactional
    public void addPO(PO po) {
        if (!poRepo.addPO(po) || !poRepo.addLineItem(po)) {
            throw new DataAccessException("Cannot add PO " + po.getOrderId()) {
            };
        }
    }
}
