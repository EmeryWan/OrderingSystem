package pers.emery.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = {Exception.class})
//    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ModelAndView exception() {
        ModelAndView mv = new ModelAndView();
        mv.addObject("msg", "程序内部错误");
        mv.setViewName("error/error");
        return mv;
    }

}
