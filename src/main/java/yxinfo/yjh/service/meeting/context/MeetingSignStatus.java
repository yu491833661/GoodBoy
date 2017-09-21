package yxinfo.yjh.service.meeting.context;

/**
 * Created by hlf on 2017/9/18.
 */
public interface MeetingSignStatus {

    // 待报名
    Short WAIT_SIGN = 1;

    // 报名中
    Short SIGNING = 2;

    // 报名结束
    Short SIGN_END = 3;

}
