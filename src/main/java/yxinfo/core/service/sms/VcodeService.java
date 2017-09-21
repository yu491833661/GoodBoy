package yxinfo.core.service.sms;

import yxinfo.core.common.validation.Add;
import yxinfo.core.common.validation.Select;
import yxinfo.core.common.validation.Valid;
import yxinfo.core.common.validation.Validator;
import yxinfo.core.service.sms.dto.EvtVcodeDTO;

/**
 * Created by dy on 2016/6/27.
 */
public interface VcodeService {

    /**
     * 发送验证码
     *
     * @param vcode
     */
    @Validator
    void sendVCode( @Valid( groups = Add.class ) EvtVcodeDTO vcode );

    /**
     * 发送验证码（自动生成验证码）
     *
     * @param vcode
     */
    @Validator
    void sendVCodeCrtCode( @Valid( groups = Add.class ) EvtVcodeDTO vcode );

    /**
     * 验证验证码
     *
     * @param vcode
     * @return
     * @throws 验证不通过时抛出{@link yxinfo.core.framework.exception.DctException}
     */
    @Validator
    void checkVCode( @Valid( groups = Select.class ) EvtVcodeDTO vcode );
}
