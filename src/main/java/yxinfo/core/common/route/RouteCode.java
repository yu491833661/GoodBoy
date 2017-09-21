package yxinfo.core.common.route;

/**
 * Created by dy on 2017/9/18.
 */
public interface RouteCode {

    // 单位与用户（0600 -> 0501）
    interface Ou {
        // 更新访问令牌
        String UPDATE_ACCESS_TOKEN = "0501001";
        // 访问令牌延期
        String EXTEND_ACCESS_TOKEN = "0501002";
        // 获取用户所有角色
        String GET_MEMBER_ROLE = "0501003";
        // 获取组织角色
        String GET_ORG_ROLE = "0501004";
        // 分页查询组织
        String ORG_PAGE = "0501005";
        // 获取用户组织
        String GET_MEMBER_ORG = "0501006";
        // 获取用户的管理后台菜单
        String MANAGE_MENU = "0501007";
        // 添加角色
        String ADD_ROLE = "0501008";
        // 添加用户组
        String ADD_GROUP = "0501009";
        // 更新用户组
        String UPDATE_GROUP = "0501010";
        // 获取用户组
        String GROUP_LIST = "0501012";
        // 删除用户组
        String DELETE_GROUP = "0501013";
        // 搜索用户
        String SEARCH_EMEBER = "0501014";
        // 删除用户
        String DELETE_MEMBER = "0501017";
        // 移动用户用户组
        String MOVE_MEMBER_GROUP = "0501018";
        // 更新用户登录信息
        String UPDATE_LOGIN_INFO = "0501022";
        // 登录
        String LOGIN = "0501023";
        // 注册
        String REGISTER = "0501024";
        // 获取全部管理后台菜单
        String MANAGE_MENU_LIST = "0501025";
        // 添加角色并授权
        String ADD_ROLE_AND_AUTHORIZE = "0501026";
        // 更新角色与授权
        String UPDATE_ROLE_AND_AUTHORIZE = "0501027";
        // 获取角色与授权
        String GET_ROLE_AND_AUTHORIZE = "0501028";
        // 删除角色与授权
        String DEL_ROLE_AND_AUTHORIZE = "0501029";
        // 注册时发送短信验证码
        String SEND_VCODE_4_REGISTER = "0501030";
        // 重置密码时发送短信验证码
        String SEND_VCODE_4_UPDATE_PWD = "0501031";
    }

    // 短信（0602）
    interface Sms {

    }

    // 文章 01
    interface Article {

        // 发布文章/提问
        String ADD_ARTICLE = "010001";
        // 保存文章/提问草稿
        String SAVE_ARTICLE_DRAFT = "010002";
        // 我的文章/提问列表
        String MY_ARTICLE_LIST = "010003";
        // 版主文章/提问列表
        String ADMIN_ARTICLE_LIST = "010004";
        // 超管文章/提问列表
        String SUPER_ARTICLE_LIST = "010005";
        // 我的回答列表
        String MY_ANSWER_LIST = "010006";
        // 文章/提问详情
        String ARTICLE_DETAIL = "010007";
        // 删除文章/提问
        String DEL_ARTICLE = "010008";
        // 修改文章
        String UPDATE_ARTICLE = "010009";
        // 提问回答列表
        String ARTICLE_ANSWER_LIST = "010010";

    }

    // 审核 02
    interface Approve {
        // 审核列表
        String APPROVE_LIST = "020001";
        // 审核
        String APPROVE = "020002";
    }

    // 宣传 003
    interface Propaganda {

    }

    // 会议 04
    interface Meeting {
        // 取消会议
        String CANCEL_MEETING = "040001";

        // 添加会议
        String ADD_MEETING = "040002";

        // 获取会议详情
        String GET_MEETING_DETAIL = "040003";

        // 管理员获取会议列表
        String GET_ADMIN_MEETING = "040004";

        // 会议封面展示正报名会议列表
        String GET_SIGNING_MEETING = "040005";

        // 展示往期会议列表
        String GET_BEFORE_MEETING = "040006";
    }
}
