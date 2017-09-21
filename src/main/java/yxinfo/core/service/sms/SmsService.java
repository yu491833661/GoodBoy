package yxinfo.core.service.sms;

import java.util.List;

/**
 * Created by dy on 2016/8/4.
 */
public interface SmsService {

    /**
     * 下发通知短信
     *
     * @param orgId   组织id, 传递则计费
     * @param mobiles 手机号
     * @param context 通知内容
     */
    boolean sendSMS( Integer orgId, List<String> mobiles, String context );
}
