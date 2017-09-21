package yxinfo.core.framework.validation.spring.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import yxinfo.core.framework.exception.DctException;
import yxinfo.core.framework.spring.aop.BaseAspect;
import yxinfo.core.framework.validation.ObjectValidator;
import yxinfo.core.framework.validation.ValidBO;
import yxinfo.core.common.validation.Valid;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * 参数校验拦截
 * Created by dy on 2016/06/20.
 */
@Component
@Aspect
public class ValidationAspect extends BaseAspect {

    private static final Logger log = LoggerFactory.getLogger( ValidationAspect.class );

    @Pointcut( "@annotation(yxinfo.core.common.validation.Validator)" )
    public void serviceAspect() {

    }

    @Before( "serviceAspect()" )
    public void doBefore( JoinPoint joinPoint ) throws DctException {

        String targetClassName = joinPoint.getTarget().getClass().getName();
        String targetMethodName = joinPoint.getSignature().getName();

        // 获取切点方法
        Method targetMethod = getMethod( joinPoint );
        if ( targetMethod == null ) return;

        // 获取待校验参数
        List<ValidBO> validBoList = new ArrayList<ValidBO>();
        try {
            Annotation[][] parameterAnnotations = targetMethod.getParameterAnnotations();
            if ( parameterAnnotations == null || parameterAnnotations.length == 0 ) {
                return;
            }
            int i = 0;
            Object[] arguments = joinPoint.getArgs();
            for ( Annotation[] parameterAnnotation : parameterAnnotations ) {
                for ( Annotation annotation : parameterAnnotation ) {
                    if ( annotation instanceof Valid ) {
                        Valid valid = (Valid) annotation;
                        validBoList.add( new ValidBO( arguments[i], valid ) );
                        break;
                    }
                }
                i++;
            }
        } catch ( Exception e ) {
            log.error( "Aspect before error, {}", e.getMessage(), e );
        }

        // 校验参数
        ObjectValidator.validate( validBoList );
    }

}
