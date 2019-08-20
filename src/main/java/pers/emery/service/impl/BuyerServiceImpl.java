package pers.emery.service.impl;

import org.springframework.stereotype.Service;
import pers.emery.dto.OrderDTO;
import pers.emery.service.BuyerService;

@Service
public class BuyerServiceImpl implements BuyerService {
    @Override
    public OrderDTO findOrderOne(String openId, String orderId) {
        return null;
    }

    @Override
    public OrderDTO cancelOrder(String openId, String orderId) {
        return null;
    }
}
