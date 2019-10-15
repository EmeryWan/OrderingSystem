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
public class SellerUserController {

    @GetMapping("/user/edit")
    public ModelAndView edit(Map<String, Object> map) {
        return new ModelAndView("user/edit", map);
    }

    @GetMapping("/user/reset")
    public ModelAndView reset(Map<String, Object> map) {
        return new ModelAndView("user/reset", map);
    }

}
