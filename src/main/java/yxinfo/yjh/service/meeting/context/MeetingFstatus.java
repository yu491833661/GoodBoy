package yxinfo.yjh.service.meeting.context;

/**
 * Created by hlf on 2017/9/15.
 */
public interface MeetingFstatus {

    // 待发布（草稿）
    Short WAIT_PUBLISH = 1;

    // 待进行
    Short WAIT_START = 2;

    // 进行中
    Short STARTING = 3;

    // 完成
    Short FINISH = 4;

    // 取消
    Short CANCEL = 5;
}
