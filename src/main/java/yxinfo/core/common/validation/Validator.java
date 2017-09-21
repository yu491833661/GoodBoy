package yxinfo.core.common.validation;

import java.lang.annotation.*;

@Target( { ElementType.METHOD } )
@Retention( RetentionPolicy.RUNTIME )
@Documented
public @interface Validator {

}
