package cn.yang.controller;

import cn.yang.domain.SysLog;
import cn.yang.service.ISysLogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.sound.midi.Soundbank;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Date;

/***
 * @ClassName: LogAop
 * @Description:
 * @Auther: 6yang
 * @Date: 2019/10/1316:07
 * @version : V1.0
 */
@Component
@Aspect
public class LogAop {

    //在web.xml 文件中去配置 RequestContextListener 监听器 之后就可以直接注入了
    @Autowired
    private HttpServletRequest request;

    @Autowired
    private ISysLogService sysLogService;

    private Date visitTime; //访问的开始时间
    private Class clazz;    //访问的类
    private Method method;  //访问的方法

    @Pointcut("execution(* cn.yang.controller.*.*(..))")
    public void pt1(){}

    //主要是获取开始时间 ，以及执行的类、方法
    @Before("pt1()")
    public void doBefore(JoinPoint jp) throws NoSuchMethodException {
        visitTime = new Date(); //当前时间就是开始访问的时间
        clazz = jp.getTarget().getClass(); // 获取具体访问的类对象
        String methodName = jp.getSignature().getName(); //获取访问的方法名称
        Object[] args = jp.getArgs();
        if (args==null || args.length==0){
            method = clazz.getMethod(methodName); // 只能获取无参数方法
        }else{
            Class[] classArgs = new Class[args.length];
            for (int i = 0; i < classArgs.length; i++) {
                classArgs[i] = args[i].getClass();
                System.out.println(args[i].getClass());
            }

            method = clazz.getMethod(methodName,classArgs);
        }

    }

    @After("pt1()")
    public void doAfter(JoinPoint jp){
        long time = new Date().getTime() - visitTime.getTime();
        String url = null;
        /*
        * 1 获取访问的url
        * */
        if (clazz !=null&& method!=null&&clazz !=LogAop.class&&clazz!=SysLogController.class){
            //1.1 获取类上的@requestMapping 值
            RequestMapping classAnnotation = (RequestMapping) clazz.getAnnotation(RequestMapping.class);
            if(classAnnotation!=null){
                String classUrl = classAnnotation.value()[0];
                //1.2 获取方法上的@requestMapping 值
                RequestMapping methodAnnotation = method.getAnnotation(RequestMapping.class);
                if (methodAnnotation!=null){
                    String methodUrl = methodAnnotation.value()[0];
                    url =classUrl +methodUrl;
                }
            }
            /*
             * 2 获取访问者的ip  通过request对象来获取
             * 在web.xml 文件中去配置 RequestContextListener 监听器
             * */
            String ip = request.getRemoteAddr();
            /*
             * 3 获取当前操作的用户
             *   (1)可以通过springSecurity获取，
             *   (2)也可以通过request.getSession获取 request.getSession().getAttribute("SPRING_SECURITY_CONTEXT")
             * */
            SecurityContext context = SecurityContextHolder.getContext(); //从上下文获取当前登录的用户
            User user = (User) context.getAuthentication().getPrincipal();
            String username = user.getUsername();

            /*
             * 4 将相关信息封装到SysLog对象
             * */
            SysLog sysLog = new SysLog();
            sysLog.setExecutionTime(time);
            sysLog.setIp(ip);
            sysLog.setUrl(url);
            sysLog.setUsername(username);
            sysLog.setVisitTime(visitTime);
            sysLog.setMethod(clazz.getName()+"."+method.getName());

            sysLogService.save(sysLog);
        }

    }
}
