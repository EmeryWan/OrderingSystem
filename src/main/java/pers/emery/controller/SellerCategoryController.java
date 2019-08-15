package pers.emery.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/seller/category")
public class SellerCategoryController {

    @RequestMapping("index")
    public ModelAndView index() {
        return new ModelAndView("category/index");
    }

}
