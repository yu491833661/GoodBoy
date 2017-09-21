package yxinfo.yjh.service.approve.context;

/**
 * @author jn
 * @date 2017/9/15 14:38
 **/
public interface ApproveStatus {

    // 状态（1待审核，2审核通过，3审核不通过，4作废）

    Short WAIT = 1;

    Short SUCCESS = 2;

    Short FAIL = 3;

    Short DISABLED = 4;
}
