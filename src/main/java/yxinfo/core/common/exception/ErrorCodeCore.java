package yxinfo.core.common.exception;

/**
 * Created by dy on 2017/6/26.
 */
public interface ErrorCodeCore {

    // 未知的请求码
    String UNKNOWN_CODE = "-2";
    // 业务处理异常
    String BIZ_ERROR = "-3";
    // 请求数据解析失败
    String REQUEST_DATA_ERROR = "-4";
    // 成功
    String SUCCESS = "0";
    // 访问令牌失效
    String ACCESS_TOKEN_INVALID = "001008";
    // 系统异常
    String SYS_ERROR = "000999";
    // 参数不能为null
    String NOT_NULL = "000001";
    // 参数不能为空
    String NOT_EMPTY = "000002";
    // 参数必须为整数
    String DIGITS = "000003";
    // 参数长度错误
    String LENGTH = "000004";
    // 参数必须为false
    String ASSERT_FALSE = "000005";
    // 参数必须为true
    String ASSERT_TRUE = "000006";
    // 参数最大值错误
    String MAX = "000007";
    // 参数最小值错误
    String MIN = "000008";
    // 参数必须是一个将来的日期
    String FUTURE = "000009";
    // 参数必须是一个过去的日期
    String PAST = "000010";
    // 分页获取page错误
    String GET_PAGE = "000011";
    // 不存在
    String NOT_EXIST = "000012";
    // 不合法
    String ILLEGAL = "000013";
    // 请求data不能为空
    String DATA_NOT_NULL = "000014";
    // 无权访问
    String DO_NOT_HAVE_ACCESS = "000015";
    // 拒绝访问
    String REFUSE_ACCESS = "000016";
    // 没有数据
    String NO_DATA = "000017";

    // 文章/提问 01
    interface ARTICLE {
        // 文章修改失败，已经被审核
        String ARTICLE_UPDATE_FAIL = "010001";
        // 文章移动失败，已经被审核
        String ARTICLE_MOVE_FAIL = "010002";
        // 文章移动失败，源板块不能和目标板块相同
        String SRC_BLOCK_EQUAL_TARGET = "010003";
        // 只能修改草稿
        String ONLY_DRAFT = "010004";
        // 没有管理的板块
        String NO_AVAILABLE_BLOCK = "010005";
    }

    // 审核 02
    interface Approve {
        // 找不到审核人员
        String NO_APPROVE_AVAILABLE = "020001";
        // 找不到审核记录
        String APPROVE_NOT_EXIST = "020002";
        // 审核失败，文章被修改或被他人审核
        String APPROVE_FAIL = "020003";
        // 请输入审核意见
        String APPROVE_OPINION = "020004";
        // 文章已经被审核或已作废
        String ALREADY_APPROVE = "020005";
    }

    // 宣传 003
    interface Propaganda {

    }

    interface Meeting {
        // 会议名额不能为空
        String ATTENDPERSON_NOT_NULL = "070001";
        // 会议名额数量必须大于等于1
        String ATTENDPERSON_MUST_NOT_LESS_THAN_ONE = "070002";
        // 会议状态不能为（ 1、待发布 2、待进行 ）之外的状态
        String FSTATUS_NOT_LEGAL = "070003";
        // 报名截至时间不能为空
        String SIGN_END_TIME_NOT_NULL = "070004";
        // 会议开始时间不能为空
        String START_TIME_NOT_NULL = "070005";
        // 会议截至时间按不能为空
        String END_TIME_NOT_NULL= "070006";
        // 报名截至时间不能大于会议开始时间
        String SIGN_TIME_NOT_GREATER_THAN_START = "070007";
        // 会议开始时间不能大于会议结束时间
        String END_TIME_NOT_LESS_THAN_START = "070008";
        // 会议地点不能为空
        String PLACE_NOT_NULL = "070009";
        // 会议地点名称长度不能大于100
        String PLACE_LENTH_LESS_THAN_HUNDRAD = "070010";
        // 会议介绍不能为空
        String INTRUDUCTION_NOT_NULL = "070011";
        // 找不到会议
        String MEETING_NOT_FIND = "070012";
        // 报名截至时间必须大于目前时间
        String SiGN_END_TIME_MUST_GREATE_CURRENT = "070013";
        // 会议开始时间必须大于目前时间
        String START_TIME_MUST_GREATE_CURRENT = "070014";
        // 会议结束时间必须大于目前时间
        String END_TIME_MUST_GREATE_CURRENT = "070015";
    }
}
