package com.pzy.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

//Controller处理异常
@ControllerAdvice       //拦截所有controller的注解
public class ControllerExceptionHandler {

    /**
     * looger日志
     * */
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler(Exception.class)  //拦截所有的异常处理
    public ModelAndView exceptionHandler(HttpServletRequest request , Exception e) throws Exception {
        logger.error("Request URL : {},Exception : {}",request.getRequestURL(),e.toString());
        e.printStackTrace();
        //自定义的异常处理
        if (AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class) != null) {
            throw e;
        }

        ModelAndView mv = new ModelAndView();
        mv.addObject("url",request.getRequestURL());
        mv.addObject("exception",e);
        mv.setViewName("error/error");
        return mv;
    }
}
