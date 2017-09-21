package yxinfo.core.common.cache;

/**
 * 缓存名称
 * Created by dy on 2017/6/12.
 */
public interface CacheName {

    // 单位与用户
    interface Ou {
        String Ou = "core.ou";
        // 用户
        String USER = "core.ou.user";
        // 角色
        String ROLE = "core.ou.role";
        // 用户角色
        String USER_ROLE = "core.ou.userRole";
        // 用户组
        String GROUP = "core.ou.group";
        // 用户用户组
        String USER_GROUP = "core.ou.userGroup";
        // 组织
        String ORG = "core.ou.org";
        // 用户组织
        String USER_ORG = "core.ou.userOrg";
        // 管理后台菜单
        String MANAGE_MENU = "core.ou.manageMenu";
        // app菜单
        String APP_MENU = "core.ou.appMenu";
        // 角色菜单
        String ROLE_MENU = "core.ou.roleMenu";
        // 访问控制器
        String ACT = "core.ou.act";
        // 角色的访问控制器
        String ROLE_ACT = "core.ou.roleAct";
    }

    // 文章
    interface Article {
        // 文章详情
        String ARTICLE = "yjh.article.article";
        // 首页文章列表
        String HOME_LIST = "yjh.article.front";
    }

    // 宣传
    interface Propaganda {
        String PROPAGANDA = "yjh.propaganda";
        // 网页广告
        String FD_LINK = "yjh.propaganda.fdLink";
        // 网页友情链接
        String AD = "yjh.propaganda.ad";
    }
}
