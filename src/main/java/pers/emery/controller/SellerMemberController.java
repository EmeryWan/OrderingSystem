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
public class SellerMemberController {

    @GetMapping("/member/index")
    public ModelAndView index(Map<String, Object> map) {

        return new ModelAndView("member/index", map);
    }

    @GetMapping("/member/comment")
    public ModelAndView comment(Map<String, Object> map) {
        return new ModelAndView("member/comment", map);
    }

}
