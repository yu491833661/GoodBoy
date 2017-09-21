package yxinfo.core.framework.spring;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

/**
 * Created by hanley on 2016/6/16.
 */
@Component
public class SpringContext implements ApplicationContextAware {

    private static ApplicationContext context;

    public static <T> T getBean( Class<T> aClass ) {
        Assert.notNull( context );
        return context.getBean( aClass );
    }

    public static Object getBean( String name ) {
        Assert.notNull( context );
        return context.getBean( name );
    }

    public void setApplicationContext( ApplicationContext applicationContext ) throws BeansException {
        context = applicationContext;
    }
}
