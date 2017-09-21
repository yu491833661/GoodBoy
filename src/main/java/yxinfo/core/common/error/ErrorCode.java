package yxinfo.core.common.error;

/**
 * Created by dy on 2017/6/26.
 */
public interface ErrorCode {

    // 用户与单位（0600 -> 0501）
    interface Ou {
        // 该单位已存在
        String ORG_EXIST = "0501002";
        // 有用户或下级组织, 无法删除
        String GROUP_HAS_MEMBER_OR_CHILD = "0501003";
        // 该用户已注册
        String USER_EXIST = "0501004";
        // 不能删除其他组织的用户
        String CANNOT_DEL_OTHER_ORG_MEMBER = "0501005";
        // 不能编辑其他组织的用户
        String CANNOT_EDIT_OTHER_ORG_MEMBER = "0501006";
        // 用户不存在
        String USER_NOT_FOUND = "0501007";
        // 登录输入密码错误次数过多
        String USER_PWD_ERROR_TOO_MUCH = "0501008";
        // 密码错误
        String PASSWORD_ERROR = "0501009";
        // 请先验证手机
        String REGISTER_MUST_CK_MOBILE = "0501010";
        // 该用户已注册
        String MEMBER_ALREADY_REGISTER = "0501011";
        // 您不是该单位成员，请联系该单位管理员处理
        String CAN_NOT_REGISTER = "0501012";
        // 已完善资料的用户才可修改
        String CAN_NOT_UPDATE_BY_NOT_ACCOMPLISH = "0501013";
    }

    // 短信（0602）
    interface Sms {
        // 发送短信过于频繁
        String VCODE_TOO_OFTEN = "0602001";
        // 短信请求超过限制，请24小时后重试
        String VCODE_TOO_OFTEN_DAY = "0602002";
        // 短信验证码不存在
        String VCODE_NOT_SEND = "0602003";
        // 短信验证码验证超限
        String VCODE_INVALID = "0602004";
        // 短信验证码超时
        String VCODE_EXPIRE = "0602005";
        // 短信验证码不正确
        String VCODE_NOT_MEET = "0602006";
    }
}
