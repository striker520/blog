package com.fkzm.blog.aspect;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@Aspect
@Component
public class LoggerHandle {
    private final Logger logger= LoggerFactory.getLogger(this.getClass());
    //给所有控制器方法切面,全面监控请求
    @Pointcut("execution(* com.fkzm.blog.controller.*.*(..))")
    public void log() {}

    @Before("log()")
    public void doBefore(JoinPoint joinPoint){

        ServletRequestAttributes attrs= (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpServletRequest req=attrs.getRequest();
        String url=req.getRequestURL().toString();
        String remoteIp=req.getRemoteAddr();
        String classMethod=joinPoint.getSignature().getDeclaringTypeName()+"."+joinPoint.getSignature().getName();
        Object[] args=joinPoint.getArgs();
        RequestInfo reqInfo=new RequestInfo(url, remoteIp,classMethod,args);
        logger.info("requestInfo: {}",reqInfo);
    }

    private class RequestInfo{
        private String url;

        protected RequestInfo(String url, String remoteIp, String classMethod, Object[] args) {
            this.url = url;
            this.remoteIp = remoteIp;
            this.classMethod = classMethod;
            this.args = args;
        }

        private String remoteIp;
        private String classMethod;
        private Object[] args;

        @Override
        public String toString() {
            return "{" +
                    "url='" + url + '\'' +
                    ", remoteIp='" + remoteIp + '\'' +
                    ", classMethod='" + classMethod + '\'' +
                    ", args=" + Arrays.toString(args) +
                    '}';
        }
    }

}
