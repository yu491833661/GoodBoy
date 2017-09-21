package yxinfo.core.common.route;

import java.lang.annotation.*;

/**
 * Created by hanley on 2016/6/23.
 */
@Target( { ElementType.METHOD } )
@Retention( RetentionPolicy.RUNTIME )
@Documented
public @interface AllowRoles {

    int[] roles() default {};
}
