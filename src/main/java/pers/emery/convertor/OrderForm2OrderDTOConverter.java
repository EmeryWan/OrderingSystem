package pers.emery.convertor;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.extern.slf4j.Slf4j;
import pers.emery.dataobject.OrderDetail;
import pers.emery.dto.OrderDTO;
import pers.emery.enums.ResultEnum;
import pers.emery.exception.SellException;
import pers.emery.form.OrderForm;

import java.util.List;

/**
 * @author emery
 */
@Slf4j
public class OrderForm2OrderDTOConverter {

    public static OrderDTO convert(OrderForm form) {
        Gson gson = new Gson();
        OrderDTO orderDTO = new OrderDTO();

        orderDTO.setBuyerName(form.getName());
        orderDTO.setBuyerPhone(form.getPhone());
        orderDTO.setBuyerAddress(form.getAddress());
        orderDTO.setBuyerOpenid(form.getOpenid());

        // 将前端 form 中的 item 购物数据存入 orderDetailList
        List<OrderDetail> orderDetailList;
        try {
            orderDetailList = gson.fromJson(form.getItems(), new TypeToken<List<OrderDetail>>() {
            }.getType());
        } catch (Exception e) {
            log.error("【对象转换】错误, String={}", form.getItems());
            throw new SellException(ResultEnum.PARAM_ERROR);
        }
        orderDTO.setOrderDetailList(orderDetailList);

        return orderDTO;
    }

}
