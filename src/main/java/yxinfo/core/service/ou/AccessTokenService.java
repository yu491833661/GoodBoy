package yxinfo.core.service.ou;

import yxinfo.core.common.route.Route;
import yxinfo.core.common.route.RouteCode;
import yxinfo.core.service.ou.dto.AccessTokenDTO;

/**
 * Created by dy on 2017/6/28.
 */
public interface AccessTokenService {

    /**
     * 创建访问令牌
     *
     * @param terminal 终端编号
     * @param memberId 用户id
     * @return
     */
    AccessTokenDTO createAccessToken( String terminal, Integer memberId );

    /**
     * 更新访问令牌
     *
     * @param accessToken
     * @return
     */
    @Route( code = RouteCode.Ou.UPDATE_ACCESS_TOKEN )
    AccessTokenDTO updateAccessToken( String accessToken );

    /**
     * 访问令牌延期
     *
     * @param accessToken
     * @return
     */
    @Route( code = RouteCode.Ou.EXTEND_ACCESS_TOKEN )
    AccessTokenDTO extendAccessToken( String accessToken );

    /**
     * 获取访问令牌
     *
     * @param accessToken
     * @return
     */
    AccessTokenDTO getAccessToken( String accessToken );

    /**
     * 查找访问令牌
     *
     * @param memberId    用户id
     * @param derviceType 终端类型编号
     * @return
     */
    AccessTokenDTO getAccessToken( Integer memberId, String derviceType );
}
