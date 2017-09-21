package yxinfo.core.framework.validation;

import yxinfo.core.common.validation.Valid;

/**
 * 待校验参数
 * Created by dy on 2016/06/20.
 */
public class ValidBO {

    private Object forValid;

    private Valid valid;

    public ValidBO( Object forCheck, Valid valid ) {
        this.forValid = forCheck;
        this.valid = valid;
    }

    public Object getForValid() {
        return forValid;
    }

    public void setForValid( Object forValid ) {
        this.forValid = forValid;
    }

    public Valid getValid() {
        return valid;
    }

    public void setValid( Valid valid ) {
        this.valid = valid;
    }
}
