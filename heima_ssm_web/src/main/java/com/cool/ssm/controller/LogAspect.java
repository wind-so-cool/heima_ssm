package com.cool.ssm.controller;

import com.cool.ssm.domain.SysLog;
import com.cool.ssm.service.ISysLogService;
import org.apache.commons.lang3.ArrayUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Date;

/**
 * @Author 许俊青
 * @Date: 2021-09-19 15:44
 */
@Component
@Aspect
public class LogAspect {

    @Autowired
    private ISysLogService sysLogService;

    private Date visitTime;
    private Class clazz;
    private Method method;

    @Autowired
    private HttpServletRequest request;


    @Before("execution(* com.cool.ssm.controller.*Controller.*(..))")
    public void doBefore(JoinPoint jp) throws NoSuchMethodException {

        clazz = jp.getTarget().getClass();
        if(SysLogController.class==clazz){
            return;
        }
        visitTime = new Date();
        String methodName = jp.getSignature().getName();
        Object[] args = jp.getArgs();
        Class[] argClasses = Arrays.stream(args).map(arg -> arg.getClass()).toArray(Class[]::new);
        method = clazz.getMethod(methodName, argClasses);

    }

    @After("execution(* com.cool.ssm.controller.*Controller.*(..))")
    public void doAfter(JoinPoint jp) {
        if(SysLogController.class==clazz){
            return;
        }
        long time = new Date().getTime() - visitTime.getTime();
        RequestMapping classMapping = (RequestMapping) clazz.getAnnotation(RequestMapping.class);
        final String classUrl = handleUrl(classMapping.value()[0]);
        Annotation[] methodAnnotations = method.getAnnotations();
        if (ArrayUtils.isNotEmpty(methodAnnotations)) {
            Arrays.stream(methodAnnotations).anyMatch(annotation -> {
                String url = "";
                String methodUrl = "";
                boolean anyMatch=false;
                if (annotation.annotationType() == GetMapping.class) {
                    GetMapping getMapping = (GetMapping) annotation;
                    methodUrl = getMapping.value()[0];

                } else if (annotation.annotationType() == PostMapping.class) {
                    PostMapping postMapping = (PostMapping) annotation;
                    methodUrl = postMapping.value()[0];
                } else if (annotation.annotationType() == PutMapping.class) {
                    PutMapping putMapping = (PutMapping) annotation;
                    methodUrl = putMapping.value()[0];
                } else if (annotation.annotationType() == DeleteMapping.class) {
                    DeleteMapping deleteMapping = (DeleteMapping) annotation;
                    methodUrl = deleteMapping.value()[0];
                }
                if(!"".equals(methodUrl)){
                    methodUrl = handleUrl(methodUrl);
                    anyMatch=true;
                }
                if(anyMatch){
                    url = classUrl + methodUrl;
                    String ip = request.getRemoteAddr();
                    User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
                    String username = user.getUsername();
                    SysLog sysLog = new SysLog();
                    sysLog.setExecutionTime(time);
                    sysLog.setIp(ip);
                    sysLog.setVisitTime(visitTime);
                    sysLog.setUsername(username);
                    sysLog.setMethod("[类名] " + clazz.getName() + " [方法名] " + method.getName());
                    sysLog.setUrl(url);
                    sysLogService.save(sysLog);
                }

                return anyMatch;

            });

        }


    }

    private String handleUrl(String url) {
        if (url.charAt(0) != '/') {
            url = "/" + url;
        }
        return url;
    }
}
