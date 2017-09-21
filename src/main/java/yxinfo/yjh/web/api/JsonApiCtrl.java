package yxinfo.yjh.web.api;

import com.alibaba.fastjson.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import yxinfo.core.common.dto.RequestMsg;
import yxinfo.core.common.exception.ErrorCodeCore;
import yxinfo.core.framework.exception.DctException;
import yxinfo.core.service.ou.AccessTokenService;
import yxinfo.core.service.ou.MemberRoleService;
import yxinfo.core.service.ou.context.AccessTokConstants;
import yxinfo.core.service.ou.context.OuConstants;
import yxinfo.core.service.ou.dto.AccessTokenDTO;
import yxinfo.yjh.web.context.RequestAttribute;
import yxinfo.yjh.web.dto.ResponseMsg;
import yxinfo.yjh.web.dto.RouteMsg;
import yxinfo.yjh.web.route.RouteMapper;
import yxinfo.yjh.web.route.RouteProcesser;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

@Controller
public class JsonApiCtrl extends AbstractApiCtrl {

    private static final Logger log = LoggerFactory.getLogger( JsonApiCtrl.class );
    @Resource
    private MemberRoleService memberRoleService;
    @Resource
    private AccessTokenService akService;

    /**
     * 检查访问令牌
     *
     * @param akService
     * @param token
     * @param response
     * @param request
     * @return
     */
    public static AccessTokenDTO checkToken( AccessTokenService akService, String token,
                                             HttpServletResponse response, HttpServletRequest request ) {
        if ( StringUtils.isEmpty( token ) ) {
            throw new DctException( ErrorCodeCore.ACCESS_TOKEN_INVALID );
        }
        AccessTokenDTO accessToken = akService.getAccessToken( token );
        if ( accessToken == null ) {
            throw new DctException( ErrorCodeCore.ACCESS_TOKEN_INVALID );
        }
        if ( accessToken.getCreateAt().before( new Date( System.currentTimeMillis() - AccessTokConstants.EXPIRE_IN ) ) ) {
            throw new DctException( ErrorCodeCore.ACCESS_TOKEN_INVALID );
        }
        return accessToken;
    }

    @RequestMapping( "api/json" )
    public void rest( HttpServletRequest request, HttpServletResponse response ) {

        RequestMsg requestMsg = (RequestMsg) request.getAttribute( RequestAttribute.REQUEST_MSG );
        if ( requestMsg == null ) {
            return;
        }

        String reqData = (String) request.getAttribute( RequestAttribute.REQ_DATA );
        if ( reqData == null ) {
            return;
        }

        try {

            // 获取请求路由
            RouteMsg route = RouteMapper.getInstance().getRouteMsg( requestMsg.getCode() );
            if ( route == null ) {
                retJson( new ResponseMsg( "-2", null, "未知的请求码" ), request, response );
                return;
            }

            if ( !route.isClearAuth() ) {

                // 检查访问令牌
                AccessTokenDTO ak = checkToken( akService, requestMsg.getToken(), response, request );
                if ( ak == null ) {
                    return;
                }
                requestMsg.setMemberId( ak.getMemberId() );

                // 鉴权
                if ( route.getAllowRoles() != null && route.getAllowRoles().size() > 0 ) {
                    List<Integer> roleIds = memberRoleService.getMemberRoleId( ak.getMemberId(), OuConstants.CORE_ORGID );
                    if ( CollectionUtils.isEmpty( roleIds ) ) {
                        throw new DctException( ErrorCodeCore.DO_NOT_HAVE_ACCESS );
                    }
                    boolean notAllow = true;
                    for ( Integer roleId : roleIds ) {
                        if ( route.getAllowRoles().contains( roleId ) ) {
                            notAllow = false;
                            break;
                        }
                    }
                    if ( notAllow ) {
                        throw new DctException( ErrorCodeCore.DO_NOT_HAVE_ACCESS );
                    }
                }
            }

            // 业务处理
            retSuccess( RouteProcesser.process( route, requestMsg, reqData ), request, response );

        } catch ( JSONException e ) {

            // 处理JSON解析异常
            log.error( "JSON解析异常, {}", e.getMessage(), e );
            retJSONException( e.getMessage(), request, response );

        } catch ( DctException e ) {

            retDctException( e.getCode(), e.getExtra(), request, response );

        } catch ( RuntimeException e ) {

            // 处理运行时异常
            if ( e.getCause() instanceof DctException ) {
                DctException de = (DctException) e.getCause();
                retDctException( de.getCode(), de.getExtra(), request, response );
            } else {
                log.error( "运行异常, {}", e.getMessage(), e );
                retException( e, requestMsg.getHit(), request, response );
            }

        } catch ( Exception e ) {

            // 处理异常
            log.error( "异常, {}", e.getMessage(), e );
            retException( e, requestMsg.getHit(), request, response );
        }
    }
}
