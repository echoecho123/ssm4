package com.itheima.ssm.controller;

import com.itheima.ssm.domain.SysLog;
import com.itheima.ssm.service.ISysLogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Date;

@Repository
@Aspect
public class LogAop {

    private Date visitTime;
    private String username;
    private String url;
    private String ip;
    private Long executionTime;
    private Method method;
    private Class clazz;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private ISysLogService sysLogService;

    @Pointcut("execution( * com.itheima.ssm.controller.*.*(..))")
    public void pt1(){}

    @Before("pt1()")
    public void doBefore(JoinPoint jp) throws NoSuchMethodException {
        visitTime = new Date();
        clazz = jp.getTarget().getClass();
        String methodName = jp.getSignature().getName();
        Object[] args = jp.getArgs();
        if(args == null && args.length == 0 ){
            method = clazz.getMethod(methodName);
        }else{
            Class[] argsClass = new Class[args.length];
            for (int i = 0; i < args.length; i++) {
                argsClass[i] = args[i].getClass();
            }
            method = clazz.getMethod(methodName,argsClass);
        }
    }

    @After("pt1()")
    public void doAfter(JoinPoint jp) throws Exception {
        executionTime = new Date().getTime() - visitTime.getTime();
        ip = request.getRemoteAddr();
        User user =(User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        username = user.getUsername();
        if(clazz != null){
            RequestMapping clazzAnnotation =(RequestMapping) clazz.getAnnotation(RequestMapping.class);
            if(clazzAnnotation != null){
                String[] clazzValue = clazzAnnotation.value();
                if(method != null){
                    RequestMapping methodAnnotation = method.getAnnotation(RequestMapping.class);
                    if(methodAnnotation != null){
                        String[] methodValue = methodAnnotation.value();
                        url = clazzValue[0] + methodValue[0];
                        if(! "/sysLog/findAll.do".equals(url)){
                            SysLog sysLog = new SysLog();
                            sysLog.setIp(ip);
                            sysLog.setUrl(url);
                            sysLog.setExecutionTime(executionTime);
                            sysLog.setUsername(username);
                            sysLog.setVisitTime(visitTime);
                            sysLog.setMethod("[类名：] "+clazz.getName()+"[方法名：] "+method.getName());
                            sysLogService.save(sysLog);
                        }




                    }
                }


            }

        }





    }



}
