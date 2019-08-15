package pers.emery.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import pers.emery.dto.OrderDTO;
import pers.emery.exception.SellException;
import pers.emery.service.OrderService;

import java.util.Map;

@Slf4j
@Controller
@RequestMapping("/seller/order")
public class SellerOrderController {

    private final OrderService orderService;

    @Autowired
    public SellerOrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @RequestMapping("/index")
    public ModelAndView index(@RequestParam(value = "page", defaultValue = "1") Integer page,
                              @RequestParam(value = "size", defaultValue = "10") Integer size,
                              Map<String, Object> map) {

        // 分页信息
        PageRequest pageRequest = PageRequest.of(page - 1, size);
        Page<OrderDTO> orderDTOPage = orderService.findList(pageRequest);
        log.info(orderDTOPage.getContent().toString());

        map.put("orderDTOPage", orderDTOPage);
        map.put("currentPage", page);
        map.put("size", size);

        return new ModelAndView("order/index", map);
    }

    /**
     * 查看单个订单的详细信息
     */
    @GetMapping("/info/{id}")
    public ModelAndView info(@PathVariable String id,
                             Map<String, Object> map) {
        OrderDTO orderDTO = new OrderDTO();
        try {
            orderDTO = orderService.findById(id);
        } catch (SellException e) {
            log.error("【卖家端查询订单详情】发生异常{}", e);
            map.put("msg", e.getMessage());
            map.put("url", "/sell/seller/order/list");
            return new ModelAndView("common/error", map);
        }

        map.put("orderDTO", orderDTO);
        return new ModelAndView("order/info", map);
    }

    /**
     * 取消订单
     */

    /**
     * 完结订单
     */

}
