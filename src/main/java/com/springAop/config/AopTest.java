package com.springAop.config;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.springAop.model.User;

@Component
@Aspect
public class AopTest {
	private static final Logger logger = LoggerFactory.getLogger(AopTest.class);

	@Pointcut("execution(public * com.springAop.service.IUserService.insertUser(com.springAop.model.User))&&args(user)")
	private void recordLog(User user) {
		logger.debug(user.getName());
	}
	 /**
     * 前置方法,在目标方法执行前执行
     * 
     * @param joinPoint
     */
    @Before(value="recordLog(user)", argNames="user" )
    public void before(User user) {
        logger.info("保存前方法：beforeSave:{}",user.getName());
    }

    /**
     * 后置方法,在目标方法执行后执行
     * 
     * @param joinPoint
     */
   @After(value="recordLog(user)", argNames="user")
    public void after(User user) {
        logger.info("保存后方法：afterSave()");
    }

    @Around(value="recordLog(user)", argNames="point,user")
    public Object around(ProceedingJoinPoint point,User user) {
        Object obj = null;
        try {
            logger.info("around前");
            point.proceed();
            logger.info("around后");
        } catch (Throwable e) {
            logger.info("aop异常");
        }
        return obj;
    }

   @AfterReturning(returning = "retObj", pointcut = "recordLog(user)",argNames="retObj,user")
    public void doAfterReturning(Object retObj,User user) throws Throwable {
        // 处理完请求，返回内容
	   logger.info("返回值 : " + user.getId());
        logger.info("返回值 : " + retObj);
    }

}
