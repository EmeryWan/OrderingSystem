package pers.emery.service;

import pers.emery.dto.OrderDTO;

public interface BuyerService {

    /**
     * 查询订单
     */
    OrderDTO findOrderOne(String openId, String orderId);

    /**
     * 取消订单
     */
    OrderDTO cancelOrder(String openId, String orderId);

}
