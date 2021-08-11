package com.pzy.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

//配置注解扫描、aop
@Aspect
@Component
public class LogAspect {
    //日志记录
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * @author : pangzy
     * @date : 2021/6/7 11:20
     * @return : void
     * 拦截web包下的所有类
     */
    @Pointcut("execution(* com.pzy.web.*.*(..))")
    public void log(){

    }

    //在log() 方法执行之前执行
    @Before("log()")
    public void doBefore(JoinPoint joinPoint){
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String url = request.getRequestURL().toString();
        String ip = request.getRemoteAddr();
        String classMethod = joinPoint.getSignature().getDeclaringTypeName()+ "." + joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        RequestLog log = new RequestLog(url,ip,classMethod,args);
        logger.info("RequestLog : {}",log);
    }

    // 在log() 方法执行之后执行
    @After("log()")
    public void doAfter(){
//        logger.info("----------- After -------------");
    }

    //拦截返回的内容
    @AfterReturning(returning = "result",pointcut = "log()")
    public void doAfterReturn(Object result){
        logger.info("Result : {}",result);
    }


    private class RequestLog{
        private String url;
        private String ip;
        private String classMethod;
        private Object[] args;

        public RequestLog(String url, String ip, String classMethod, Object[] args) {
            this.url = url;
            this.ip = ip;
            this.classMethod = classMethod;
            this.args = args;
        }

        @Override
        public String toString() {
            return "RequestLog{" +
                    "url='" + url + '\'' +
                    ", ip='" + ip + '\'' +
                    ", classMethod='" + classMethod + '\'' +
                    ", args=" + Arrays.toString(args) +
                    '}';
        }
    }
}
