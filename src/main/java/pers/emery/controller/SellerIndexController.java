package pers.emery.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/seller")
public class SellerIndexController {

    @RequestMapping("")
    public ModelAndView index() {
        // @RequestMapping("/") 映射的是 seller/
        return new ModelAndView("index/index");
    }

}
