package yxinfo.core.service.ou;

import yxinfo.core.common.dto.RequestMsg;
import yxinfo.core.common.route.Route;
import yxinfo.core.common.route.RouteCode;
import yxinfo.core.service.ou.dto.LoginInfoDTO;

/**
 * Created by dy on 2017/7/11.
 */
public interface LoginInfoService {

    /**
     * 更新登录信息
     *
     * @param loginInfo
     */
    void updateLoginInfo( LoginInfoDTO loginInfo );

    /**
     * 更新登录信息
     *
     * @param loginInfo
     */
    @Route( code = RouteCode.Ou.UPDATE_LOGIN_INFO )
    void updateLoginInfo( LoginInfoDTO loginInfo, RequestMsg req );
}
