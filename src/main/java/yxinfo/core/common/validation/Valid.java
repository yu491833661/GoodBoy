package yxinfo.core.common.validation;

import java.lang.annotation.*;

@Target( { ElementType.PARAMETER } )
@Retention( RetentionPolicy.RUNTIME )
@Documented
public @interface Valid {

    Class<?>[] groups() default {};
}
