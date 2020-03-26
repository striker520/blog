package com.fkzm.blog.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

//全局异常处理,数据预处理,同名属性添加前缀,当两个实体 Bean有同名属性时,绑定就回出现问题,需要给他们起别名,在入参中添加@ModelAttribute("a")@ModelAttribute("b")将他们区分
//在此类中添加两个@InitBinder("a")@InitBinder("b")方法,入参为WebDataBinder ,方法体setFieldDefaultPrefix("a."),setFieldDefaultPrefix("b."),完成绑定
@ControllerAdvice
public class ControllerExceptionHandler {
    private Logger logger= LoggerFactory.getLogger(this.getClass());
//    @InitBinder("a")
//    public void a(WebDataBinder binder){
//        binder.setFieldDefaultPrefix("a.");
//    }
//    @InitBinder("b")
//    public void b(WebDataBinder binder){
//        binder.setFieldDefaultPrefix("b.");
//    }

    //只有加上注解才能处理全局异常
    @ExceptionHandler(Exception.class)
    public ModelAndView handleException(Exception e, HttpServletRequest req) throws Exception {
        logger.error("RequestUrl: {},Exception: {}",req.getRequestURL(),e.getMessage());

        //带状态码的异常抛出给上一级处理,即返回404,500等页面,其他错误返回错误页面
        if(AnnotationUtils.findAnnotation(e.getClass(),ResponseStatus.class)!=null){
            throw e;
        }

        ModelAndView modelAndView=new ModelAndView("error/error");
        modelAndView.addObject("url",req.getRequestURL());
        modelAndView.addObject("exception", e);
        return modelAndView;

    }


}
