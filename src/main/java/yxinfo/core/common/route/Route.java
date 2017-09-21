package yxinfo.core.common.route;

import java.lang.annotation.*;

/**
 * Created by dy on 2017/6/5.
 */
@Target( { ElementType.METHOD } )
@Retention( RetentionPolicy.RUNTIME )
@Documented
public @interface Route {

    String code();

    Class dataType() default Class.class;

    int[] allowRoles() default {};
}
