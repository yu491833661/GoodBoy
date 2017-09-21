package yxinfo.core.framework.validation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import yxinfo.core.framework.exception.DctException;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.*;

/**
 * 参数检验器
 * Created by dy on 2016/06/20.
 */
public class ObjectValidator {

    private static final Logger log = LoggerFactory.getLogger( ObjectValidator.class );

    /**
     * 检验参数
     *
     * @param validBos 待检验参数
     * @throws DctException
     */
    public static void validate( List<ValidBO> validBos ) throws DctException {
        if ( validBos == null || validBos.size() == 0 ) return;
        Map<String, String> errDetails = new HashMap<String, String>();
        for ( ValidBO validBo : validBos ) {
            ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
            Validator validator = factory.getValidator();
            Class[] groups = validBo.getValid().groups();

            if ( validBo.getForValid() != null && validBo.getForValid() instanceof Collection ) {
                for ( Object validObj : (Collection) validBo.getForValid() ) {
                    validate( groups, validObj, validator );
                }
            } else {
                validate( groups, validBo.getForValid(), validator );
            }
        }
    }

    /**
     * 检验参数
     *
     * @param groups
     * @param obj
     * @param validator
     * @throws DctException
     */
    public static void validate( Class[] groups, Object obj ) throws DctException {
        Set<ConstraintViolation<Object>> constraintViolations = null;
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        if ( groups == null || groups.length == 0 ) {
            constraintViolations = validator.validate( obj );
        } else {
            constraintViolations = validator.validate( obj, groups );
        }
        for ( ConstraintViolation<Object> constraintViolation : constraintViolations ) {
            String message = constraintViolation.getMessage();
            String key = constraintViolation.getPropertyPath().toString();
            throw new DctException( message, key );
        }
    }

    /**
     * 检验参数
     *
     * @param groups
     * @param obj
     * @param validator
     * @throws DctException 参数检验异常
     */
    private static void validate( Class[] groups, Object obj, Validator validator ) throws DctException {
        Set<ConstraintViolation<Object>> constraintViolations = null;
        if ( groups == null || groups.length == 0 ) {
            constraintViolations = validator.validate( obj );
        } else {
            constraintViolations = validator.validate( obj, groups );
        }
        for ( ConstraintViolation<Object> constraintViolation : constraintViolations ) {
            String message = constraintViolation.getMessage();
            String key = constraintViolation.getPropertyPath().toString();
            throw new DctException( message, key );
        }
    }
}
