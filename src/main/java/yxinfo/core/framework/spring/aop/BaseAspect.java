package yxinfo.core.framework.spring.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;

/**
 * Created by dy on 2016/06/20.
 */
public abstract class BaseAspect {

    private static final Logger log = LoggerFactory.getLogger( BaseAspect.class );

    /**
     * 获取切点方法
     *
     * @param joinPoint
     * @return
     */
    public Method getMethod( JoinPoint joinPoint ) {
        Method targetMethod = null;
        try {
            String targetName = joinPoint.getTarget().getClass().getName();
            String methodName = joinPoint.getSignature().getName();
            Class targetClass = Class.forName( targetName );
            Class[] parameterTypes = ( (MethodSignature) joinPoint.getSignature() ).getMethod().getParameterTypes();
            targetMethod = targetClass.getMethod( methodName, parameterTypes );
        } catch ( Exception e ) {
            log.info( "GetJoinPointMethod Error, {}", e.getMessage(), e );
        }
        return targetMethod;
    }
}
