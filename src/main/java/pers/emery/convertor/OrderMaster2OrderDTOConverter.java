package pers.emery.convertor;

import org.springframework.beans.BeanUtils;
import pers.emery.dataobject.OrderMaster;
import pers.emery.dto.OrderDTO;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author emery
 */
public class OrderMaster2OrderDTOConverter {

    public static OrderDTO convert(OrderMaster orderMaster) {
        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(orderMaster, orderDTO);
        return orderDTO;
    }

    public static List<OrderDTO> convert(List<OrderMaster> orderMasterList) {
        return orderMasterList.stream().map(e -> convert(e))
                .collect(Collectors.toList());
    }

}
