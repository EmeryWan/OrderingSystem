package pers.emery.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Slf4j
@Controller
@RequestMapping("/seller")
public class SellerFinanceController {

    /**
     * 财务营收页面
     */
    @GetMapping("/finance/index")
    public ModelAndView index(Map<String, Object> map) {
        return new ModelAndView("finance/index", map);
    }

    /**
     * 销量页面
     */
    @GetMapping("/finance/sales")
    public ModelAndView sales(Map<String, Object> map) {
        return new ModelAndView("finance/sales", map);
    }

    /**
     * 顾客消费数据
     */
    @GetMapping("/finance/consume")
    public ModelAndView consume(Map<String, Object> map) {
        return new ModelAndView("finance/consume", map);
    }

    /**
     * 分享记录
     */
    @GetMapping("/finance/share")
    public ModelAndView share(Map<String, Object> map) {
        return new ModelAndView("finance/share", map);
    }

}
